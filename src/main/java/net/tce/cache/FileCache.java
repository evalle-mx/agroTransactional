package net.tce.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.log4j.Logger;
import net.tce.dto.CacheFileDto;
import net.tce.dto.TipoContenidoDto;

public class FileCache {
	//static Logger log4j = Logger.getLogger("CacheFile");
	static Logger log4j = Logger.getLogger( FileCache.class );

	// cache donde se guardan los archivos referenciados a una carpeta
	private static final ConcurrentHashMap<String, CacheFileDto> chmFiles = new ConcurrentHashMap<String, CacheFileDto>();

	// cache donde se guardan los sujetos(personas o empresas) y las carpetas que solicitaron
	private static final ConcurrentHashMap<String, List<String>> chmHandCheck = new ConcurrentHashMap<String, List<String>>();
	
	// cache donde se guardan los tipos de contenido para filtrar los archivos al ser "subidos" al sistema
	private static  ConcurrentHashMap<String, TipoContenidoDto> chmContenido =null;

	/**
	 * Se inicializa el map chmContenido
	 * @param chmContenido
	 */
	public static void setChmcontenido(ConcurrentHashMap<String, TipoContenidoDto> chmContenido) {
		if(FileCache.chmContenido == null){
			FileCache.chmContenido=chmContenido;
		}
	}

	/**
	 * Regresa el json de los tipo_contenidos, de la clave correspondiente
	 * @param idConf, es el id de la empresa
	 * @return la cadena json o nulo
	 */
	public static  TipoContenidoDto getChmContenido(String clave){
		if(chmContenido.containsKey(clave)){
			return chmContenido.get(clave);
		}else{
			return null;
		}
	}
	
	/**
	 * Verifica si el objeto chmContenido es nulo
	 * @return true ->  null
	 * 		   false -> not null
	 */
	public static boolean isNullChmContenido(){
		if(FileCache.chmContenido == null){
			return true;
		}else{
			return false;
		}
	}
	
	
//	/**
//	 * 
//	 * @param cacheFileDto,, contiene las caracteristicas del archivo
//	 * @param idCarpeta,  el id de la carpeta fisica
//	 */
//	public static void setFiles(CacheFileDto cacheFileDto, String idCarpeta) {
//		chmFiles.put(idCarpeta, cacheFileDto);
//	}

//	/**
//	 * Se obtiene in lista de archivos dependiendo de la carpeta solicitada
//	 * @param idCarpeta , id de la carpeta
//	 * @param solicitante , id del sujeto que quiere ver los archivos
//	 * @return
//	 */
//	public static CacheFileDto getFiles(String idCarpeta) {
//		return chmFiles.get(idCarpeta);
//	}
	
	
	
	/**
	 * Se elimina la carpeta en el map Files
	 * @param idCarpeta, la clave de la carpeta
	 */
	public static void deleteFile(String idCarpeta){
		if(chmFiles.containsKey(idCarpeta)){
			chmFiles.remove(idCarpeta);
		}
	}
	
	/**
	 * Se adiciona un nuevo solicitante al map HandCheck
	 * @param solicitante, id compuesto que hace referencia a una persona o empresa
	 * @param idCarpeta, el id de la carpeta fisica
	 */
	  public static void setHandCheck(String solicitante, String idCarpeta){
		  List<String> lsHandCheck=null;
		  if(chmHandCheck.containsKey(solicitante)){ 
			  lsHandCheck=chmHandCheck.get(solicitante);
			 if(!lsHandCheck.contains(idCarpeta))
				 lsHandCheck.add(idCarpeta);
		  }else{
			  lsHandCheck=new ArrayList<String>();
			  lsHandCheck.add(idCarpeta);
			  chmHandCheck.put(solicitante, lsHandCheck); 
		  }
	}
	 
  /**
   * Regresa una lista con los idCarpetas asignadas al solicitante
   * @param solicitante,  id compuesto que hace referencia a una persona o empresa
   * @return una lista con los idCarpetas, si existen,  de otra manera regresa una lista nula
   */
	public static List<String> getHandCheck(String solicitante){
		List<String> lsHandCheck=null;
		if(chmHandCheck.containsKey(solicitante)){ 
			  lsHandCheck=chmHandCheck.get(solicitante);
		}
		return lsHandCheck;
	}

	/**
	 * Se elimina el solicitante en el Hash Map de HandCheck
	 * @param solicitante, es la clave unica del solicitante
	 */
	public static void deleteHandCheck(String solicitante){
		if(chmHandCheck.containsKey(solicitante)){ 
			chmHandCheck.remove(solicitante);
		}
	}
	
	
	/**
	 * 
	 * @param idCarpeta
	 */
	public static void estatusChmHandCheck() {
		log4j.debug("## chmHandCheck --> "+chmHandCheck);
	}
	
	/**
	 * 
	 * @param idCarpeta
	 */
	public static void estatusChmFiles(String idCarpeta) {
		CacheFileDto cacheFileDto = chmFiles.get(idCarpeta);
		if (cacheFileDto != null) {
			log4j.debug(" ## chmFiles --> idCarpeta="+idCarpeta+
					    " getConcurrencias="+cacheFileDto.getConcurrencias()+
					    " chmArchivos="+cacheFileDto.getChmArchivos());
		}else{
			log4j.debug(" ## chmFiles --> cacheFileDto="+cacheFileDto);
		}
	}
	
}
