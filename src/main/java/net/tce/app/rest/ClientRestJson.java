package net.tce.app.rest;

import net.tce.app.exception.SystemTCEException;

public interface ClientRestJson {
	String getObjectFromService(String inputJson, String uriService) throws SystemTCEException;
}
