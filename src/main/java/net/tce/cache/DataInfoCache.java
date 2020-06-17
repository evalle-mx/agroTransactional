package net.tce.cache;

import java.util.concurrent.ConcurrentHashMap;
import org.apache.log4j.Logger;

public class DataInfoCache {
	static Logger log4j = Logger.getLogger("DataConfCache");

	// map cache donde se guardan los objetos data info
	static final ConcurrentHashMap<String, Object> chmDataInfo = new ConcurrentHashMap<String, Object>();

	/**
	 * Adiciona un nuevo map 
	 * @param nombreDataInfoDto
	 * @param object
	 * @return
	 */
	public static void set(String nombreDataInfoDto, Object object){
		chmDataInfo.put(nombreDataInfoDto, object);
	}
	
	/**
	 *  Obtiene un map dependiendo de su key
	 * @param nombreDataInfoDto
	 * @return
	 */
	public static Object get(String nombreDataInfoDto){
		Object objectResp=null;
		if(chmDataInfo.containsKey(nombreDataInfoDto)){
			objectResp=chmDataInfo.get(nombreDataInfoDto);
		}
		return objectResp;
	}
	
	/*public static void viewchmDataInfo(){
		log4j.debug(" ## chmDataInfo="+chmDataInfo);
	}*/
	
}
