package net.tce.cache;

import java.util.concurrent.ConcurrentHashMap;

import net.tce.dto.AvisoDto;

import org.apache.log4j.Logger;

public class AvisoCache {
	
	static Logger log4j = Logger.getLogger("AvisoCache");
	
	// map cache donde se guardan los objetos AvisoDto
	   static final ConcurrentHashMap<String, AvisoDto> chmAviso = new ConcurrentHashMap<String, AvisoDto>();
	
	   /**
	    * Adiciona un nuevo objeto al map 
	    * @param key
	    * @param avisoDto
	    */
	   public static void put(String key, AvisoDto avisoDto){
		   chmAviso.put(key, avisoDto);
	   }
	   
	   /**
	    * Obtiene el objeto dependiendo de su key 
	    * @param key
	    * @return
	    */
	   public static AvisoDto get(String key ){
		   AvisoDto avisoDto=null;
		   if(chmAviso.containsKey(key)){
			   avisoDto=chmAviso.get(key);
			}
			return avisoDto;
	   }
	   
	   /**
		 * Tiene datos el map
		 * @return
		 */
		public static boolean isEmpty(){
			if(chmAviso.size() == 0){
				return true;
			}else{
				return false;
			}
		}
	   
	   /**
	    * Se muestra los objetos del map
	    */
	   public static void viewChmAviso(){
			log4j.debug(" ## chmAviso="+chmAviso);
		}
}
