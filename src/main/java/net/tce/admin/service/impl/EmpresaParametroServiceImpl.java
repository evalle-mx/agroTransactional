package net.tce.admin.service.impl;

import javax.inject.Inject;
import org.apache.log4j.Logger;
import net.tce.admin.service.EmpresaParametroService;
import net.tce.admin.service.RestJsonService;
import net.tce.app.exception.SystemTCEException;
import net.tce.dto.EmpresaParametroDto;
import net.tce.util.Constante;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.google.gson.Gson;

/**
 * Clase donde se aplica las politicas de negocio del servicio de enterprise
 * @author Goyo
 *
 */
@Transactional
@Service("empresaParametroService")
public class EmpresaParametroServiceImpl implements EmpresaParametroService {
	static Logger log4j = Logger.getLogger( EmpresaParametroServiceImpl.class );
	
	
	@Autowired
	private RestJsonService restJsonService;
	
	@Inject
	private Gson gson;
	
	/**
	 * Se obtiene una lista de objetos EmpresaParametroDto dependiendo del idConf y idTipoParametro
	 * @param empresaParametroDto
	 * @return
	 * @throws SystemTCEException 
	 */
	public String get(EmpresaParametroDto empresaParametroDto) throws SystemTCEException {
		
		log4j.debug("<get> Se manda a llamar get de EmpresaParametroService");
		return restJsonService.serviceRESTJson(gson.toJson(empresaParametroDto),new StringBuilder(Constante.URI_ENTERPRISE).
											   append(Constante.URI_GET).toString());
	}
	
	public String create(EmpresaParametroDto empresaParametroDto) throws SystemTCEException {
		
		log4j.debug("<update> Se manda a llamar CREATE de EmpresaParametroService");
		return restJsonService.serviceRESTJson(gson.toJson(empresaParametroDto),new StringBuilder(Constante.URI_ENTERPRISE).
											   append(Constante.URI_CREATE).toString());		
	}
	
	/**
	 * Se actualiza un registro de Empresa Parámetro
	 * @param empresaParametroDto
	 * @return
	 * @throws SystemTCEException 
	 */
	public String update(EmpresaParametroDto empresaParametroDto) throws SystemTCEException {
		
		log4j.debug("<update> Se manda a llamar UPDATE de EmpresaParametroService");
		return restJsonService.serviceRESTJson(gson.toJson(empresaParametroDto),new StringBuilder(Constante.URI_ENTERPRISE).
											   append(Constante.URI_UPDATE).toString());		
	}
	
	/**
	 * Se envia un listado de EMpresa parámetro para actualizarlos
	 * @param json
	 * @return
	 * @throws SystemTCEException
	 */
	public String updateMultiple(String json) throws SystemTCEException {
		log4j.debug("<updateMultiple> Se manda a llamar UPDATE_MULTIPLE de EmpresaParametroService");
		return restJsonService.serviceRESTJson(json, new StringBuilder(Constante.URI_ENTERPRISE).
				   append(Constante.URI_MULTIPLE_UPDATE).toString() );
		
	}

	
	/**
	 * Realiza una petición para recargar el Cache
	 * @param empresaParametroDto
	 * @return
	 * @throws SystemTCEException
	 */
	public String reloadCache(EmpresaParametroDto empresaParametroDto) throws SystemTCEException {
		//TODO verificar si se tiene que recargar en Transactional También
		log4j.debug("<reloadCache> Se manda a llamar RELOAD EmpresaParametroService");
		return restJsonService.serviceRESTJson(gson.toJson(empresaParametroDto),new StringBuilder(Constante.URI_ENTERPRISE).
											   append(Constante.URI_RELOAD).toString());		
	}
	
	/**
	 * Se la eliminacion de un registro de Empresa Parámetro
	 * @param empresaParametroDto
	 * @return
	 * @throws SystemTCEException 
	 */
	public String delete(EmpresaParametroDto empresaParametroDto) throws SystemTCEException {
		
		log4j.debug("<delete> Se manda a llamar DELETE de EmpresaParametroService");
		return restJsonService.serviceRESTJson(gson.toJson(empresaParametroDto),new StringBuilder(Constante.URI_ENTERPRISE).
											   append(Constante.URI_DELETE).toString());		
	}
}
