package net.tce.util.converter;

import java.util.Date;

import org.apache.log4j.Logger;

import net.tce.util.Constante;
import net.tce.util.DateUtily;

/**
 * Convierte un String a un Date y visceversa
 * @author Osy
 *
 */
public class DateDozerConverter  extends org.dozer.DozerConverter<String, Date>{

	Logger log4j = Logger.getLogger( this.getClass());

	public DateDozerConverter() {
		super(String.class, Date.class);
	}

	/**
	 * Convierte un string a boolean
	 * @param source
	 * @param destination
	 * @return un objeto boolean
	 */
	public Date convertTo(String source, Date destination){
		log4j.info("<DateDozerConverter,convertTo> Inicio...");
		log4j.info("<DateDozerConverter,convertFrom> Source :" + source);
	    try {
			log4j.info("<DateDozerConverter,convertTo> Fin...");
			return DateUtily.string2Date(source, Constante.DATE_FORMAT_JAVA);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	  }

	 /**
	  * Convierte un boolean a string
	  * @param source
	  * @param destination
	  * @return un objeto string
	  */
	  public String convertFrom(Date source, String destination) {
			log4j.info("<DateDozerConverter,convertFrom> Inicio...");
			String formateada =DateUtily.date2String(source,Constante.DATE_FORMAT_JAVA); 
		    return formateada;
	  }

}
