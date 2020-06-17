package net.tce.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tce.cache.EmpresaConfCache;
import net.tce.cache.FileCache;
import net.tce.dto.EmpresaConfDto;
import net.tce.dto.TipoContenidoDto;
import net.tce.util.Constante;
import net.tce.util.Mensaje;
import net.tce.util.UtilsTCE;
import org.apache.log4j.Logger;

/**
 * Filtra toda peticion al sistema
 * @author Goyo
 *
 */

public class AppFilter implements Filter {
	Logger log4j = Logger.getLogger( this.getClass());
	
	public final static int BODY_SIN_ARCHIVO_BYTES=51;

	/**
	 * 
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	

	/**
	 * Se analizan todos los request, al entrar al sistema
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		 HttpServletRequest httpRequest = (HttpServletRequest) request;
	     HttpServletResponse httpResponse = (HttpServletResponse) response;
	     //log4j.debug(" #& AppIni -> doFilter --> wContentLength="+httpRequest.getHeader("Content-Length"));
	     
	     //Se analiza si el request es del protocolo SOAP_MTOM
	   if(httpRequest.getHeader("Content-type") != null && 
		  httpRequest.getHeader("Content-type").indexOf("application/xop+xml") > 0){
	    	StringBuilder sbMensaje=null;
	    	TCERequestWrapper requestWrapper=new TCERequestWrapper(httpRequest);
	    	BufferedReader bufferedReader =requestWrapper.getReader();
	    	char[] charBuffer = new char[128]; 
	    	int bytesRead = -1;
           	String idTipoContenido=null;
           	String idEmpresaConf=null;
           	String tipoArchivo=null;
           	int contador=0;
           	StringBuilder sbBody=new StringBuilder();
           	//Se obtiene la parte principal del "body"
            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) { 
            	//Se revisa estos tokens en el "body", para saber si hay un archivo adjunto o no
            	 if(String.valueOf(charBuffer).toLowerCase().contains("binary") ||
            		String.valueOf(charBuffer).toLowerCase().contains("encoding")){
            		 contador++;
            	 }
            	 sbBody.append(charBuffer, 0, bytesRead);
            	//si se cumple esta condicion, el request trae archivo
            	if(contador == 2){
            		break;
            	}
            } 
            log4j.debug(" ##& doFilter --> sbp="+
            		sbBody.toString().substring(0,sbBody.toString().lastIndexOf("binary") + "binary".length()));
            log4j.debug(" ##& doFilter --> count:="+contador+" bytes_sbBody="+
            		sbBody.toString().substring(0,sbBody.toString().lastIndexOf("binary") + "binary".length()).length());
            //Si se cumple esta condicion significa que se adjunto un archivo en el request
            if(contador == 2){
            	//Como hay archivo, obtengamos los siguientes parametros:
             	// idTipoContenido
               	if(sbBody.toString().contains("<idTipoContenido>")){
                		idTipoContenido=sbBody.toString().substring(sbBody.toString().indexOf("<idTipoContenido>")+
                										 "<idTipoContenido>".length(), 
                										 sbBody.toString().indexOf("</idTipoContenido>"));
                	}
            	//  idEmpresaConf
            	if(sbBody.toString().contains("<idEmpresaConf>")){
            		idEmpresaConf=sbBody.toString().substring(sbBody.toString().indexOf("<idEmpresaConf>")+
            										 "<idEmpresaConf>".length(), 
            										 sbBody.toString().indexOf("</idEmpresaConf>"));
            	}
            	// tipoArchivo
            	if(sbBody.toString().contains("<tipoArchivo>")){
            		tipoArchivo=sbBody.toString().substring(sbBody.toString().indexOf("<tipoArchivo>")+
            										 "<tipoArchivo>".length(), 
            										 sbBody.toString().indexOf("</tipoArchivo>"));
            	}
            	log4j.debug(" ##& doFilter --> idEmpresaConf="+idEmpresaConf+" idTipoContenido="+idTipoContenido+
              					" tipoArchivo="+tipoArchivo);
            	//Son obligatorios los parametros: idEmpresaConf, idTipoContenido y tipoArchivo
            	if(idEmpresaConf != null && idTipoContenido != null && tipoArchivo != null){
            		
            		//Ahora si se obtiene el idEmpresaConf del cache
        			EmpresaConfDto empresaConfDto=EmpresaConfCache.get(Long.valueOf(idEmpresaConf));
        			
        			//Hay registro en el cache
        			if(empresaConfDto != null){
        				 log4j.debug(" ##& doFilter --> getIdConf="+empresaConfDto.getIdConf());
	            		 //Se obtiene el tipo contenido(filtros).La clave se compone de: idConf + _ + idTipoContenido
	    	             TipoContenidoDto tipoContenidoDto=FileCache.getChmContenido(
	    	            									new StringBuilder(empresaConfDto.getIdConf().toString()).
	    	            									append("_").append(idTipoContenido).toString());
	    	            log4j.debug(" ##& doFilter --> tipoContenidoDto="+tipoContenidoDto);
	    	            //si hay tipo contenido
	    	            if(tipoContenidoDto != null){
	    	            	 //se calcula el tamaño real del archivo, partiendo del request
	    	            	 Long sizeFile=Long.parseLong(requestWrapper.getHeader("Content-Length"))- 
	    	            	 				(sbBody.toString().substring(0,sbBody.toString().
	    	            	 				lastIndexOf("binary") + "binary".length()).length()
	    	            	 				+ BODY_SIN_ARCHIVO_BYTES);
	    	            	 log4j.debug(" ##& doFilter -->getIdTipoContenido="+tipoContenidoDto.getIdTipoContenido()+
	    		            		" getMinSize="+tipoContenidoDto.getMinSize()+
	    		            		" getMaxSize="+tipoContenidoDto.getMaxSize()+
	    		            		" getTypes="+tipoContenidoDto.getTypes());
	    	            	 log4j.debug(" ##& doFilter --> sizeRequest="+requestWrapper.getHeader("Content-Length")+
	    		            		" sizeFile="+sizeFile);
	    	            	 //se efectua el filtro: tipo Archivo
	    	            	 if(tipoContenidoDto.getTypes() != null && 
	    	            		tipoContenidoDto.getTypes().indexOf(tipoArchivo.toLowerCase().concat("|")) == -1){
	    	            		 	//no existe el tipo de archivo en el json
	    	            		 	sbMensaje=new StringBuilder("<code>").append(Mensaje.SERVICE_CODE_002).append("</code>").
						     					append("<type>").append(Mensaje.SERVICE_TYPE_ERROR).append("</type>").
						     					append("<message>").append(Mensaje.SERVICE_MSG_ERROR_UPLOAD_TYPE).append("</message>");
		    	            	 
	    	            	 }
	    	            	 //si hay error no seguir
	    	            	 if(sbMensaje == null){
		    	            	 //se efectua el filtro de minSize
		    	            	 if(tipoContenidoDto.getMinSize() != null && 
		    	            		(sizeFile < tipoContenidoDto.getMinSize().longValue())){
		    	            		 sbMensaje=new StringBuilder("<code>").append(Mensaje.SERVICE_CODE_008).append("</code>").
							     			   append("<type>").append(Mensaje.SERVICE_TYPE_ERROR).append("</type>").
							     			   append("<message>").append(Mensaje.SERVICE_MSG_ERROR_UPLOAD_SIZE_MIN).append("</message>");
		    	            	 }
		    	            	 //se efectua el filtro de maxSize
		    	            	 if(tipoContenidoDto.getMaxSize() != null &&
		    	            		(sizeFile > tipoContenidoDto.getMaxSize().longValue())){
		    	            		 sbMensaje=new StringBuilder("<code>").append(Mensaje.SERVICE_CODE_009).append("</code>").
		    				     			   append("<type>").append(Mensaje.SERVICE_TYPE_ERROR).append("</type>").
		    				     			   append("<message>").append(Mensaje.SERVICE_MSG_ERROR_UPLOAD_SIZE_MAX).append("</message>");
		    	            	 }
	    	            	 }
	    	            }else{
	    	            	//No hay filtros 
	    	            	sbMensaje=new StringBuilder("<code>").append(Mensaje.SERVICE_CODE_002).append("</code>").
				        				append("<type>").append(Mensaje.SERVICE_TYPE_FATAL).append("</type>").
				        				append("<message>").append(Mensaje.MSG_ERROR_SISTEMA).append("</message>");
	    	            	log4j.error(new StringBuilder("Los filtros: types, minSize y maxSize, para idConf:").
	    	            				append(empresaConfDto.getIdConf()).append(" e idTipoContenido:").append(idTipoContenido).
	    	            				append(" ,no se encuentran en la tabla EMPRESA_INTERFASE"));
	    	            }
        			}else{
        				 //no hay idconf
        				 sbMensaje=new StringBuilder("<code>").append(Mensaje.SERVICE_CODE_002).append("</code>").
				     			   append("<type>").append(Mensaje.SERVICE_TYPE_FATAL).append("</type>").
				     			   append("<message>").append(Mensaje.SERVICE_MSG_ERROR_DATACONF).append("</message>");
        				 log4j.error("No existe objeto en EmpresaConfCache para el idEmpresaConf:"+idEmpresaConf);
        			}
            	}else{
            		sbMensaje=new StringBuilder("<code>").append(Mensaje.SERVICE_CODE_006).append("</code>").
    							append("<type>").append(Mensaje.SERVICE_TYPE_FATAL).append("</type>").
    							append("<message>").append(Mensaje.SERVICE_MSG_ERROR_UPLOAD_ERROR).append("</message>");
            	}
            	log4j.debug(" ##& doFilter --> mensaje="+sbMensaje);
            	  //si mensaje no es nulo, hay error
                 if(sbMensaje != null){
                	 //a barrer la basura
                	 requestWrapper=null;
                	 httpRequest=null;
     		    	StringBuilder sbclave=new StringBuilder();
     		    	//Se obtiene la clave aleatoria para el mensaje response
     		    	sbclave.append(UtilsTCE.getCadenaAlfanumAleatoria(8).toLowerCase()).append("-").
     		    	append(UtilsTCE.getCadenaAlfanumAleatoria(4).toLowerCase()).append("-").
     		    	append(UtilsTCE.getCadenaAlfanumAleatoria(4).toLowerCase()).append("-").
     		    	append(UtilsTCE.getCadenaAlfanumAleatoria(4).toLowerCase()).append("-").
     		    	append(UtilsTCE.getCadenaAlfanumAleatoria(12).toLowerCase());
     		    	log4j.debug(" ##& doFilter --> clave="+sbclave.toString());
     		    	PrintWriter out = httpResponse.getWriter();
     		    	//Se crea mensaje header
     		    	httpResponse.addHeader("Content-Type",new StringBuilder("multipart/related;start=\"<rootpart*").
     	    			append(sbclave.toString()).append("@example.jaxws.sun.com>\";type=\"application/xop+xml\";boundary=\"uuid:").
     	    	    	append(sbclave.toString()).append("\";start-info=\"text/xml\";charset=UTF-8").toString());
     		    	//Para el protocolo MTOM
     		    	httpResponse.flushBuffer();
     		    	out.println(new StringBuilder("--uuid:").append(sbclave.toString()).toString());
     		    	out.println(new StringBuilder("Content-Id: <rootpart*").append(sbclave.toString()).
     		    				append("@example.jaxws.sun.com>").toString());
     		    	out.println("Content-Type: application/xop+xml;charset=utf-8;type=\"text/xml\"");
     		    	out.println("Content-Transfer-Encoding: binary");
     		    	out.println("");
     		    	out.println("<?xml version='1.0' encoding='UTF-8'?><S:Envelope xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\"><S:Body>");
     		    	out.println(new StringBuilder("<ns2:fileUploadResponse xmlns:ns2=\"http://jaxws.adapter.admin.tce.net/\">").
     		    	    		append("<return><item>").append(sbMensaje.toString()).
     		    	    		append("</item></return></ns2:fileUploadResponse></S:Body></S:Envelope>").toString());
     		    	out.println("2f");
     		    	out.println("");
     		    	out.println(new StringBuilder("--uuid:").append(sbclave.toString()).append("--").toString());
     		    	out.close();
     		    	out.flush();
                 }else{
                	 chain.doFilter(requestWrapper, httpResponse); 
                }  
            }else{
            	chain.doFilter(requestWrapper, httpResponse);
            }
  	    }else{
	    	  chain.doFilter(httpRequest, httpResponse);
	   }
	}

	/**
	 * 
	 */
    public void destroy() {
		
	}

}
