package net.tce.cache;

import java.util.concurrent.ConcurrentHashMap;
import net.tce.dto.EmpresaConfDto;
import org.apache.log4j.Logger;

public class EmpresaConfCache {

	static Logger log4j = Logger.getLogger("EmpresaConfCache");

	// map cache donde se guardan los objetos empresa_conf
	static final ConcurrentHashMap<Long, EmpresaConfDto> chmEmpresaConfCache = new ConcurrentHashMap<Long, EmpresaConfDto>();
	
	/**
	 * Adiciona un nuevo map 
	 * @param nombreDataInfoDto
	 * @param object
	 */
	public static void put(Long key, EmpresaConfDto object){
		chmEmpresaConfCache.put(key, object);
	}
	
	/**
	 *  Obtiene un map dependiendo de su key
	 * @param nombreDataInfoDto
	 * @return
	 */
	public static EmpresaConfDto get(Long key ){
		EmpresaConfDto objectResp=null;
		if(chmEmpresaConfCache.containsKey(key)){
			objectResp=chmEmpresaConfCache.get(key);
		}
		return objectResp;
	}
	
	
	
	/**
	 * Tiene datos el map
	 * @return
	 */
	public static boolean isEmpty(){
		if(chmEmpresaConfCache.size() == 0){
			return true;
		}else{
			return false;
		}
	}
	
	public static void viewchmDataInfo(){
		log4j.debug(" ## chmEmpresaConfCache="+chmEmpresaConfCache);
	}
}
