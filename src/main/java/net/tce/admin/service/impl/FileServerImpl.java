package net.tce.admin.service.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
//import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import javax.activation.DataHandler;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.amazonaws.services.s3.model.ObjectMetadata;
//import com.google.common.io.ByteStreams;
import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
import net.tce.admin.service.EmpresaInterfaseService;
import net.tce.admin.service.FileServer;
import net.tce.app.aws.ClientAWSServer;
import net.tce.cache.FileCache;
//import net.tce.dao.CandidatoDao;
//import net.tce.dao.ContenidoDao;
//import net.tce.dao.TipoContenidoArchivoDao;
//import net.tce.dto.CacheFileDto;
//import net.tce.dto.CandidatoDto;
import net.tce.dto.FileDataConfDto;
import net.tce.dto.FileDto;
import net.tce.dto.MensajeDto;
//import net.tce.dto.SolicitanteDto;
import net.tce.exception.FileException;
//import net.tce.model.Contenido;
//import net.tce.model.TipoContenidoArchivo;
import net.tce.util.Constante;
//import net.tce.util.DateUtily;
import net.tce.util.Mensaje;
import net.tce.util.UtilsTCE;
//import net.tce.util.Validador;

/**
 * Clase donde se aplica las politicas de negocio del servicio file
 * @author Goyo
 *
 */
@Transactional
@Service("fileService")
public class FileServerImpl implements FileServer{
	Logger log4j = Logger.getLogger( this.getClass());

//	SolicitanteDto solicitanteDto;
	ConcurrentHashMap<String,FileDto> chmArchivos;
	Field field ;
	
	//Se leen las propiedades del archivo aplications.properties
	private  @Value("${files_repository_temp}")String files_repository_temp;	
	private  @Value("${files.repdocs.awsdocspath}") String awsDocsPath;
	private  @Value("${files_url_repository}")String file_uri;
	private  @Value("${files_repository_local}")boolean repository_is_local;
	private  @Value("${hibernate_manager}")String hibernate_manager;

	
	@Inject
	private ConversionService converter;
		
	@Inject
	private Gson gson;
	
//	@Autowired
//	private ContenidoDao contenidoDao;
	
//	@Autowired
//	private TipoContenidoArchivoDao tipoContenidoArchivoDao;
	
	@Autowired
	private EmpresaInterfaseService empresaInterfaseService;

//	@Autowired
//	private CandidatoDao candidatoDao;
	
	@Autowired
	private ClientAWSServer clientAWSServer;
	
	/**
	 * Se crea un nuevo archivo 
	 * @param fileDto, contiene las propiedades necesarias para ejecutar esta tarea 
	 * @return  un objeto fileDto con el IdContenido creado
	 * @throws IOException 
	 * @throws Exception 
	 */
//	@SuppressWarnings("unchecked")
	public List<MensajeDto> fileUpload(FileDto fileDto) throws Exception   {
		List<MensajeDto> lsMensajeDto=new ArrayList<MensajeDto>();
		fileDto=filtros(fileDto,  Constante.M_CREATE);
		if(fileDto.getCode() == null){
			//si se tiene un error de dataconf para las siguientes propiedades, no se puede persistir
			if(fileDto.getMessages() != null && 
			   (fileDto.getMessages().indexOf("idTipoContenido") != -1 ||
			    fileDto.getMessages().indexOf("idPersona") != -1 ||
			    fileDto.getMessages().indexOf("idEmpresa") != -1 )){
					lsMensajeDto.add(new MensajeDto(null,null,Mensaje.SERVICE_CODE_006,
													Mensaje.SERVICE_TYPE_FATAL,Mensaje.MSG_ERROR));
			}else{
				/*//Se revisa si el tipo de archivo es valido para el tipo de contenido y extension
				TipoContenidoArchivo tipoContenidoArchivo= tipoContenidoArchivoDao.get(fileDto.getTipoArchivo(), 
																	 Long.valueOf(fileDto.getIdTipoContenido()));
				log4j.debug("%%%% getTipoContenido="+fileDto.getIdTipoContenido()+
							" TipoArchivo="+fileDto.getTipoArchivo()+
							" lsTipoContenidoArchivo="+tipoContenidoArchivo);
				if(tipoContenidoArchivo != null){
					//Se analiza si cumple con el numero de archivos permitidos
					MensajeDto mensajeDto=maxMinArchivos(fileDto,tipoContenidoArchivo.getTipoContenido().getMinimo(), 
										 			 tipoContenidoArchivo.getTipoContenido().getMaximo());
					if(!mensajeDto.getCode().equals(Mensaje.SERVICE_CODE_009)){
						//Para errores data-conf
						String erroresDataconf=fileDto.getMessages();	
						Contenido contenido=converter.convert(fileDto,Contenido.class);
						contenido.setFechaCarga(DateUtily.getToday());
						contenido.setTipoContenidoArchivo(tipoContenidoArchivo);
						
						//Se adiciona el contenido, dependiendo de la base de datos, al objeto
						setContenido(contenido, ByteStreams.toByteArray (fileDto.getDhContenido().getInputStream()));
				
						// Se crea el contenido en la BD
						fileDto.setIdContenido(((Long)contenidoDao.create(contenido)).toString());
						contenidoDao.flush();

						// Se obtiene la uri del nuevo archivo						
						//Se obtiene una lista de los archivos que estan en la carpeta fisica
						Object objResp=null;
						try{
							objResp=get(fileDto);
						} catch (Exception e) {
							log4j.error("Error al crear archivo fisico temporal -> get()");
							lsMensajeDto.add(new MensajeDto(null,null,Mensaje.SERVICE_CODE_011,
			 						Mensaje.SERVICE_TYPE_FATAL,Mensaje.SERVICE_MSG_ERROR_UPLOAD));
							e.printStackTrace();
							return lsMensajeDto;
						}  
						log4j.debug(" && IdContenido="+fileDto.getIdContenido()+" objResp="+objResp);
						//si es una lista de archivos
						if(objResp instanceof List<?>){
							boolean estaArchivo=false;
							boolean soloUno=true;
							String urlBase=null;
							Iterator<FileDto> itFileDto=((List<FileDto>)objResp).iterator();
							while(itFileDto.hasNext()){
								FileDto resFileDto=itFileDto.next();
								//solo se necesita una url para obtener el nombre de la carpeta correspondiente
								if(soloUno){
									urlBase=resFileDto.getUrl();
									soloUno=false;
								}
								//Se analiza si existe el archivo en la carpeta
								if(resFileDto.getIdContenido().equals(fileDto.getIdContenido())){
									fileDto=resFileDto;
									estaArchivo=true;
									break;
								}
							}
							log4j.debug("&& esta el archivo en la carpeta? -> "+estaArchivo);
							log4j.debug("&& filePath="+files_repository_temp+" urlBase="+urlBase+
									"  file_uri="+file_uri+" repository_is_local="+repository_is_local);
							//no esta el archivo en la carpeta
							if(!estaArchivo){
								try {
									String pathDirectorio;
									
									//Se obtiene la uri del nuevo archivo									 
									if(repository_is_local){
										//Se pone el nuevo archivo en la carpeta
										pathDirectorio=new StringBuilder(files_repository_temp).append(urlBase.replace(file_uri,"").
														  substring(0,urlBase.replace(file_uri,"").indexOf("/",1))).toString();
									}else{
										//repositorio externa
										pathDirectorio=urlBase.replace(file_uri,"").substring(0,urlBase.replace(file_uri,"").
														indexOf("/",files_repository_temp.length())).toString();
									}
									
									
									log4j.debug(" && fileUpload() -> pathArchivoFisico="+pathDirectorio+
												" getDhContenido="+fileDto.getDhContenido());
									String pathArchivoFisico = dejarArchivoFisico(fileDto.getDhContenido(),fileDto.getIdContenido(),
											 									  fileDto.getTipoArchivo(),pathDirectorio);
									log4j.debug("&& fileUpload() -> pathArchivoFisico="+pathArchivoFisico);
									
									//Se genera el URL del archivo 
									setUrl(pathArchivoFisico, fileDto);
									 
									//Se actualiza el cache
									 //Se obtiene la informacion necesaria del solicitante
									 SolicitanteDto solicitanteDto=getSolicitante(fileDto.getIdPersona(),fileDto.getIdEmpresa(),
																                 null);
									 //Se crea el id del directorio o carpeta para el cache
									 String idDirectorioCache=getDirectorioCache(solicitanteDto.getTipoSujeto(),
															 solicitanteDto.getIdSujeto(),String.valueOf(fileDto.getIdTipoContenido()));
									//se busca en cache la carpeta
									 CacheFileDto cacheFileDto= FileCache.getFiles(idDirectorioCache);
									 log4j.debug(" && fileUpload -> idDirectorioCache="+idDirectorioCache);
									 fileDto.setDhContenido(null);
									 fileDto.setIdPersona(null);
									 fileDto.setIdEmpresa(null);
									 //se actualiza
									 cacheFileDto.getChmArchivos().put(String.valueOf(fileDto.getIdContenido()), fileDto);
									 log4j.debug(" && fileUpload -> getChmArchivos="+cacheFileDto.getChmArchivos());
								} catch (Exception e) {
									log4j.error("Error al crear archivo fisico temporal)",e);
									lsMensajeDto.add(new MensajeDto(null,null,Mensaje.SERVICE_CODE_011,
					 						Mensaje.SERVICE_TYPE_ERROR,Mensaje.SERVICE_MSG_ERROR_UPLOAD));
									return lsMensajeDto;
								}  
							}
							 log4j.debug(" && getUrl="+fileDto.getUrl()+
									     " erroresDataconf="+erroresDataconf);
							//si hay error de Data-conf
							if(erroresDataconf != null  ){
								lsMensajeDto= gson.fromJson(erroresDataconf, new TypeToken<List<MensajeDto>>(){}.getType());
							}
							lsMensajeDto.add(0,new MensajeDto("idContenido",fileDto.getIdContenido(),Mensaje.SERVICE_CODE_004,
															  Mensaje.SERVICE_TYPE_INFORMATION,null));
							lsMensajeDto.add(1,new MensajeDto("url",fileDto.getUrl(),Mensaje.SERVICE_CODE_004,
															  Mensaje.SERVICE_TYPE_INFORMATION,null));
							lsMensajeDto.add(2,mensajeDto);
						}else if(objResp instanceof FileDto){
							fileDto=(FileDto)objResp;
							lsMensajeDto.add(new MensajeDto(null,null,fileDto.getCode(),
															fileDto.getType(),fileDto.getMessage()));
						}
					}else{
						 //Supera em max. permitido
						 lsMensajeDto.add(mensajeDto);
					}
				}
				else{
					//No se encontro el tipo de archivo para el tipo de contenido
					 lsMensajeDto.add(new MensajeDto(null,null,Mensaje.SERVICE_CODE_006,
							 						Mensaje.SERVICE_TYPE_FATAL,Mensaje.MSG_ERROR));
				}//*/
			}
		}else{
			lsMensajeDto.add(new MensajeDto(null,null,fileDto.getCode(),
											  fileDto.getType(),fileDto.getMessage()));
		}
		return  lsMensajeDto;
	}


//	/**
//	 * Se adiciona el archivo en la propiedad contenido, dependiendo de la base de datos
//	 * @param contenido, pojo referido a la tabla contenido
//	 * @param bytesFile, archivo que se va a adicionar
//	 * @throws IllegalArgumentException
//	 * @throws IllegalAccessException
//	 */
//	protected void setContenido(Contenido contenido,byte[] bytesFile) throws IllegalArgumentException, IllegalAccessException{
//		field = UtilsTCE.findUnderlying(contenido.getClass(),"contenido");
//		field.setAccessible(true);
//		field.set(contenido, bytesFile);
//	}
	
	
	
	/** 
	 * Se modifica el contenido correspondiente 
	 * @param fileDto, contiene las propiedades necesarias para ejecutar esta tarea 
	 * @return  
	 * @throws IOException 
	 */
	public  List<MensajeDto> update(FileDto fileDto) throws Exception {
		List<MensajeDto> lsMensajeDto=new ArrayList<MensajeDto>();
		fileDto=filtros(fileDto,Constante.M_UPDATE);
		if(fileDto.getMessage() == null){
//			//Existe en la bd
//			if(contenidoDao.existeContenido(fileDto.getIdContenido())){
//				//Se persiste en la bd, solo descripcion. En este caso no se hizo, el update,
//				//de la forma habitual, ya que fue solo para ahorrar memoria, al no
//				// cargar el archivo blob
//				 //contenidoDao.updateDescripcion(fileDto.getIdContenido(),fileDto.getDescripcion());
//				contenidoDao.updateDescripcion(fileDto.getIdContenido(),fileDto.getFileDescripcion());
//			}else{
				lsMensajeDto.add(new MensajeDto(null,null,Mensaje.SERVICE_CODE_002,
						Mensaje.SERVICE_TYPE_FATAL,Mensaje.MSG_ERROR));
//			}
		}else{
				lsMensajeDto.add(new MensajeDto(null,null,fileDto.getCode(),
								fileDto.getType(),fileDto.getMessage()));
		}
		return lsMensajeDto;
	}
	
	

	/**
	 * Se crea una carpeta fisica para dejar los archivos solicitados y se regresa 
	 * una lista con las referencias de dichos archivos
	 * @param fileDto, contiene las propiedades necesarias para ejecutar esta tarea 
	 * @return  lista de objetos FileDto o un mensaje de error
	 * @throws Exception 
	 */
	@Transactional(readOnly=true)
	public Object get(FileDto fileDto) throws Exception {
		log4j.debug("get....");
		List<FileDto> lsFileDto=null;
		fileDto=filtros(fileDto, Constante.M_GET);
		if(fileDto.getCode() == null && fileDto.getMessages() == null){
			lsFileDto=new  LinkedList<FileDto>();
			log4j.debug("se obtiene solicitante --> getIdPersona="+fileDto.getIdPersona()+
					" getIdEmpresa="+fileDto.getIdEmpresa()+
					" getIdPosicion="+fileDto.getIdPosicion());
			/*//Se obtiene la informacion necesaria del solicitante
			SolicitanteDto solicitanteDto=getSolicitante(fileDto.getIdPersona(),
														 fileDto.getIdEmpresa(),
										                 fileDto.getIdPosicion());
			
			log4j.debug(" %%/&% getIdSujeto="+solicitanteDto.getIdSujeto()+
					    " getSolicitante="+solicitanteDto.getSolicitante()+
					    " HayHandcheck="+solicitanteDto.getHayHandcheck());
			//Se revisa si hubo un Handcheck o el mismo candidato solicita su contenido
			if(solicitanteDto.getHayHandcheck() != null){
				
				//Se crea el id del directorio o carpeta para el cache
				String idDirectorioCache=getDirectorioCache(solicitanteDto.getTipoSujeto(),
										 solicitanteDto.getIdSujeto(),String.valueOf(fileDto.getIdTipoContenido()));
				//se busca en cache la carpeta
				CacheFileDto cacheFileDto= FileCache.getFiles(idDirectorioCache);
				log4j.debug("&&& get ->buscando cache Dir -- > cacheFileDto="+cacheFileDto+
						" solicitante="+solicitanteDto.getSolicitante()+
						" idDirectorioCache="+idDirectorioCache);
				//No existe la carpeta en el cache
				if(cacheFileDto == null){
					log4j.debug("> No existe Cache");
					List<Contenido> lsContenido=contenidoDao.get(fileDto);
					log4j.debug("&& lsContenido="+lsContenido);
					//solo una foto
					if(lsContenido != null && lsContenido.size() > 0 ){
						chmArchivos=new ConcurrentHashMap<String, FileDto>();
						//El directorio se compone de: num_random + _ + idDirectorio + cadenaAleatoria
						String pathDirectorioTemporal=new StringBuilder(String.valueOf(new Random().nextInt(Constante.FILE_SIZE_RANDOM))).
													append("_"). append(idDirectorioCache).append(UtilsTCE.getCadenaAlfanumAleatoria(
													Constante.FILE_SIZE_RANDOM_CADENA)).toString();
						String pathdirectorio=new StringBuilder(files_repository_temp).append(pathDirectorioTemporal).toString();
						//si es local el repositorio
						if(repository_is_local){
							//se crea el directorio
							File flDirectorio=new File(pathdirectorio);
							flDirectorio.mkdir();
						}
						
						Iterator<Contenido>	itContenido=lsContenido.iterator();
						log4j.debug("&& pathdirectorio="+pathdirectorio+" pathDirectorioFisico="+pathDirectorioTemporal);
						while(itContenido.hasNext()){
							Contenido contenido=itContenido.next();
							//Se deja fisicamente el archivo
							String pathArchivoFisico=dejarArchivoFisico(contenido.getContenido(),
																String.valueOf(contenido.getIdContenido()),
																contenido.getTipoContenidoArchivo().getTipoArchivo().getExtension(),
																pathdirectorio);
							fileDto=converter.convert(contenido,FileDto.class);
							
							//Se genera el URL del archivo 
							setUrl(pathArchivoFisico, fileDto);
							
							fileDto.setIdPersona(null);
							fileDto.setIdEmpresa(null);
							fileDto.setDhContenido(null);
							lsFileDto.add(fileDto);
							//Para el cache
							chmArchivos.put(String.valueOf(fileDto.getIdContenido()), fileDto);
						}
						//Se guarda en cache
					    cacheFileDto=new CacheFileDto();
						cacheFileDto.setConcurrencias(1);
						cacheFileDto.setCarpetaFisico(pathDirectorioTemporal);
						cacheFileDto.setChmArchivos(chmArchivos);
						//Se guarda el id del solicitante o handcheck, y el idDirectorioCache
						FileCache.setHandCheck(solicitanteDto.getSolicitante(), idDirectorioCache);
						//Se guarda el objeto cacheFileDto y el idDirectorioCache
						FileCache.setFiles(cacheFileDto, idDirectorioCache);
					}else{
						log4j.debug("No existen contenidos");
					}
				}else{
					log4j.debug("get -> Se obtiene de cache el directorio -> lsHandCheck");
					List<String> lsHandCheck=FileCache.getHandCheck(solicitanteDto.getSolicitante());
					log4j.debug("&&  getSolicitante="+solicitanteDto.getSolicitante()+" lsHandCheck="+lsHandCheck);
					//Se adiciona, si no esta en la lista
					if(lsHandCheck == null || !lsHandCheck.contains(idDirectorioCache)){
						//Se guarda el id del solicitante o handcheck, y el idDirectorioCache
						FileCache.setHandCheck(solicitanteDto.getSolicitante(), idDirectorioCache);
						cacheFileDto.setConcurrencias(cacheFileDto.getConcurrencias()+1);
					}
					//Se regresa la lista de objetos FileDto,  del cache
					lsFileDto= new ArrayList<FileDto>(cacheFileDto.getChmArchivos().values());
				}
				//revisamos cache para el directorio correspondiente y de solicitantes
				FileCache.estatusChmFiles(idDirectorioCache);
				FileCache.estatusChmHandCheck();
			}else{
				//idPosicion no existe o no tiene relacion con idPersona o idEmpresa 
				if(solicitanteDto.getHayHandcheck() == null){
					fileDto=new FileDto();
					fileDto.setCode(Mensaje.SERVICE_CODE_006);
					fileDto.setMessage(Mensaje.MSG_ERROR);
					fileDto.setType(Mensaje.SERVICE_TYPE_FATAL);
					return fileDto;	
				}else{
					//No existe handCheck para la posicion correspondiente
					return lsFileDto;
				}
			}//*/
			
		}else{
			if(fileDto.getCode() == null){
				fileDto=new FileDto();
				fileDto.setCode(Mensaje.SERVICE_CODE_006);
				fileDto.setMessage(Mensaje.MSG_ERROR);
				fileDto.setType(Mensaje.SERVICE_TYPE_FATAL);
			}
			return fileDto;
		}
		return lsFileDto;
	}

	/**
	 * 
	 * @param pathArchivoFisico
	 * @param fileDto
	 */
	void setUrl(String pathArchivoFisico,FileDto fileDto){
		//Se crean los URI's de los archivos, que estan en el repositorio
		if(repository_is_local){
			//el repositorio es local
			fileDto.setUrl(new StringBuilder(file_uri).append(pathArchivoFisico.replace(files_repository_temp,"")).toString());
		}else{
			//el repositorio es externo (AWS)
			fileDto.setUrl(new StringBuilder(file_uri).append(pathArchivoFisico).toString());
		}
		 
		log4j.debug(" && nuevo archivo getUrl="+fileDto.getUrl());
	}
	
	
	/**
	 * Se borran los archivos de la persona dada
	 * @param fileDto
	 * @return
	 * @throws Exception 
	 */
	 public String deleteByPerson(FileDto fileDto) throws Exception{
//		 List<Contenido> lsContenido=contenidoDao.getAll(fileDto);
		 String resp=Mensaje.SERVICE_MSG_OK_JSON;
//		 if(lsContenido != null && lsContenido.size() > 0){
//			 Iterator<Contenido> itContenido=lsContenido.iterator();
//			 while(itContenido.hasNext()){
//				 Contenido contenido=itContenido.next();
//				 fileDto.setIdTipoContenido(String.valueOf(contenido.
//						 					getTipoContenidoArchivo().getIdTipoContenidoArchivo()));
//				 fileDto.setIdContenido(String.valueOf(contenido.getIdContenido()));
//				 resp= delete(fileDto) ;
//				 //si no hay texto:idContenido, en la respuesta, entonces no se elimino el archivo
//				 if(resp.indexOf("idContenido") == -1 ){
//					 log4j.error("Error al eliminar el archivo fisicamente, cuyo id es:"+fileDto.getIdContenido());
//					 break;
//				 }
//			 } 
//		 }		 
		 return resp;
	 }
	
	/**
	 * Se elimina el contenido correspondiente
	 * @param fileDto
	 * @return un objeto fileDto
	 * @throws Exception 
	 */
	public String delete(FileDto fileDto) throws Exception {
		log4j.debug("&&& getIdContenido="+fileDto.getIdContenido()+" getIdEmpresaConf="+fileDto.getIdEmpresaConf());
		fileDto=filtros(fileDto, Constante.M_DELETE);
		if(fileDto.getCode() == null && fileDto.getMessages() == null){
//			Contenido contenido=contenidoDao.read(Long.valueOf(fileDto.getIdContenido()));
//			if(contenido != null){
//				//se asigna el correspondiente valor de idPersona o idEmpresa
//				if(contenido.getEmpresa() != null)
//					fileDto.setIdEmpresa(String.valueOf(contenido.getEmpresa().getIdEmpresa()));
//				if(contenido.getPersona() != null)	
//					fileDto.setIdPersona(String.valueOf(contenido.getPersona().getIdPersona()));
//			
//				//Se obtiene la informacion necesaria del solicitante
//				SolicitanteDto solicitanteDto=getSolicitante(fileDto.getIdPersona(),fileDto.getIdEmpresa(),
//											                 null);
//				
//				//Se crea el id del directorio o carpeta para el cache
//				String idDirectorioCache=getDirectorioCache(solicitanteDto.getTipoSujeto(),
//										 solicitanteDto.getIdSujeto(),String.valueOf(contenido.getTipoContenidoArchivo().
//										 getTipoContenido().getIdTipoContenido()));
//				log4j.debug("&&& idDirectorioCache="+idDirectorioCache);
//				
//				//se busca en cache la carpeta
//				CacheFileDto cacheFileDto= FileCache.getFiles(idDirectorioCache);
//				log4j.debug("&&& cacheFileDto="+cacheFileDto);
//				//existe en cache la carpeta
//				if(cacheFileDto != null){
//					//Se obtiene la ubicacion de la carpeta
//					FileDto chFileDto=(FileDto)cacheFileDto.getChmArchivos().get(fileDto.getIdContenido());
//					log4j.debug("&&& getUrl="+chFileDto.getUrl());
//					//Si es local el repositorio
//					if(repository_is_local){
//						log4j.debug("&&& se borra localmnete ");
//						//se elimina el archivo fisico
//						UtilsTCE.deleteFolderoFileLocal(chFileDto.getUrl().replace(file_uri, files_repository_temp), 
//														idDirectorioCache);
//					}else{
//						//Se elimina el archivo
//						deleteFileRepositoryExternal( chFileDto.getUrl(),  idDirectorioCache,true);
//					}
//						
//					//se elimina de cache
//					cacheFileDto.getChmArchivos().remove(fileDto.getIdContenido());
//				}else{
//					//No se hace nada
//				}	
//				//Se elimina de la base de datos
//				contenidoDao.delete(contenido);
				fileDto.setMessages(UtilsTCE.getJsonMessageGson(null, 
									new MensajeDto("idContenido",fileDto.getIdContenido(),
									Mensaje.SERVICE_CODE_007,Mensaje.SERVICE_TYPE_INFORMATION,null)));
//				
//			}else{
//				fileDto.setMessages(UtilsTCE.getJsonMessageGson(null, new MensajeDto(null,null,
//											Mensaje.SERVICE_CODE_002,Mensaje.SERVICE_TYPE_FATAL,
//											Mensaje.MSG_ERROR)));
//			}
		}else{
			if(fileDto.getMessages() == null)
				fileDto.setMessages(UtilsTCE.getJsonMessageGson(null, new MensajeDto(null,null,
						fileDto.getCode(),fileDto.getType(),fileDto.getMessage())));
			else
				fileDto.setMessages(UtilsTCE.getJsonMessageGson(null, new MensajeDto(null,null,
											Mensaje.SERVICE_CODE_006,Mensaje.SERVICE_TYPE_FATAL,
											Mensaje.MSG_ERROR)));
		}
		return fileDto.getMessages();
	}
	
	/**
	 * Se elimina el archivo en el repositorio externo
	 * @param uri, el url del archivo
	 * @param idDirectorioCache, el id del archivo en el cache
	 * @param fileOrFolder, true si es archivo
	 * 						false si es folder 
	 */
	public void deleteFileRepositoryExternal(String uri, String idDirectorioCache, boolean fileOrFolder){
		//si es folder o archivo
		if(fileOrFolder){
			//Se elimina el archivo
			clientAWSServer.deleteObject(uri.replace(file_uri, ""));
		}else{
			//Se elimina la carpeta
			clientAWSServer.deleteObjectsInFolder(uri);
		}
		//Se borra del cache
		FileCache.deleteFile(idDirectorioCache); 
	}

	/**
	 * Se eliminan los archivos en el repositorio
	 */
	public void deleteAllRepository() {
		log4j.debug("deleteAllRepository() ->  files_repository_temp="+files_repository_temp+" repository_is_local="+repository_is_local);
		
		//Si es local el repositorio
		if(repository_is_local){
			UtilsTCE.deleteFolderoFileLocal(files_repository_temp, null);
		}else{
			//Se eliminan los archivos
			clientAWSServer.deleteObjectsInFolder(files_repository_temp);
			clientAWSServer.deleteObjectsInFolder(awsDocsPath);
		}	
	}
	
	
//	/**
//	 * Regresa un objeto dataConf referida al servicio file
//	 * @param fileDto, objeto data-conf correspondiente
//	 * @return fileDto
//	 * @throws Exception 
//	 */
//	public FileDataConfDto dataConf(FileDataConfDto fileDataConfDto) throws Exception{
//		 log4j.debug(" $$ dataConf() ");
//		 if(fileDataConfDto == null || fileDataConfDto.getIdEmpresaConf() == null){
//			 	fileDataConfDto=new FileDataConfDto();
//			 	fileDataConfDto.setMessage(Mensaje.MSG_ERROR);
//			 	fileDataConfDto.setType(Mensaje.SERVICE_TYPE_FATAL);
//			 	fileDataConfDto.setCode(Mensaje.SERVICE_CODE_002);
//		 }else{
//			 /*String idTipoContenido=fileDataConfDto.getIdTipoContenido();
//			 log4j.debug(" $$ dataConf() -> idTipoContenido="+idTipoContenido);*/
//			 fileDataConfDto=(FileDataConfDto)empresaInterfaseService.dataConf(
//							  fileDataConfDto.getIdEmpresaConf(),"Contenido",new FileDataConfDto());
//			 log4j.debug(" $$ dataConf() -> fileDataConfDto="+fileDataConfDto);
//			if(fileDataConfDto == null){
//				fileDataConfDto=new FileDataConfDto();
//			 	fileDataConfDto.setMessage(Mensaje.SERVICE_MSG_ERROR_DATACONF);
//			 	fileDataConfDto.setType(Mensaje.SERVICE_TYPE_FATAL);
//			 	fileDataConfDto.setCode(Mensaje.SERVICE_CODE_002);
//			}else{
//				 log4j.debug(" $$ dataConf() -> getContenido="+fileDataConfDto.getContenido());
//				fileDataConfDto.setContenido(fileDataConfDto.getContenido().substring(fileDataConfDto.getContenido().
//						 					  indexOf("["), fileDataConfDto.getContenido().indexOf("]")+1));
//				fileDataConfDto.setIdTipoContenidoArchivo(null);
//				fileDataConfDto.setFechaCarga(null);
//			}
//		 }
//		return fileDataConfDto;
//	}
	
//	/**
//	 * Se aplica los filtros de Dataconf para los valores de las propiedades del objeto fileDto
//	 * @param fileDto, objeto a filtrar correspondiente
//	 * @return fileDto
//	 * @throws Exception
//	 */
//	private FileDto filtrosDataConf(FileDto fileDto) throws Exception {
//		//Se asigna valores del objeto FileDto a un objeto FileDataConfDto
//		FileDataConfDto fileDCDto=new FileDataConfDto();
//		fileDCDto.setIdContenido(fileDto.getIdContenido());
//		fileDCDto.setIdEmpresa(fileDto.getIdEmpresa());
//		fileDCDto.setIdPersona(fileDto.getIdPersona());
//		fileDCDto.setDescripcion(fileDto.getFileDescripcion());
//		
//		//Se crea otro objeto FileDataConfDto donde se asigna los filtros data-conf
//		FileDataConfDto fileDataConfDto=new FileDataConfDto();
//		fileDataConfDto.setIdEmpresaConf(fileDto.getIdEmpresaConf());
//		fileDataConfDto.setIdTipoContenido(fileDto.getIdTipoContenido());
//		fileDataConfDto=dataConf(fileDataConfDto);
//		
//		//si no hay error
//		if(fileDataConfDto.getCode() == null){
//			//Se filtra el objeto
//			fileDCDto=(FileDataConfDto)Validador.filtrosDataConf(fileDataConfDto,fileDCDto);
//			fileDto.setIdContenido(fileDCDto.getIdContenido());
//			fileDto.setIdEmpresa(fileDCDto.getIdEmpresa());
//			fileDto.setIdPersona(fileDCDto.getIdPersona());
//			fileDto.setFileDescripcion(fileDCDto.getDescripcion());
//			fileDto.setMessages(fileDCDto.getMessages());
//		}else{
//			fileDto=new FileDto();
//			fileDto.setMessage(fileDataConfDto.getMessage());
//			fileDto.setType(fileDataConfDto.getType());
//			fileDto.setCode(fileDataConfDto.getCode());
//		}
//		return fileDto;
//	}
	
//	/**
//	 * Proporciona las caracteristicas del solicitante investigando si existe un handcheck
//	 * @param idPersona, el id de la persona candidato
//	 * @param idEmpresa, el id de la empresa candidato
//	 * @param idPosicion, el id de la posicion
//	 * @return
//	 */
//	public SolicitanteDto getSolicitante(String idPersona,String idEmpresa,String idPosicion){
//		solicitanteDto=new SolicitanteDto();
//		//Se investiga si es persona o empresa
//		if(idPersona != null){
//			solicitanteDto.setTipoSujeto(String.valueOf(Constante.PERSONA));
//			solicitanteDto.setIdSujeto(idPersona);
//		}else if(idEmpresa != null){
//			solicitanteDto.setTipoSujeto(String.valueOf(Constante.EMPRESA));
//			solicitanteDto.setIdSujeto(idEmpresa);
//		}
//		//Se obtiene el solicitante
//		//Si hay handcheck
//		if(idPosicion != null){
////			CandidatoDto candidatoDto=candidatoDao.getHandCheck(idPosicion, idEmpresa, idPersona);
////			log4j.debug("se verifica handcheck --> candidatoDto="+candidatoDto);
////			if(candidatoDto != null){
////				//Hay handcheck
////				if(candidatoDto.getHandshake()){
////					//se crea el id del solicitante
////					if(candidatoDto.getIdPersona() != null){
////						solicitanteDto.setSolicitante(new StringBuilder(String.valueOf(Constante.PERSONA)).
////													  append(candidatoDto.getIdPersona()).toString());
////					}else if(candidatoDto.getIdEmpresa() != null){
////						solicitanteDto.setSolicitante(new StringBuilder(String.valueOf(Constante.EMPRESA)).
////													  append(candidatoDto.getIdEmpresa() ).toString());
////					}					
////				}	
////				solicitanteDto.setHayHandcheck(candidatoDto.getHandshake());
////			}else{
////				//No debe pasar
////				solicitanteDto.setHayHandcheck(null);
////			}			
//		}else{
//			//se crea el id del solicitante= tipoSujeto + isSujeto
//			solicitanteDto.setSolicitante(new StringBuilder(solicitanteDto.getTipoSujeto()).
//											append(solicitanteDto.getIdSujeto()).toString());
//			solicitanteDto.setHayHandcheck(true);
//		}
//		return solicitanteDto;
//	}
	
	/**
	 * Deja el archivo fisico en la  carpeta correspondiente
	 * @param isArchivo, archivo fisico
	 * @param idContenido, es el id del contenido, en la bd
	 * @param extension, la extencion 
	 * @param pathDirectorio, la ubicación fisica de la carpeta donde se va adejar el archivo
	 * @return
	 * @throws SQLException 
	 * @throws FileException 
	 * @throws IOException 
	 */
	protected String dejarArchivoFisico(Object archivoBytes,String idContenido,
						String extension,String pathDirectorio) throws Exception {
		OutputStream out =null;
		StringBuilder pathArchivoFisico=null;
		InputStream inputStream=null;
		File file;
		try {
			log4j.debug("dejarArchivoFisico() 0  -> archivoBytes="+archivoBytes.getClass().getName());
			//Se compone de: random + _ + cadenaAleatoria + IdContenido + . + tipoArchivo
			pathArchivoFisico= new StringBuilder(String.valueOf(new Random().nextInt(Constante.FILE_SIZE_RANDOM))).
								append("_").append(UtilsTCE.getCadenaAlfanumAleatoria(
								Constante.FILE_SIZE_RANDOM_CADENA)).append(idContenido).append(".").append(extension);
			//Se revisa el tipo de archivo
			//Para oracle
			if(archivoBytes instanceof Blob){
				inputStream=((Blob)archivoBytes).getBinaryStream();
			//Para el servicio
			}else if(archivoBytes instanceof DataHandler){
				inputStream=((DataHandler)archivoBytes).getInputStream();
			//Para Postgresql
			}else if(archivoBytes instanceof byte[]){			
				 inputStream = new ByteArrayInputStream(((byte[])archivoBytes)); 
				
			}
			log4j.debug("dejarArchivoFisico() 1--> pathArchivoFisico(FileName)= "+pathArchivoFisico.toString()+
					" pathDirectorio(Folder)="+pathDirectorio+" repository_is_local="+repository_is_local+" inputStream="+inputStream);
			//si es repositorio local
			if(repository_is_local){
				int read = 0;
				byte[] bytes = new byte[1024];
				pathArchivoFisico.insert(0, pathDirectorio.concat((Constante.OS.indexOf("win") != -1) ? "\\":"/"));
				file=new File(pathArchivoFisico.toString());
				//Se especifica el path del directorio
				out = new FileOutputStream(file); 
			    //se escribe al disco
				while ((read = inputStream.read(bytes)) != -1) {
					out.write(bytes, 0, read);
				}			
			}else{
				//si es repositorio externo AWS
				pathArchivoFisico.insert(0, pathDirectorio.concat("/"));		
				log4j.debug("dejarArchivoFisico() -> pathArchivoFisico[AWS]="+pathArchivoFisico);
				clientAWSServer.putObject(pathArchivoFisico.toString(),  inputStream,new ObjectMetadata());
			}
			log4j.debug("dejarArchivoFisico() 2--> pathArchivoFisico="+pathArchivoFisico.toString());
			
					
		}finally{
			if(inputStream != null){
				try {
					inputStream.close();
				} catch (IOException e) {
					throw new FileException(e);
				}
			}
			if(out != null){
				try {
					if(repository_is_local){
						out.flush();
						out.close();
					}					
				} catch (IOException e) {
					throw new FileException(e);
				}
			}
		}
		return pathArchivoFisico.toString();
	}
	
	
	/**
	 * Analiza la cantidad de archivos permitidos
	 * @param fileDto, contiene las propiedades del archivo
	 * @param minimo, de archivos permitidos
	 * @param maximo,de archivos permitidos
	 * @return un mensaje, si es ok paso los filtros
	 */
	protected MensajeDto maxMinArchivos(FileDto fileDto,Integer minimo, Integer maximo ){
		MensajeDto mensajeDto=new MensajeDto();
		mensajeDto.setMessage(Mensaje.MSG_OK_CUOTA);
		mensajeDto.setCode(Mensaje.SERVICE_CODE_010);
		mensajeDto.setType(Mensaje.SERVICE_TYPE_INFORMATION);
		
		int cantidad=0;
		//Se investiga cuantos archivos hay en la tabla contenidos para el correspondiente sujeto
//		List<Contenido> lsContenido=contenidoDao.get(fileDto);
//		if(lsContenido != null && lsContenido.size() > 0 ){
//			cantidad=lsContenido.size()+1;
//		}else{
//			cantidad=1;
//		}
		//Se analiza las cuotas
		if(minimo == null && maximo != null){
			if(cantidad > maximo.intValue() ){
				mensajeDto.setMessage(Mensaje.MSG_ERROR_SUPERA_MAX);
				mensajeDto.setCode(Mensaje.SERVICE_CODE_009);
				mensajeDto.setType(Mensaje.SERVICE_TYPE_ERROR);
			}else{
				mensajeDto.setName("contador");
				mensajeDto.setValue(new StringBuilder(String.valueOf(cantidad)).append("/").
									append(maximo.toString()).toString());
				mensajeDto.setCode(Mensaje.SERVICE_CODE_010);
				mensajeDto.setType(Mensaje.SERVICE_TYPE_INFORMATION);
				mensajeDto.setMessage(null);
			}
		}else if(minimo != null && maximo == null){
			if(cantidad < minimo.intValue() ){
				mensajeDto.setMessage(new StringBuilder("Se necesita la cantidad de ").
								append(String.valueOf(minimo.intValue() - cantidad)).
								append(" archivo(s) para que se cumpla la cuota mínima").toString());
				mensajeDto.setCode(Mensaje.SERVICE_CODE_008);
				mensajeDto.setType(Mensaje.SERVICE_TYPE_WARNING);
			}
		}else if(minimo != null && maximo != null){
			if(cantidad < minimo.intValue() ){
				mensajeDto.setMessage(new StringBuilder("Se necesita la cantidad de ").
							append(String.valueOf(minimo.intValue() - cantidad)).
							append(" archivo(s) para que se cumpla la cuota mínima").toString());
				mensajeDto.setCode(Mensaje.SERVICE_CODE_008);
				mensajeDto.setType(Mensaje.SERVICE_TYPE_WARNING);
			}else if( cantidad > maximo.intValue() ){
				mensajeDto.setMessage(Mensaje.MSG_ERROR_SUPERA_MAX);
				mensajeDto.setCode(Mensaje.SERVICE_CODE_009);
				mensajeDto.setType(Mensaje.SERVICE_TYPE_ERROR);
			}else if( cantidad <= maximo.intValue() ){
				mensajeDto.setName("contador");
				mensajeDto.setValue(String.valueOf(cantidad).concat("/").concat(maximo.toString()));
				mensajeDto.setCode(Mensaje.SERVICE_CODE_010);
				mensajeDto.setType(Mensaje.SERVICE_TYPE_INFORMATION);
				mensajeDto.setMessage(null);
			}
		}
		
		log4j.debug("&& maxMinArchivos --> cantidad="+cantidad+
				     " minimo="+minimo+" Maximo="+maximo+" mensaje="+mensajeDto.getMessage());
		return mensajeDto;
	}

	/**
	 * Crea un id para la carpeta fisica, el cual se va a guardar en el cacheFile
	 * @param tipoSujeto
	 * @param idSujeto
	 * @param idTipoContenido
	 * @return el id de la carpeta
	 */
	protected String getDirectorioCache(String tipoSujeto, String idSujeto, String idTipoContenido){
		return new StringBuilder(tipoSujeto).append(idSujeto).append(idTipoContenido).toString();
	}
	
	/**
	 * Se aplican criterios de negocio para analizar si es viable la ejecucion correspondiente
	 * @param fileDto
	 * @param funcion
	 * @return
	 * @throws Exception 
	 */
	private FileDto filtros(FileDto fileDto, int funcion) throws Exception {
		boolean error=false;
		if(fileDto != null){
			//update y delete
			if(funcion == Constante.M_DELETE || 
			   funcion == Constante.M_UPDATE){
				if(fileDto.getIdContenido() == null){
					 error=true;
				}
				//update
				if(!error && funcion == Constante.M_UPDATE &&
					fileDto.getFileDescripcion() == null){
					 error=true;
				}
			}
			//update
			if(!error && funcion == Constante.M_UPDATE){
				//si hay cambio de archivo fisico se necesita la extension
				if(fileDto.getDhContenido() != null && 
				   fileDto.getTipoArchivo() == null ){
					 error=true;
				}
				//No se puede hacer cambio de tipo de archivo sino se hace lo correspondiente al archivo fisico
				if(!error && fileDto.getDhContenido() == null && 
				   fileDto.getTipoArchivo() != null ){
					 fileDto.setTipoArchivo(null);
				}
			}
			//upload y get
			if(!error && (funcion ==  Constante.M_CREATE || 
				funcion == Constante.M_GET)){
				 int solouno=0;
				 if(fileDto.getIdEmpresa() != null){
					 solouno++;
				 }
				 if(fileDto.getIdPersona() != null){
					 solouno++;
				 }
				
				 //almenos debe contener solo id de empresa o de persona
				 if(solouno != 1){
					 error=true;
				 }
				 //upload
				 if(funcion == Constante.M_CREATE){
					//obligatorios
					if(!error && (fileDto.getDhContenido() == null || 
					   fileDto.getTipoArchivo() == null ||
					   fileDto.getIdTipoContenido() == null)){
						error=true;
					}
					//la propiedad archivo debe ser nulo 
					if(!error && fileDto.getIdContenido() != null){
						fileDto.setIdContenido(null);
					}
				 }
				 //get y dataConf
				 if(!error && funcion == Constante.M_GET ){
					 if( fileDto.getIdTipoContenido() == null){
						 error=true;
					 }
				 }
			}
			//todos
			 if(!error && fileDto.getIdEmpresaConf() == null){
				 error=true;
			 }
		}else{
			 error=true;
		}
		 log4j.debug("&& filtros() -> error="+error);
		 if(error){
			 fileDto=new FileDto();
			 fileDto.setMessage(Mensaje.MSG_ERROR);
			 fileDto.setType(Mensaje.SERVICE_TYPE_FATAL);
			 fileDto.setCode(Mensaje.SERVICE_CODE_006);
		 }
		 return fileDto;
	}

	


	
}
