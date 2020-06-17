package net.tce.cache;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import net.tce.dto.EmpresaParametroDto;

import org.apache.log4j.Logger;

public class ParametrosCache {

	static Logger log4j = Logger.getLogger("EmpresaParametroCache");
	// map cache donde se guardan los objetos data info
	static final ConcurrentHashMap<String, List<EmpresaParametroDto>> chmParametros = new ConcurrentHashMap<String, List<EmpresaParametroDto>>();

	// map cache donde se guardan los parametros generales
	static final ConcurrentHashMap<String, String> chmGeneral = new ConcurrentHashMap<String, String>();
	
	/**
	 * Adiciona un nuevo map 
	 * @param key, compuesto por: idEmpresaConf + idTipoParametro
	 * @param lista de objetos EmpresaParametroDto
	 * @return
	 */
	public static void put(String key, List<EmpresaParametroDto> lista){
		chmParametros.put(key, lista);
	}
	
	/**
	 *  Obtiene un map dependiendo de su key
	 * @param key, compuesto por: idEmpresaConf + idTipoParametro
	 * @return lista de objetos EmpresaParametroDto
	 */
	public static List<EmpresaParametroDto> get(String key){
		List<EmpresaParametroDto> lista=null;
		if(chmParametros.containsKey(key)){
			lista=(List<EmpresaParametroDto>)chmParametros.get(key);
		}
		return lista;
	}
	
	public static void viewchmEmpresaParametro(){
		log4j.debug(" ## chmEmpresaParametro="+chmParametros);
	}
	
	
	/**
	 * Adiciona un nuevo string a la lista de parámetros generales 
	 * @param key, compuesto por: Identificador del parámetro
	 * @param value, valor del parámetro
	 * @return
	 */
	public static void putChmGeneral(String key, String value){
		chmGeneral.put(key, value);
	}	
	
	/**
	 * Reemplaza un nuevo string a la lista de parámetros generales 
	 * @param key, compuesto por: Identificador del parámetro
	 * @param value, valor del parámetro a reemplazar
	 * @return
	 */
	public static void replaceChmGeneral(String key, String value){
		chmGeneral.replace(key, value);
	}	

	/**
	 *  Obtiene un string dependiendo de su key
	 * @param key, compuesto por: Identificador del parámetro
	 * @return lista de objetos EmpresaParametroDto
	 */
	public static String getChmGeneral(String key){
		String value = null;
		if(chmGeneral.containsKey(key)){
			value = (String)chmGeneral.get(key);
		}
		return value;
	}	
	
	/**
	 * Verifica si el objeto chmGeneral es nulo
	 * @return true ->  null
	 * 		   false -> not null
	 */
	public static boolean isNullChmGeneral(){
		if(chmGeneral == null){
			return true;
		}else{
			return false;
		}
	}	

	/**
	 * Verifica si el objeto chmGeneral está vacío
	 * @return true ->  null
	 * 		   false -> not null
	 */
	public static boolean isEmptyChmGeneral(){
		if(chmGeneral.size() == 0){
			return true;
		}else{
			return false;
		}
	}	
}
