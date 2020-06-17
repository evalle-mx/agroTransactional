package net.tce.admin.adapter.rest;

import javax.inject.Inject;

import net.tce.admin.service.EncuestaService;
import net.tce.dto.EncuestaDto;
import net.tce.util.Constante;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

/**
 * Funcionalidad de EndPoint Para <b>ENCUESTA</b> <br>
 * @author DhrDeveloper
 *
 */
@Controller
@RequestMapping(Constante.URI_ENCUESTA)
public class EncuestaAdapterRest extends ErrorMessageAdapterRest {
	
	private final String URI_QUESTIONARY ="/questionary";
	@Autowired
	EncuestaService encuestaService;
	Logger log4j = Logger.getLogger( this.getClass());
	
	@Inject
	Gson gson;
	
	/**
	 * Controlador expuesto que ejecuta la funcion get del servicio Rol 
	 * @param json
	 * @return  un mensaje json con una lista de objetos Rol
	 */
	@RequestMapping(value=Constante.URI_GET, method=RequestMethod.POST,headers = Constante.ACEPT_REST_JSON)
	public @ResponseBody String get(@RequestBody String json)  {
		Object object=encuestaService.get(gson.fromJson(json, EncuestaDto.class));
		return  (object instanceof String) ? (String)object:gson.toJson(object);
	}
	
	/**
	 * Controlador expuesto que ejecuta la funcion get del servicio Rol 
	 * @param json
	 * @return  un mensaje json con una lista de objetos Rol
	 */
	@RequestMapping(value=Constante.URI_READ, method=RequestMethod.POST,headers = Constante.ACEPT_REST_JSON)
	public @ResponseBody String read(@RequestBody String json)  {
		return encuestaService.read(gson.fromJson(json, EncuestaDto.class));
	}
		
	/**
	 * Controlador expuesto que ejecuta la funcion get del servicio Rol 
	 * @param json
	 * @return  un mensaje json con una lista de objetos Rol
	 */
	@RequestMapping(value=Constante.URI_UPDATE, method=RequestMethod.POST,headers = Constante.ACEPT_REST_JSON)
	public @ResponseBody String update(@RequestBody String json)  {	
		Object object=encuestaService.update(gson.fromJson(json, EncuestaDto.class));
		return  (object instanceof String) ? (String)object:gson.toJson(object);
	}
	
	/**
	 * Controlador expuesto que ejecuta la funcion get del servicio Rol 
	 * @param json
	 * @return  un mensaje json con una lista de objetos Rol
	 */
	@RequestMapping(value=URI_QUESTIONARY, method=RequestMethod.POST,headers = Constante.ACEPT_REST_JSON)
	public @ResponseBody String delete(@RequestBody String json)  {
		Object object=encuestaService.questionary(gson.fromJson(json, EncuestaDto.class));
		return  (object instanceof String) ? (String)object:gson.toJson(object);
	}
	

}
