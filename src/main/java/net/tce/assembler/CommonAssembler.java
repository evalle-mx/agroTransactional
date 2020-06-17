package net.tce.assembler;

import java.math.BigDecimal;
import javax.inject.Inject;
import com.google.gson.Gson;
/**
 * Se crea esta clase para contener los assembler de Domicilio y otros empleados de manera común
 * y así concentrar el codigo, evitando duplicidad
 * @author tce
 *
 */
public  class CommonAssembler {
	
	protected boolean huboModificacion;
	
	@Inject
	Gson gson;
	
	/**
	 * Metodo para obtener el valor de un Objeto en cadena (String.valueOf), y en caso de ser null, obtiene null 
	 * @param value
	 * @return
	 */
	protected static String stValue(Object value){
		String resp = null;
		
		if(value!=null){
			resp = String.valueOf(value);
		}
		return resp;
	}
	
	/**
	 * Procesa la respuesta en modo Rest (String)
	 * @param object
	 * @return
	 */
	public String restResponse(Object object){
		return (object instanceof String) ? (String)object: gson.toJson(object);
	}
	

}
