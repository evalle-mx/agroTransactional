package net.tce.admin.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;
import net.tce.admin.service.EmpresaInterfaseService;
import net.tce.app.exception.SystemTCEException;
import net.tce.cache.DataInfoCache;
import net.tce.cache.EmpresaConfCache;
//import net.tce.dao.EmpresaParametroDao;
import net.tce.dto.EmpresaConfDto;
import net.tce.dto.TipoContenidoDto;
//import net.tce.model.EmpresaParametro;
import net.tce.util.Constante;
import net.tce.util.Mensaje;
//import net.tce.util.Validador;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Clase donde se aplica las politicas de negocio del servicio empresaInterfase
 * @author Goyo
 *
 */
@Transactional
@Service("empresaInterfaseService")
public class EmpresaInterfaseServiceImpl implements EmpresaInterfaseService{
	Logger log4j = Logger.getLogger( this.getClass());
	
	HashMap<String, Object> currFilters;
	ArrayList<String> orderby;

//	@Autowired
//	private EmpresaParametroDao empresaParametroDao;
	

//	/**
//	 * Regresa un map con el id_empresa como llave y   el json_valor como valor
//	 * @return el map si hay informacion, de lo contrario regresa nulo
//	 */
//	@SuppressWarnings("unchecked")
//	public ConcurrentHashMap<String, TipoContenidoDto>  getAllContenido(){
//		log4j.debug("<getAllContenido>");
//		ConcurrentHashMap<String, TipoContenidoDto> chmValor =null;
//		HashMap<String, Object> currFilters  = new HashMap<String, Object>();
//		//incluir id_tipo_parametro=8, cuando se ocupe empresa parametro
//		ArrayList<String> orderby=new ArrayList<String>();
//		currFilters.put("contexto","Contenido");
//		currFilters.put("descripcion","contenido");
//		currFilters.put("tipoParametro.idTipoParametro",Constante.PARAMETRO_DATACONF.longValue());
//		orderby.add(0,"conf.idConf");	
//		orderby.add(1,"descripcion");	
//		currFilters.put(Constante.SQL_ORDERBY,orderby );
//		List<EmpresaParametro> lsEmpresaInterfase=empresaParametroDao.getByFilters(currFilters);
//		//Se verifica si hay información
//		if(lsEmpresaInterfase != null && lsEmpresaInterfase.size() >0){
//			log4j.debug("<getAllContenido> Se obtuvo parametros de Contenido ");
//			StringBuilder sbJson=null;
//			StringBuilder sbClave=new StringBuilder();
//			JSONArray jaContenido=null;
//			chmValor = new ConcurrentHashMap<String, TipoContenidoDto>();
//			Iterator<EmpresaParametro>  itEmpresaInterfase=lsEmpresaInterfase.iterator();
//			//se itera y se ponen los valores en el map
//			while(itEmpresaInterfase.hasNext()){
//				EmpresaParametro empresaParametro=itEmpresaInterfase.next();
//				sbJson=new StringBuilder("[");
//				//log4j.debug("getAllContenido() -> getValor="+empresaInterfase.getValor());
//				try {
//					jaContenido=new JSONArray(empresaParametro.getValor());
//					log4j.debug("<getAllContenido> Contenido.length="+jaContenido.length());
//					for (int i = 0; i < jaContenido.length(); i++) {
//						String json=jaContenido.get(i).toString();
//						sbJson.append(json.substring(json.indexOf(":")+1,json.length()-1));
//						if((jaContenido.length() - i) == 1){
//							sbJson.append("]");
//						}else{
//							sbJson.append(",");
//						}
//					}
//					log4j.info("<getAllContenido> Parametros iniciales de las imagenes="+sbJson.toString());
//					Iterator<TipoContenidoDto> itTipoContenidoDto=((List<TipoContenidoDto>)new Gson().
//																  fromJson(sbJson.toString(),
//																  new TypeToken<List<TipoContenidoDto>>(){}.
//																  getType())).iterator();
//					//Se obtienen los tipos contenidos
//					while(itTipoContenidoDto.hasNext()){
//						TipoContenidoDto tipoContenidoDto=itTipoContenidoDto.next();
//						//La clave se compone de: idConf + _ + idTipoContenido
//						sbClave.append(String.valueOf(empresaParametro.getConf().getIdConf())).
//						append("_").append(tipoContenidoDto.getIdTipoContenido());
//						
//						tipoContenidoDto.setTypes(new StringBuilder(tipoContenidoDto.getTypes()).
//												  append("|").toString());
//						chmValor.put(sbClave.toString(),tipoContenidoDto);
//						log4j.debug("<getAllContenido> DUMMY-CREATE chmValor.put("+ sbClave.toString() + ", "+tipoContenidoDto +"); ");
//						sbClave.delete(0, sbClave.length());
//					}
//					
//				} catch (JSONException e) {
//					log4j.error("<getAllContenido> Error al crear el map, para guardar en el cache los filtros de tipo_contenido %%");
//					chmValor=null;
//					e.printStackTrace();
//				}
//				
//				log4j.debug("<getAllContenido> chmValor: "+chmValor);
//			}
//		}else{
//			log4j.error("<getAllContenido> No hay informacion en base de datos, relacionado a los contenidos(File),"+
//						" en la tabla EMPRESA_PARAMETRO. Por lo tanto no se va a poder subir ningun archivo. %%");
//		}
//		return chmValor;
//	}
	

//	/**
//	 * Regresa un objeto datainfo
//	 * @param idEmpresa, referida al id de la empresa
//	 * @param contexto, es nombre de la tabla
//	 * @param object, es el objeto datainfo 
//	 * @return el objeto datainfo si hay informacion de lo contrario regresa nulo
//	 * @throws Exception 
//	 */
//	public Object dataConf(String idEmpresaConf, String contexto, Object objectDataInfo) throws Exception {
//		Object objectDataInfoResp=null;
//		//Se revisa si es numero
//		if(Pattern.matches(Validador.NUMERICDECPATT,idEmpresaConf)){
//			//se obtiene el idconf
//			EmpresaConfDto empresaConfDto=EmpresaConfCache.get(Long.valueOf(idEmpresaConf));
//			if(empresaConfDto != null){
//				
//				// OJO -> Ya que los dataconf son los metadatos de las tablas se va a tomar para todos los casos el idconf=0
//				empresaConfDto.setIdConf(new Long(0));
//				
//				//dar un vistazo al cache
//				//DataInfoCache.viewchmDataInfo();
//				//Se obtiene del cache
//				objectDataInfoResp=DataInfoCache.get(new StringBuilder(objectDataInfo.getClass().getName()).
//								   append(empresaConfDto.getIdConf()).toString()); 
//				log4j.debug("%%% dataConf -> objectDataInfo.name="+objectDataInfo.getClass().getName()+
//						" getIdConf="+empresaConfDto.getIdConf());
//				//No esta en el cache
//				if(objectDataInfoResp == null ){
//					//incluir id_tipo_parametro=8, cuando se ocupe empresa parametro
//					currFilters  = new HashMap<String, Object>();
//					orderby=new ArrayList<String>();
//					currFilters.put("conf.idConf",empresaConfDto.getIdConf());
//					currFilters.put("contexto",contexto);
//					currFilters.put("tipoParametro.idTipoParametro",Constante.PARAMETRO_DATACONF.longValue());
//					orderby.add(0,"descripcion");	
//					currFilters.put(Constante.SQL_ORDERBY,orderby );
//					List<EmpresaParametro> lsDataConf=empresaParametroDao.getByFilters(currFilters);
//					if(lsDataConf != null && lsDataConf.size() > 0){
//						Field field =null;
//						Iterator<EmpresaParametro> itDataConf= lsDataConf.iterator();
//						while(itDataConf.hasNext()){
//							EmpresaParametro empresaParametro=itDataConf.next();
//							try {
//								field =objectDataInfo.getClass().getDeclaredField(empresaParametro.getDescripcion());
//								field.setAccessible(true);
//								//log4j.debug("%%% dataConf -> getValor="+empresaParametro.getValor()+" getDescripcion="+empresaParametro.getDescripcion());
//								field.set(objectDataInfo, empresaParametro.getValor());
//							
//							} catch (NoSuchFieldException ex) {
//								//Este error no es necesario usarlo
//								/*log4j.debug("dataConf() -> No hacer nada para "+objectDataInfo.getClass().getName()+
//										" --> "+ex.toString());*/
//							} catch (Exception e) {
//								log4j.error("Error al obtener dataconf".concat(" , en el dto:").
//											concat(objectDataInfo.getClass().getName()).
//											concat(" --> ").concat(e.toString()));
//								 throw new Exception(e);
//							}
//						}
//						//Se guarda en cache
//						//llave es : nombreClase + idConf
//						DataInfoCache.set(new StringBuilder(objectDataInfo.getClass().getName()).
//											append(empresaConfDto.getIdConf()).toString(), objectDataInfo);
//						objectDataInfoResp=objectDataInfo;
//						objectDataInfo=null;
//					}else{
//						log4j.error(new StringBuilder(Mensaje.MSG_ERROR_DATACONF).append(contexto).
//									append(" , para la empresa(id)=").append(empresaConfDto.getIdConf()).toString());
//						objectDataInfoResp=null;
//					}
//				}
//			}else{
//				log4j.error(new StringBuilder("El idEmpresaconf:").append(idEmpresaConf).
//							append(", no esta registrado.").toString());
//			}
//		}
//		return objectDataInfoResp;
//	}


	
}
