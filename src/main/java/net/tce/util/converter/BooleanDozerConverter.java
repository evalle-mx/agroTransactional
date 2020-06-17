package net.tce.util.converter;

/**
 * Esta clase se encarga de converir un String a un boolean y visceversa
 * @author Goyo
 *
 */
public class BooleanDozerConverter  extends org.dozer.DozerConverter<String, Boolean>{

	public BooleanDozerConverter() {
		super(String.class, Boolean.class);
	}

	/**
	 * Convierte un string a boolean
	 * @param source
	 * @param destination
	 * @return un objeto boolean
	 */
	public Boolean convertTo(String source, Boolean destination) {
	    if ("1".equals(source)) {
	      return Boolean.TRUE;
	    } else if ("0".equals(source)) {
	      return Boolean.FALSE;
	    }
	    throw new IllegalStateException("BooleanDozerConverter(convertTo) --> El valor no se reconoce");
	  }

	 /**
	  * Convierte un boolean a string
	  * @param source
	  * @param destination
	  * @return un objeto string
	  */
	  public String convertFrom(Boolean source, String destination) {
	    if (Boolean.TRUE.equals(source)) {
	      return "1";
	    } else if (Boolean.FALSE.equals(source)) {
	      return "0";
	    }
	    throw new IllegalStateException("BooleanDozerConverter(convertFrom) --> El valor no se reconoce");
	  }

}
