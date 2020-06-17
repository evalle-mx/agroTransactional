package net.tce.exception;

/**
 * 
 * @author Goyo
 *
 */
@SuppressWarnings("serial")
public class FileException extends Exception {

	/**
	 * 
	 */
	public FileException(String msg) {
		super(msg);
	}
	
	public FileException(Throwable causa) {
		super(causa);
	}
	
	public FileException(String mensaje, Throwable causa) {
		super(mensaje,causa);
	}

}
