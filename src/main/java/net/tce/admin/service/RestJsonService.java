package net.tce.admin.service;

import net.tce.app.exception.SystemTCEException;

public interface RestJsonService {
	String serviceRESTJson(String inputJson, String uriService) throws  SystemTCEException;
}
