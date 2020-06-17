package net.tce.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.tce.admin.service.RestJsonService;
import net.tce.app.exception.SystemTCEException;
import net.tce.app.rest.ClientRestJson;

//@Transactional
@Service("restJsonService")
public class RestJsonServiceImpl implements RestJsonService {
	 
	@Autowired
	private ClientRestJson clientRestJson;
	
	
	/**
	 * Envia directamente el mensaje Json desde el cliente y obtiene respuesta del servicio en  mensaje Json
	 * @param inputJson, mensaje json a mandar
	 * @param uriService, es el URI del servicio
	 * @return un mensaje sson
	 * @throws SystemTCEException
	 */
	public String serviceRESTJson(String inputJson, String uriService)
			throws SystemTCEException {
		return clientRestJson.getObjectFromService(inputJson, uriService);
	}



}
