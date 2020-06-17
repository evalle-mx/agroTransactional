package net.tce.admin.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;

import net.tce.admin.service.EncuestaService;
//import net.tce.dao.EncRespuestaDao;
import net.tce.dto.EncuestaDto;
import net.tce.dto.MensajeDto;
//import net.tce.dto.ReactivoDto;
//import net.tce.model.EncRespuesta;
import net.tce.util.Constante;
import net.tce.util.Mensaje;
import net.tce.util.UtilsTCE;


@Transactional
@Service("encuestaService")
public class EncuestaServiceImpl implements EncuestaService {

	Logger log4j = Logger.getLogger( this.getClass());

	@Inject
	private Gson gson;
	
//	@Autowired
//	EncRespuestaDao encRespuestaDao;
	
	@Override
	public Object get(EncuestaDto encuestaDto) {
		log4j.debug("<get()>  ");
		encuestaDto = filtro(encuestaDto, Constante.M_GET);
		if(encuestaDto.getCode() == null && 
				encuestaDto.getMessages() == null){
			/*List<EncuestaDto> lsEncDto = null;			
			log4j.debug("<get()>  getModo="+encuestaDto.getModo());
			//FIx
			if(encuestaDto.getModo()==null){
				encuestaDto.setModo("1");
			}
			if(encuestaDto.getModo().equals("1")){
				log4j.debug("<get()> Buscando por usuario (Evaluador) " + 
												encuestaDto.getIdUsuario() );
				lsEncDto=encRespuestaDao.getEvaluados(
							new Long(encuestaDto.getIdUsuario()));
				
			}else if(encuestaDto.getModo().equals("2")){
				//Le falta nombre (evaluado)
				lsEncDto=encRespuestaDao.getAllEvaluados();
				
			}else if(encuestaDto.getModo().equals("3")){
				//Le falta nombre (evaluado)
				lsEncDto=encRespuestaDao.getEvaluadosUsuarios(
							Long.parseLong(encuestaDto.getIdEvaluado()));
				
				//Se adicionan las respuestas
				if(lsEncDto != null && lsEncDto.size() > 0){
					Iterator<EncuestaDto> itEncDto= lsEncDto.iterator();
					while(itEncDto.hasNext()){
						EncuestaDto encuestaDto1=itEncDto.next();
						//Solo se adiciónan si esta terminado (Para evitar accesos innecesarios y reducir json)
						if(encuestaDto1.getTerminado().equals("1")){
							encuestaDto1.setRespuestas(encRespuestaDao.getReactivosCorto(
									Long.parseLong(encuestaDto1.getIdEvaluacion())));
						}
						
					}
				}
			}
			
			log4j.debug("<get()> lsEncDto: " + lsEncDto );
			if(lsEncDto != null && lsEncDto.size() > 0){
				return lsEncDto;
			}else{
				encuestaDto.setMessages(UtilsTCE.getJsonMessageGson(null, new MensajeDto(null,null,
											Mensaje.SERVICE_CODE_003,Mensaje.SERVICE_TYPE_WARNING,
											Mensaje.MSG_WARNING)));	
			}//*/	
			return "[{\"message\":\"Respuesta GET\"}]";
		}
		else{
			if(encuestaDto.getMessages() == null)
				encuestaDto.setMessages(
						UtilsTCE.getJsonMessageGson(null, new MensajeDto(null,null,
								encuestaDto.getCode(),
								encuestaDto.getType(),
								encuestaDto.getMessage())));
			else
				encuestaDto.setMessages(UtilsTCE.getJsonMessageGson(
											null, new MensajeDto(null,null,
											Mensaje.SERVICE_CODE_006,Mensaje.SERVICE_TYPE_FATAL,
											Mensaje.MSG_ERROR)));
		}
		return encuestaDto.getMessages();
	}

	@Override
	public String read(EncuestaDto encuestaDto) {
		log4j.debug("<read()>  ");
		encuestaDto = filtro(encuestaDto, Constante.M_READ);
		if(encuestaDto.getCode() == null && 
				encuestaDto.getMessages() == null){
			/*Long idEvaluacion = new Long(encuestaDto.getIdEvaluacion());
			Long idUsuario = new Long(encuestaDto.getIdUsuario());
			log4j.debug("<read> idEvaluacion: "+idEvaluacion + ", idUsuario: " + idUsuario );
			EncuestaDto encDto = encRespuestaDao.readEvaluacion(idEvaluacion, idUsuario);
			if(encDto != null && encDto.getIdEvaluacion()!=null){
				log4j.debug("<read> encDto: " + encDto );
				encuestaDto.setMessages(UtilsTCE.getJsonMessageGson(null,encDto)); 
			}else{
				log4j.debug("<read> No existe relacion evaluacion-usuario. encDto: " + encDto );
				encuestaDto.setMessages(UtilsTCE.getJsonMessageGson(null, new MensajeDto(null,null,
																	Mensaje.SERVICE_CODE_002,
																	Mensaje.SERVICE_TYPE_FATAL,
																	Mensaje.MSG_ERROR)));
			}//*/
			return "[{\"message\":\"Respuesta READ\"}]";
		}else{
			log4j.debug("<read> encuestaDto: " + encuestaDto );
			if(encuestaDto.getMessages() == null)
				encuestaDto.setMessages(
						UtilsTCE.getJsonMessageGson(null, new MensajeDto(null,null,
								encuestaDto.getCode(),
								encuestaDto.getType(),
								encuestaDto.getMessage())));
			else
				encuestaDto.setMessages(UtilsTCE.getJsonMessageGson(
											null, new MensajeDto(null,null,
											Mensaje.SERVICE_CODE_006,Mensaje.SERVICE_TYPE_FATAL,
											Mensaje.MSG_ERROR)));
		}
		return encuestaDto.getMessages();
	}

	@Override
	public String update(EncuestaDto encuestaDto) {
		log4j.debug("<update()>  ");
		filtro(encuestaDto, Constante.M_UPDATE);
		
		log4j.debug("<update()>  getCode="+encuestaDto.getCode()+
				" getIdEvaluacion="+encuestaDto.getIdEvaluacion());
		if(encuestaDto.getCode() ==  null){	
			/*if(encuestaDto.getRespuestas() != null &&
			   encuestaDto.getRespuestas().size() > 0){
				
				log4j.debug("<update()>  getRespuestas_dto="+ encuestaDto.getRespuestas().size());
				
				ReactivoDto reactivoDto;
				EncRespuesta encRespuesta;
				Iterator<EncRespuesta> itRespuesta;
				
				//Se obtiene la lista de reactivos
				HashMap<String, Object> currFilters  = new HashMap<String, Object>();
				currFilters.put("encEvaluacion.idEvaluacion",Long.parseLong(encuestaDto.getIdEvaluacion()));
				List<EncRespuesta>  lsEncRespuesta= encRespuestaDao.getByFilters(currFilters);
				log4j.debug("<update()>  lsEncRespuesta="+lsEncRespuesta);
				
				//iteration para el dto
				Iterator<ReactivoDto> itRespuestaDto = encuestaDto.getRespuestas().iterator();
				while(itRespuestaDto.hasNext()){
					reactivoDto = itRespuestaDto.next();
					
					//log4j.debug("<update()>  getIdRespuesta="+reactivoDto.getIdRespuesta()+
							//" getnResp="+reactivoDto.getnResp());
					
					//solo si hay datos
					if(reactivoDto.getIdRespuesta() != null &&
					   reactivoDto.getnResp() != null){
						
						itRespuesta =lsEncRespuesta.iterator();
						while(itRespuesta.hasNext()){
							encRespuesta=itRespuesta.next();
							
							//log4j.debug("<update()>  pojo_IdRespuesta="+encRespuesta.getIdRespuesta()+
									//" dto_IdRespuesta"+reactivoDto.getIdRespuesta());
							
							//el mismo idRespuesta
							if(encRespuesta.getIdRespuesta() == 
							   Long.parseLong(reactivoDto.getIdRespuesta())){
								
								//log4j.debug("<update()> coinciden pojo_IdRespuesta="+encRespuesta.getIdRespuesta()+
										//" dto_IdRespuesta="+reactivoDto.getIdRespuesta());
								
								//sean diferentes los valores se aplica el update
								if(encRespuesta.getValorNumero() == null ||
								   (encRespuesta.getValorNumero().shortValue() != 
								   Short.parseShort(reactivoDto.getnResp()))){
									//log4j.debug("<update()> se cambia po este valor="+reactivoDto.getnResp());
									
									encRespuesta.setValorNumero(
										Short.parseShort(reactivoDto.getnResp()));
								}
								break;
							}
						}
					}
					
				}
			}
			
			//Se investiga si hay nulos
			//y se manda el estatus: terminado
			// si hay un reactivo sin calificar -> terminado=false
			// si todos estan calificados -> terminado=true
			//encRespuestaDao.flush();
			
			Long num=encRespuestaDao.getRespuestasNulas(
					Long.parseLong(encuestaDto.getIdEvaluacion()));
			//Long num=new Long(0);
			log4j.debug("<update()>  num="+num);
			
			//Si todos los reactivos estan completos cambiar estatus teminado
			//de la tabla de evaluacion a true sino lo contrario
			encRespuestaDao.updateEstatusTerminacion(num > 0 ? false:true, 
											encuestaDto.getIdEvaluacion());
			
			encuestaDto.setMessages(UtilsTCE.getJsonMessageGson(null, 
					new MensajeDto("terminado",
							num > 0 ? "0":"1",
	 						Mensaje.SERVICE_CODE_004,
	 						Mensaje.SERVICE_TYPE_INFORMATION,
	 						null)));
			
			//*/
			return "[{\"message\":\"Respuesta GET\"}]";
		}else{
			encuestaDto.setMessages(UtilsTCE.getJsonMessageGson(
													null, new MensajeDto(
													null,null,
													encuestaDto.getCode(),
													encuestaDto.getType(),
													encuestaDto.getMessage())));
		}
		return encuestaDto.getMessages();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object questionary(EncuestaDto encuestaDto) {
		log4j.debug("<questionary()>  ");
		encuestaDto = filtro(encuestaDto, Constante.M_SEARCH );
		if(encuestaDto.getCode() == null && 
				encuestaDto.getMessages() == null){
			/*Long idEvaluacion = new Long(encuestaDto.getIdEvaluacion());
			log4j.debug("<questionary> idEvaluacion: "+idEvaluacion );
			Object objReactivos = encRespuestaDao.getReactivos(idEvaluacion);
			if(objReactivos!=null){
				List<ReactivoDto> lsReactivos = (List<ReactivoDto>) objReactivos;
				//forzar el reordenamiento aleatorio de la lista
				//Collections.shuffle(lsReactivos);
				
				return lsReactivos;
			}else{
				encuestaDto.setMessages(UtilsTCE.getJsonMessageGson(
						null, new MensajeDto(null,null,
						Mensaje.SERVICE_CODE_006,Mensaje.SERVICE_TYPE_FATAL,
						Mensaje.MSG_ERROR)));
			}//*/
			return "[{\"message\":\"Respuesta GET\"}]";
		}
		else{
			log4j.debug("<questionary> encuestaDto: " + encuestaDto );
			if(encuestaDto.getMessages() == null)
				encuestaDto.setMessages(
						UtilsTCE.getJsonMessageGson(null, new MensajeDto(null,null,
								encuestaDto.getCode(),
								encuestaDto.getType(),
								encuestaDto.getMessage())));
			else
				encuestaDto.setMessages(UtilsTCE.getJsonMessageGson(
											null, new MensajeDto(null,null,
											Mensaje.SERVICE_CODE_006,Mensaje.SERVICE_TYPE_FATAL,
											Mensaje.MSG_ERROR)));
		}
		return encuestaDto.getMessages();
	}

	/**
     * Se equipara el funcionamiento de filtros para la validación de parámetros
     * @param jsonDto
     * @param stParams
     * @return
     */
	private EncuestaDto filtro(EncuestaDto encuestaDto, int funcion){
		 boolean error=false;
		 if(encuestaDto.getIdEmpresaConf() == null ){
			 log4j.debug("<filtro> IdEmpresaConf es requerido ");
			 error=true;
		 }
		 if(!error && (funcion == Constante.M_UPDATE )){
			 if(encuestaDto.getIdEvaluacion() == null){
				 log4j.debug("<filtro> IdEvaluacion es requerido ");
				 error=true;
			 }
		 }
		 if(!error && (funcion == Constante.M_GET )){
			if(encuestaDto.getModo()!= null){
				if(encuestaDto.getModo().equals("1")){
					if(encuestaDto.getIdUsuario() == null){ //idUsuario()
						log4j.debug("<filtro> IdUsuario es requerido ");
						error=true;
					}
				}else  if(encuestaDto.getModo().equals("3")){
					if(encuestaDto.getIdEvaluado() == null){
						log4j.debug("<filtro> IdEvaluado es requerido ");
						error=true;
					}
				}
			 }
		 }
		 if(!error && (funcion == Constante.M_READ )){
			 if(encuestaDto.getIdUsuario() == null){ //idUsuario()
				 log4j.debug("<filtro> IdUsuario es requerido ");
				 error=true;
			 }
			 if(encuestaDto.getIdEvaluacion() == null){
				 log4j.debug("<filtro> IdEvaluacion es requerido ");
				 error=true;
			 }
		 }
		 if(!error && (funcion == Constante.M_SEARCH )){
			 if(encuestaDto.getIdEvaluacion() == null){
				 log4j.debug("<filtro> IdEvaluacion es requerido ");
				 error=true;
			 }
		 }
		 
		 if(error){
			 encuestaDto=new EncuestaDto();
			 encuestaDto.setMessage(Mensaje.MSG_ERROR);
			 encuestaDto.setType(Mensaje.SERVICE_TYPE_ERROR);
			 encuestaDto.setCode(Mensaje.SERVICE_CODE_006);
		 }
		 return encuestaDto;
	}
}
