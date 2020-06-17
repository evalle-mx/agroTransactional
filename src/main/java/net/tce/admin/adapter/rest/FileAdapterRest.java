package net.tce.admin.adapter.rest;

import net.tce.admin.service.FileServer;
import net.tce.dto.FileDataConfDto;
import net.tce.dto.FileDto;
import net.tce.exception.FileException;
import net.tce.util.Constante;
import net.tce.util.UtilsTCE;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(Constante.URI_FILE)
public class FileAdapterRest extends ErrorMessageAdapterRest{
	Logger log4j = Logger.getLogger( this.getClass());
	@Autowired
	private FileServer fileService;
	
	
	/**
	 * Controlador expuesto que ejecuta la funcion update del servicio File 
	 * @param json
	 * @return  un mensaje json con una lista de objetos FileDto
	 * @throws Exception 
	 */
	@RequestMapping(value=Constante.URI_UPDATE, method=RequestMethod.POST,headers = Constante.ACEPT_REST_JSON)
	public @ResponseBody String update(@RequestBody String json) throws Exception  {
		return gson.toJson(fileService.update(gson.fromJson(json, FileDto.class)));
	  }
	
	
	/**
	 * Controlador expuesto que ejecuta la funcion get del servicio File 
	 * @param json
	 * @return  un mensaje json con una lista de objetos FileDto
	 * @throws Exception 
	 */
	@RequestMapping(value=Constante.URI_GET, method=RequestMethod.POST,headers = Constante.ACEPT_REST_JSON)
	public @ResponseBody String get(@RequestBody String json) throws Exception  {
		Object object=fileService.get(gson.fromJson(json, FileDto.class));
		if(object instanceof FileDto){
			return UtilsTCE.getJsonMessageGson(null, object);
		}else{
			return gson.toJson(object);
		}
	  }
	
	/**
	 * Controlador expuesto que ejecuta la funcion delete del servicio File 
	 * @param json
	 * @return  un mensaje json con una lista de objetos FileDto
	 * @throws Exception 
	 */
	@RequestMapping(value=Constante.URI_DELETE, method=RequestMethod.POST,headers = Constante.ACEPT_REST_JSON)
	public @ResponseBody String delete(@RequestBody String json) throws Exception  {
		return fileService.delete(gson.fromJson(json, FileDto.class));
	  }
	
//	/**
//	 * Controlador expuesto que ejecuta la funcion dataConf del servicio File 
//	 * @param json, mensaje JSON 
//	 * @return,  mensaje JSON
//	 * @throws Exception 
//	 */
//	 
//	@RequestMapping(value=Constante.URI_DATA_CONF, method=RequestMethod.POST,headers = Constante.ACEPT_REST_JSON)
//	public @ResponseBody String dataConf(@RequestBody String json) throws Exception {
//		FileDataConfDto fileDataConfDto=fileService.dataConf(gson.fromJson(json, FileDataConfDto.class));
//		return fileDataConfDto.getMessage() != null ? UtilsTCE.getJsonMessageGson(null,fileDataConfDto):
//													  UtilsTCE.dataconfToJson(fileDataConfDto);
//	  }//*/
}
