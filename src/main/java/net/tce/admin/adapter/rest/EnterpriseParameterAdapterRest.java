package net.tce.admin.adapter.rest;

import net.tce.admin.service.EmpresaParametroService;
import net.tce.app.exception.SystemTCEException;
import net.tce.dto.EmpresaParametroDto;
import net.tce.util.Constante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Clase donde se aplica el MVC Spring REST para los servicos expuestos
 * @author evalle
 *
 */
@Controller
@RequestMapping(Constante.URI_ENTERPRISE)
public class EnterpriseParameterAdapterRest extends ErrorMessageAdapterRest{
	
	
	@Autowired
	EmpresaParametroService empresaParametroService;


	/**
	 * Controlador expuesto que ejecuta la funcion get del servicio "enterpriseParameter" 
	 * @param json, mensaje json del cliente
	 * @return JSON Message
	 * @throws ClassNotFoundException 
	 */
	@RequestMapping(value=Constante.URI_GET, method=RequestMethod.POST,headers = Constante.ACEPT_REST_JSON)
	public @ResponseBody String getParameters(@RequestBody String json) throws Exception  {
		log4j.debug("%%% get > json="+gson);
			return empresaParametroService.get(gson.fromJson(json, EmpresaParametroDto.class));
		
	}
	
	@RequestMapping(value=Constante.URI_CREATE, method=RequestMethod.POST,headers = Constante.ACEPT_REST_JSON)
	public @ResponseBody String create(@RequestBody String json) throws Exception  {
		log4j.debug("%%% create > json="+json);
		return empresaParametroService.create(gson.fromJson(json, EmpresaParametroDto.class));
	}
	
	@RequestMapping(value=Constante.URI_UPDATE, method=RequestMethod.POST,headers = Constante.ACEPT_REST_JSON)
	public @ResponseBody String update(@RequestBody String json) throws Exception  {
		log4j.debug("%%% update > json="+json);
		return empresaParametroService.update(gson.fromJson(json, EmpresaParametroDto.class));
	}
	
	@RequestMapping(value=Constante.URI_DELETE, method=RequestMethod.POST,headers = Constante.ACEPT_REST_JSON)
	public @ResponseBody String delete(@RequestBody String json) throws Exception  {
		log4j.debug("%%% delete > json="+json);
		return empresaParametroService.delete(gson.fromJson(json, EmpresaParametroDto.class));
	}
	
	@RequestMapping(value=Constante.URI_MULTIPLE_UPDATE, method=RequestMethod.POST,headers = Constante.ACEPT_REST_JSON)
	public @ResponseBody String updateMultiple(@RequestBody String json) throws Exception  {
		log4j.debug("%%% updMultiple > json="+json);
		return empresaParametroService.updateMultiple(json);
	}
	
	
	@RequestMapping(value=Constante.URI_RELOAD, method=RequestMethod.POST,headers = Constante.ACEPT_REST_JSON)
	public @ResponseBody String reloadCache(@RequestBody String json) throws Exception  {
		log4j.debug("%%% reload > json="+json);
		return empresaParametroService.reloadCache(gson.fromJson(json, EmpresaParametroDto.class));
	}
}
