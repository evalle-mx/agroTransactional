package net.tce.admin.service.impl;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.tce.admin.service.CommonService;
import net.tce.admin.service.EmpresaParametroService;
import net.tce.app.exception.SystemTCEException;
import net.tce.cache.ParametrosCache;
import net.tce.dto.EmpresaParametroDto;
//import net.tce.util.Validador;

@Transactional
@Service("commonService")
public class CommonServiceImpl implements CommonService{
	Logger log4j = Logger.getLogger( this.getClass());
	
	String resp;
	List<EmpresaParametroDto> lsEmpresaParametroDto;
	
	@Autowired
	private EmpresaParametroService empresaParametroService;

//	/**
//	 * 
//	 * @param objetoRevision, contiene las propiedades donde van a a ser evaluadas las expresiones(EmpresaParametro)
//	 * @param noAplicaPropiedadInicial, nombre de la propiedad inicial de la expresion que no se toma en cuenta
//	 * @param idEmpresaConf, el id de empresa conf
//	 * @param tipoParametro, tipo de parámetro
//	 * @return	si es String es un error
//	 * 			si es boolean y si es true  se cumple la expresion en la propiedad
//	 * 							si es false no se cumple
//	 * @throws SystemTCEException 
//	 * @throws Exception
//	 */
//	@SuppressWarnings("unchecked")
//	public Object seEvaluaPropiedad(Object objetoRevision,String noAplicaPropiedadInicial, String idEmpresaConf, String tipoParametro) throws SystemTCEException {
//		//try {
//			lsEmpresaParametroDto=ParametrosCache.get(new StringBuilder(idEmpresaConf).append(tipoParametro).toString());
//			log4j.debug("seEvaluaPropiedad() -> del cache -> lsEmpresaParametroDto="+lsEmpresaParametroDto);
//			if(lsEmpresaParametroDto != null ){
//				return Validador.searchProperty(lsEmpresaParametroDto,objetoRevision, noAplicaPropiedadInicial );
//			}else{
//				//Obtiene la lista de atributos requeridos para la publicación
//				resp=empresaParametroService.get(new EmpresaParametroDto(tipoParametro,idEmpresaConf,null));		
//				log4j.debug("seEvaluaPropiedad() -> del servicio -> resp="+resp);
//				
//				if(resp.indexOf("code") != -1){
//					return resp;
//				}else{
//					lsEmpresaParametroDto=(List<EmpresaParametroDto>)new Gson().fromJson(resp,
//							new TypeToken<List<EmpresaParametroDto>>(){}.getType());
//					if(lsEmpresaParametroDto != null && lsEmpresaParametroDto.size() > 0){
//						log4j.debug("seEvaluaPropiedad() -> se pone en cache");
//						ParametrosCache.put(new StringBuilder(idEmpresaConf).append(tipoParametro).toString(), lsEmpresaParametroDto);
//					}
//					return Validador.searchProperty(lsEmpresaParametroDto,objetoRevision, noAplicaPropiedadInicial );
//				}	
//			}					
//		/*} catch (Exception e) {
//			log4j.error(" ERROR en el validador de propiedades ");
//			e.printStackTrace();
//			return UtilsTCE.getJsonMessageGson(null, new MensajeDto(null,null,
//					Mensaje.SERVICE_CODE_002,Mensaje.SERVICE_TYPE_FATAL,
//					Mensaje.MSG_ERROR));
//		}*/
//	}
	
//	/**
//	 * Verifica que todas las validaciones se cumplan para el objeto determinado
//	 * @param objetoRevision, contiene las propiedades donde van a a ser evaluadas las expresiones(EmpresaParametro)
//	 * @param propiedadInicial
//	 * @param idEmpresaConf, identificador de la lista de Configuración correspondiente
//	 * @param tipoParametro, identificador de tipo de parametro en la busqueda de empresa Parametro
//	 * @return si es String es un error
//	 * 			si es boolean y si es true  se cumple la expresion en la propiedad
//	 * 							si es false no se cumple
//	 * @throws SystemTCEException 
//	 */
//	@SuppressWarnings("unchecked")
//	public Object seEvaluanTodasPropiedad(Object objetoRevision, String propiedadInicial, String idEmpresaConf, String tipoParametro) throws SystemTCEException {
//		//try {
//			 synchronized(this) {
//				log4j.debug("Se busca lista de Parametros Publicacion en Cache...");
//				lsEmpresaParametroDto=ParametrosCache.get(new StringBuilder(idEmpresaConf).append(tipoParametro).toString());
//				if(lsEmpresaParametroDto==null || lsEmpresaParametroDto.isEmpty()){
//					log4j.debug("Se genera lista de parametros por servicio...");
//					resp=empresaParametroService.get(new EmpresaParametroDto(tipoParametro,idEmpresaConf,null));
//					log4j.debug("resp: "+ resp );
//					if(resp.indexOf("code") != -1){
//						return resp;
//					}else{
//						lsEmpresaParametroDto=(List<EmpresaParametroDto>)new Gson().fromJson(resp,
//								new TypeToken<List<EmpresaParametroDto>>(){}.getType());
//						log4j.debug("lsEmpresaParametroDto.size(): "+ lsEmpresaParametroDto.size() );
//						if(lsEmpresaParametroDto != null && lsEmpresaParametroDto.size() > 0){
//							log4j.debug("Se agrega la lista de Parametros Publicacion en cache");
//							ParametrosCache.put(new StringBuilder(idEmpresaConf).append(tipoParametro).toString(), lsEmpresaParametroDto);
//						}
//					}
//				}
//			 }
//			 log4j.debug("lsEmpresaParametroDto="+lsEmpresaParametroDto);
//			return Validador.searchAllProperties(lsEmpresaParametroDto,objetoRevision, propiedadInicial );
//			
//		/*}catch (Exception e) {
//			log4j.error(" ERROR en el validador de propiedades ", e );
//			e.printStackTrace();
//			return UtilsTCE.getJsonMessageGson(null, new MensajeDto(null,null,
//					Mensaje.SERVICE_CODE_002,Mensaje.SERVICE_TYPE_FATAL,
//					Mensaje.MSG_ERROR));
//		}*/
//	}
}
