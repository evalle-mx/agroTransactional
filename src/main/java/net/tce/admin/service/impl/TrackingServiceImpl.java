package net.tce.admin.service.impl;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;

import net.tce.admin.service.TrackingService;
import net.tce.dto.MensajeDto;
import net.tce.dto.TrackingDto;
import net.tce.util.Constante;
import net.tce.util.Mensaje;
import net.tce.util.UtilsTCE;

@Transactional
@Service("trackingService")
public class TrackingServiceImpl implements TrackingService {

Logger log4j = Logger.getLogger( this.getClass());
	
	private String pREAD = "idEmpresaConf";
	private String pGET = "idEmpresaConf";
	
		
	@Inject
	private Gson gson;
	
	@Override
	public String read(TrackingDto dto) {
		log4j.debug("<read()>  ");
		try{
			log4j.debug("<read> " );
			String stJson = gson.toJson(dto);
			JSONObject json = new JSONObject(stJson);
//			String uriService = AppEndPoints.SERV_TRACKING_R;
			
			json = filtros(json, pREAD);		
			
			if(!json.has("code")){
//				if(json.has("idTracking")){
//					log4j.debug("<read> Obteniendo Tracking id: " + json.getString("idTracking") );
//					uriService += json.getString("idTracking");
//				}
//				return getJsonFile(uriService); //Default
				return "[{\"message\":\"Respuesta Read\"}]";
			}
			else{
				return json.toString();
			}
		}catch (Exception e){
			log4j.error("<read> Exception ", e );
			return UtilsTCE.getJsonMessageGson(null, new MensajeDto(null, null,
					Mensaje.SERVICE_CODE_010, Mensaje.SERVICE_TYPE_ERROR,
					Mensaje.MSG_ERROR + e.getMessage()));
		}
	}
	
	@Override
	public String get(TrackingDto dto) {
		log4j.debug("<get()>  ");
		try{
			log4j.debug("<get> " );
			String stJson = gson.toJson(dto);
			JSONObject json = new JSONObject(stJson);
//			String uriService = AppEndPoints.SERV_TRACKING_G;
			
			json = filtros(json, pGET);		
			
			if(!json.has("code")){
//				return getJsonFile(uriService); //Default
				return "[{\"message\":\"Respuesta GET\"}]";
			}
			else{
				return json.toString();
			}
		}catch (Exception e){
			log4j.error("<get> Exception ", e );
			return UtilsTCE.getJsonMessageGson(null, new MensajeDto(null, null,
					Mensaje.SERVICE_CODE_010, Mensaje.SERVICE_TYPE_ERROR,
					Mensaje.MSG_ERROR + e.getMessage()));
		}
	}

	@Override
	public String create(TrackingDto trackDto) throws Exception {
		log4j.debug("<create> ");
		trackDto = filtros(trackDto, Constante.M_FULLTRACK );
		
		int indice = 1;
		
		//Validaciones
		if(trackDto.getCode()==null){
			log4j.debug("<create> Creando Nuevo Tracking");
			Long idPerfilPos = Long.parseLong(trackDto.getIdPerfilPosicion());
			
			Iterator<TrackingDto> itTracks = trackDto.getTracking().iterator();
					//lsDtos.iterator();
			TrackingDto tmpDto;
			Object idTrack = null;
			log4j.debug("<create> Insertando "+ trackDto.getTracking().size() + " Estados (tracking's) con PerfilPosicion: "+idPerfilPos );
			while(itTracks.hasNext()){
				tmpDto = itTracks.next();
				log4j.debug("<create> Insertando trackingEsquemaRol.descripcion: "+tmpDto.getDescripcion() );
				idTrack = indice*2;
				log4j.debug("<create> idTrackingEsquemaRol: "+ idTrack );
				indice++;
			}
			//Se manda el mensaje de regreso
			trackDto = new TrackingDto();
			trackDto.setMessages(UtilsTCE.getJsonMessageGson(trackDto.getMessages(), 
				new MensajeDto("idTrack",
					String.valueOf(idTrack),
						Mensaje.SERVICE_CODE_004,Mensaje.SERVICE_TYPE_INFORMATION,null)));
		}
		else{
			log4j.debug("<create> trackDto.getCode: " + trackDto.getCode());
			log4j.fatal("<create> Existió un error en filtros");
			trackDto.setMessages(UtilsTCE.getJsonMessageGson(trackDto.getMessages(), new MensajeDto(null,null,
					Mensaje.SERVICE_CODE_006,Mensaje.SERVICE_TYPE_FATAL,
					Mensaje.MSG_ERROR)));
		}
		return trackDto.getMessages();
	}
	
	@Override
	public String delete(TrackingDto trackDto) throws Exception {
		log4j.debug("<delete>  ");
		//Validaciones
		trackDto = filtros(trackDto, Constante.M_DELETE);
		if(trackDto.getCode()==null){
			Object idTrack = "-1";
			log4j.debug("<delete> Borrando todos los registros con perPos: " + trackDto.getIdPerfilPosicion() + " idRol: "+ trackDto.getIdRol() );
			
			//TODO

			//Se manda el mensaje de regreso
			trackDto = new TrackingDto();
			trackDto.setMessages(UtilsTCE.getJsonMessageGson(trackDto.getMessages(), 
				new MensajeDto("idTrack",
					String.valueOf(idTrack),
						Mensaje.SERVICE_CODE_004,Mensaje.SERVICE_TYPE_INFORMATION,null)));
		}else{
			log4j.debug("<delete> trackDto.getCode: " + trackDto.getCode());
			log4j.fatal("<create> Existió un error en filtros ");
			trackDto.setMessages(UtilsTCE.getJsonMessageGson(trackDto.getMessages(), new MensajeDto(null,null,
					Mensaje.SERVICE_CODE_006,Mensaje.SERVICE_TYPE_FATAL,
					Mensaje.MSG_ERROR)));
		}
		log4j.debug("<delete> " );
			
		return trackDto.getMessages();
	}
	
	/* filtros AppTransactionalStructured */
	/**
	 * Se aplican los filtros a las propiedades correspondientes del objeto catalogueDto
	 * @param catalogueDto
	 * @return catalogueDto
	 * @throws Exception 
	 */
	private TrackingDto filtros(TrackingDto trackDto, int funcion) throws Exception{
		boolean error=false;
		if(trackDto != null){
			 if(trackDto.getIdEmpresaConf() == null ){
				 log4j.debug("<filtros> idEmpresaConf es null");
				 error=true;
			 }else{
				  if(funcion == Constante.M_FULLTRACK){
						if(trackDto.getIdPerfilPosicion()==null){
							log4j.debug("<filtros> IdPerfilPosicion es null ");
							error = true;
						}
						if(trackDto.getTracking()==null){
							log4j.debug("<filtros> Lista Tracking es null ");
							error = true;
						}
				  }
				  if(funcion == Constante.M_CREATE){
					  if(trackDto.getIdPerfilPosicion() ==null){
							log4j.debug("<filtros> IdPerfilPosicion es null ");
							error = true;
					   }
					  if(trackDto.getIdRol() ==null){
							log4j.debug("<filtros> IdRol es null ");
							error = true;
						}
					  if(trackDto.getDescripcion() ==null){
							log4j.debug("<filtros> Descripcion es null ");
							error = true;
						}
					  if(trackDto.getOrden() ==null){
							log4j.debug("<filtros> Orden es null ");
							error = true;
						}
					}
				  if(funcion == Constante.M_UPDATE){
					  if(trackDto.getIdTracking() ==null){
							log4j.debug("<filtros> IdTracking es null ");
							error = true;
						}
					}
				  if(funcion == Constante.M_DELETE){

//					  if(trackDto.getIdTracking() ==null){
//							log4j.debug("<filtros> IdTracking es null ");
//							error = true;
//						}
					  if(trackDto.getIdPerfilPosicion() ==null){
							log4j.debug("<filtros> IdPerfilPosicion es null ");
							error = true;
					   }
					  if(trackDto.getIdRol() ==null){
							log4j.debug("<filtros> IdRol es null ");
							error = true;
						}
				  }
			 }
		}else{
			log4j.debug("<filtros> trackDto es null ");
			error=true;
		}
		
		log4j.debug("get() -->   error="+error);
		 if(error){
			 trackDto=new TrackingDto();
			 trackDto.setMessage(Mensaje.MSG_ERROR);
			 trackDto.setType(Mensaje.SERVICE_TYPE_FATAL);
			 trackDto.setCode(Mensaje.SERVICE_CODE_006);
		 }
		 /* filtros data-conf */
		 return trackDto;
	}

	@Override
	public String update(TrackingDto trackDto) throws Exception {
		// TODO Auto-generated method stub
		return "[]";
	}
	/**
     * Se equipara el funcionamiento de filtros para la validación de parámetros
     * @param jsonDto
     * @param stParams
     * @return
     */
    protected JSONObject filtros(JSONObject jsonDto, String stParams){
    	log4j.debug("<filtros> jsonDto " + jsonDto );
    	boolean error = false;
    	try{
    		if(stParams != null){	// log4j.debug("<filtros> stParams" + stParams );
        		List<String> lsParam = Arrays.asList(stParams.split("\\s*,\\s*"));
        		String param;
        		for(int x=0;x<lsParam.size();x++){
        			param = lsParam.get(x);
        			if(!jsonDto.has(param)){
        				log4j.error("<filtros> Error: falta "+ param );
        				error=true;
        			}
        		}
        	}
        	if(error){	// log4j.error("<filtros> Faltan parametros, generando error tipo Filtros ");
        		JSONObject jsonRes = new JSONObject();
        		jsonRes.put("code", Mensaje.SERVICE_CODE_001);
        		jsonRes.put("type", Mensaje.SERVICE_TYPE_ERROR);
        		jsonRes.put("message", Mensaje.MSG_ERROR);
        		return jsonRes;
        	}
        	else{
        		return jsonDto;
        	}
    	}catch (Exception e){
    		log4j.fatal("<filtros> Exception en filtro ", e);
    		return jsonDto;
    	}
    }
}
