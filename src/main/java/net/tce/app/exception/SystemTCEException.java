package net.tce.app.exception;

import net.tce.util.Constante;


@SuppressWarnings("serial")
public class SystemTCEException extends Exception {
	private String cveError = Constante.ERROR_CODE_SYSTEM;
	
	public SystemTCEException(String msg) {
		super(msg);
		
	}
	public SystemTCEException(String msg, Throwable causa) {
		super(msg,causa);
	}
	public SystemTCEException(String cveError, String msg) {
		super(msg);
		this.cveError= cveError;
	}
	public String getCveError() {
		return cveError;
	}
}
