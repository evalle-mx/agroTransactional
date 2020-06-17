package net.tce.cache;

import java.util.HashMap;
import java.util.Iterator;

import org.apache.log4j.Logger;

public class PoliticaValorElementoCache {
	static Logger log4j = Logger.getLogger("ElementoCache");
	// map cache donde se guardan los objetos data info
	static final HashMap<String,Float> hmPoliticaValorElemento=new HashMap<String, Float>();

	/**
	 * Adiciona un nuevo valor al map 
	 * @param key, compuesto por: idPerfil + _ +idArea + _ + nombreElemento + _ + idTipoElemento
	 * @param valor del objeto
	 * @return
	 */
	public static void put(String key, Float valor){
		hmPoliticaValorElemento.put(key, valor);
	}
	
	/**
	 * Obtiene un valor dependiendo de su key
	 * @param nombreDataInfoDto
	 * @return lista de objetos EmpresaParametroDto
	 */
	public static Float get(String key){
		Float valor=null;
		if(hmPoliticaValorElemento.containsKey(key)){
			valor=(Float)hmPoliticaValorElemento.get(key);
		}
		return valor;
	}
	
	/**
	 * Verifica si la llave se encuentra en el map
	 * @param key es la llave
	 * @return  true -> se encuentra
	 * 			false -> no  se encuentra
	 */
	public static boolean containsKey(String key){
		return hmPoliticaValorElemento.containsKey(key);
	}

	/**
	 * Verifica si la cadena se encuentra en alguna de las llaves
	 * @param cad, la cadena a analizar
	 * @return true si se encuentra
	 * 		   false si no se encuentra
	 */
	 public static boolean indexOfKey(String cad){
		boolean resp=false;
		Iterator<String> itKey =hmPoliticaValorElemento.keySet().iterator();
		while(itKey.hasNext()){
			if(itKey.next().indexOf(cad) > -1){
				resp=true;
				break;
			}
		}
		return resp;
	}
	
	 /**
	 * Elimina la politica_valor del cache dependiendo de la llave
	 * @param cad, la cadena a eliminar
	 */
	 public static void deleteIndexOfKey(String cad){
		Iterator<String> itKey =hmPoliticaValorElemento.keySet().iterator();
		while(itKey.hasNext()){
			String key=itKey.next();
			if(key.indexOf(cad) > -1){
				log4j.debug("Se elimina del cache_politica_valor="+key);
				itKey.remove();
			}
		}
	}
	 
	 
	/**
	 * 
	 */
	public static void viewhmPoliticaValorElemento(){
		log4j.info(" ## hmPoliticaValorElemento="+hmPoliticaValorElemento);
	}
}
