package net.tce.admin.adapter.rest;

import javax.inject.Inject;

import net.tce.admin.service.TrackingService;
import net.tce.dto.TrackingDto;
import net.tce.util.Constante;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;


/**
 * Funcionalidad de EndPoint Para <b>TRACKING</b> <br>
 * @author DhrDeveloper
 *
 */
@Controller
@RequestMapping(Constante.URI_TRACKING)
public class TrackingAdapterRest extends ErrorMessageAdapterRest {

	@Inject
	Gson gson;
	
	@Autowired
	TrackingService trackingService;
	
	/**
	 * Controlador expuesto que ejecuta la funcion CREATE del servicio Tracking
	 * @param json
	 * @return idTracking
	 */
	@RequestMapping(value=Constante.URI_CREATE, method=RequestMethod.POST,headers = Constante.ACEPT_REST_JSON)
	public @ResponseBody String create(@RequestBody String json)  throws Exception {
		return trackingService.create(gson.fromJson(json, TrackingDto.class));
	}
	
	/**
	 * Controlador expuesto que ejecuta la funcion READ del servicio Tracking 
	 * @param json
	 * @return  un mensaje json con una lista de objetos Tracking
	 */
	@RequestMapping(value=Constante.URI_READ, method=RequestMethod.POST,headers = Constante.ACEPT_REST_JSON)
	public @ResponseBody String read(@RequestBody String json)  throws Exception {
		return trackingService.read(gson.fromJson(json, TrackingDto.class));
	}
	
	/**
	 * Controlador expuesto que ejecuta la funcion UPDATE del servicio Tracking 
	 * @param json
	 * @return  un mensaje json con una lista de objetos Tracking
	 */
	@RequestMapping(value=Constante.URI_UPDATE, method=RequestMethod.POST,headers = Constante.ACEPT_REST_JSON)
	public @ResponseBody String update(@RequestBody String json)  throws Exception {
		return trackingService.update(gson.fromJson(json, TrackingDto.class));
	}

	/**
	 * Controlador expuesto que ejecuta la funcion GET del servicio Tracking 
	 * @param json
	 * @return  un mensaje json con una lista de objetos Tracking
	 */
	@RequestMapping(value=Constante.URI_GET, method=RequestMethod.POST,headers = Constante.ACEPT_REST_JSON)
	public @ResponseBody String get(@RequestBody String json)  throws Exception {
		Object object=trackingService.get(gson.fromJson(json, TrackingDto.class));
		return  (object instanceof String) ? (String)object:gson.toJson(object);
	}
	
	/**
	 * Controlador expuesto que ejecuta la funcion DELETE del servicio Tracking
	 * @param json
	 * @return idTracking
	 */
	@RequestMapping(value=Constante.URI_DELETE, method=RequestMethod.POST,headers = Constante.ACEPT_REST_JSON)
	public @ResponseBody String delete(@RequestBody String json)  throws Exception {
		return trackingService.delete(gson.fromJson(json, TrackingDto.class));
	}
}
