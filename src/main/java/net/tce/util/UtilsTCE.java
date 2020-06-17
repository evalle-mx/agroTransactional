package net.tce.util;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.Calendar;
//import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.tce.cache.FileCache;
//import net.tce.dto.DataConfDto;
import net.tce.dto.MensajeDto;
import org.apache.log4j.Logger;
import com.google.gson.Gson;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import uk.ac.shef.wit.simmetrics.similaritymetrics.JaroWinkler;
import uk.ac.shef.wit.simmetrics.similaritymetrics.Levenshtein;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class UtilsTCE {
	static Logger log4j = Logger.getLogger("net.tce.util.UtilsTCE");
	public final static float MAX_SCORE_SIMILARITY = 0.8f;
	public final static String EXPRESION_REGULAR_RFC_EMPRESA = "^[A-Z]{3}[0-9]{6}[A-Z0-9]{3}$";
	public final static String EXPRESION_REGULAR_RFC_PERSONA = "^[A-Z]{4}[0-9]{6}[A-Z0-9]{3}$";
	
	public final static int EARTH_RADIUS=6371;
	public final static String EXP_REG_A="[àáâãäå]";
	public final static String EXP_REG_E="[èéêë]";
	public final static String EXP_REG_I="[ìíîï]";
	public final static String EXP_REG_O="[òóôõö]";
	public final static String EXP_REG_U="[ùúûü]";	
	public final static String EXP_REG_CARACTERES="[^0-9a-zA-Z ;,ñ]";
	public final static String EXP_REG_ESPACIO="\\s+";
	public final static String EXP_REG_ARTICULOS=" el | la | lo | las | los | un | una | unos | unas | al | del ";
	public final static String EXP_REG_CONJUNCION=" ni | bien | pero | sino | aunque |".
					concat(" que | ya que | puesto que | pues | porque | como ya |").
					concat(" si | siempre que | aunque | como | para | para que |").
					concat(" asi que | de modo que | de manera que | luego | por tanto |").
					concat(" cuando | mientras | tan pronto como | despues de que | hasta que ");
	// | y | e | o | u | no se quitan
	
	public final static String EXP_REG_ADVERBIO=" aqui | ahi | alli | alla | aca | arriba | abajo | cerca | lejos |".
					concat(" delante | encima | debajo | enfrente | atras | alrededor |").
					concat(" pronto | tarde | temprano | todavia | aun | ya | ayer | hoy |").
					concat(" mañana | siempre | nunca | jamas |").
					concat(" anoche | enseguida | ahora | mientras | anteriormente |").
					concat(" bien | mal | regular | despacio | deprisa | asi | aprisa |").
					concat(" adrede | peor | mejor | bastante | muy | mas | menos | algo |").
					concat(" demasiado | casi | solo | nada | tambien | cierto | claro |").
					concat(" exacto | obvio | no | tampoco | quiza | quizas | acaso | tal vez |").
					concat(" puede | puede ser | a lo mejor | solo | aun | inclusive | ademas |").
					concat(" incluso | viceversa | siquiera ");
	
	public final static String EXP_REG_PREPOSICIONES=" a | ante | bajo | cabes | con | contra |".
					 concat(" de | desde | durante | en | entre |").
					 concat(" excepto | hacia | hasta | mediante |").
					 concat(" pro | salvo | segun | sin |").
					 concat(" so | sobre | tras | via ");
	
	public final static String EXP_REG_PRONOMBRES=" yo | mi | me | conmigo | tu | ti | te | contigo | usted |".
					concat(" el | ella | ello | le | se | consigo | si |").
					concat(" nosotros | nosotras | nos | vosotros | vosotras | os |").
					concat(" ellos | ellas | les | se |").
					concat(" este | esta | esto | estos | estas |").
					concat(" ese | esa | eso | esos | esas |").
					concat(" aquel | aquella | aquello | aquellos | aquellas |").
					concat(" mio | tuyo | suyo | nuestro | vuestro |").
					concat(" mios | tuyos | suyos | nuestros | vuestros |").
					concat(" mia | tuya | suya | nuestra | vuestra |").
					concat(" mias | tuyas | suyas | nuestras | vuestras |").
					concat(" aquel | aquella | aquello | aquellos | aquellas |").
					concat(" ningun | ninguno | ninguna | nada | ningunos | ningunas |").
					concat(" uno | una | algun | alguno | alguna | alguna | algo | algunos | algunas |").
					concat(" ningun | ninguno | ninguna | nada | ningunos | ningunas |").
					concat(" poco | poca | pocos | pocas |").
					concat(" escaso | escasa | escasos | escasas |").
					concat(" mucho | mucha | muchos | muchas |").
					concat(" demasiado | demasiada | demasiados | demasiadas |").
					concat(" todo | toda | todos | todas |").
					concat(" mismo | misma | mismos | mismas |").
					concat(" tan | tanto | tanta | tantos | tantas |").
					concat(" varios | varias | alguien | nadie | cualquier |  cualquiera | cualesquiera |").
					concat(" quienquiera | quienesquiera | tal | tales | demás | bastante | bastantes |").
					concat(" que | cual | cuales | quien | quienes | cuyo | cuya | cuyos |  cuyas | donde |").
					concat(" cuanto | cuanta ");


	/**
	 * Regresa una cadena aleatoria 
	 * @param longitud de la cadena
	 * @return
	 */
	public static String getCadenaAlfanumAleatoria (int longitud){
		String cadenaAleatoria = "";
		int i = 0;
		while ( i < longitud){
			char c = (char)new Random().nextInt(255);
			if ( (c >= '0' && c <='9') || (c >='A' && c <='Z') ){
				cadenaAleatoria += c;
				i ++;
			}
		}
		return cadenaAleatoria;
	}
	

	/**
	 * Este m�todo se encarga de cambiar la configuración de log4j en runtime
	 * @param nivel  String que indica el nivel de debug
	 */
	 /*public static void cambiarNivelLog4j(String nivel, String path){
	      try {	    	
	    	  Properties props = new Properties();
	          InputStream configStream = UtilsTCE.class.getResourceAsStream("/log4j.properties");
	          props.load(configStream);
	          configStream.close();  	          
	 	      props.setProperty("log4j.appender.FILE.Threshold",nivel);
	 	      props.setProperty("log4j.appender.FILE.File",path);
	 	      LogManager.resetConfiguration();
	 	      PropertyConfigurator.configure(props);	 	    
	      } catch(Exception e) {
	    	 log4j.error("ERROR AL CAMBIAR EL NIVEL DE VISTA EN LOG4J");
	         e.printStackTrace();
	      }	    	     
	   }*/
	 
	 /**
	  * Borra archivos en forma recursiva, de una carpeta dada
	  * @param directorio correspondiente
	  */
	  public static void borrarDirectorio (File directorio){
          File[] ficheros = directorio.listFiles();
          log4j.debug("%%%% ficheros:"+ficheros);
          if(ficheros != null){
	          for (int x=0;x<ficheros.length;x++){
	              if (ficheros[x].isDirectory()) {
	                      borrarDirectorio(ficheros[x]);
	              }
	              ficheros[x].delete();
	          } 
          }
      }
	  
	  /**
	   * Borra una carpeta o archivo 
	   * @param pathAbsoluto, ubicacion del archivo o carpeta
	   * @param idDirectorioCache, es la clave del directorio en cache
	   */
	  public static void deleteFolderoFileLocal(String pathAbsoluto,String idDirectorioCache){
		  File file =new File(pathAbsoluto);
		  boolean isFile=file.isFile();
		 log4j.debug("%%%% isFile:"+isFile+" pathAbsoluto="+pathAbsoluto+
				 " carpeta="+pathAbsoluto.substring(0, pathAbsoluto.lastIndexOf("/")));
		 //Es una carpeta
		 if(!isFile){
			 //Se borran fisicamente los hijos
			 UtilsTCE.borrarDirectorio(file);
		 }
		 //Si hay cache
		 if(idDirectorioCache != null){
			 //Se borra el archivo o la carpeta fisica
			 if (file.delete()){
				 //Es una carpeta
				 if(!isFile ){
					 //se elimina el id de la carpeta en el cache
					 FileCache.deleteFile(idDirectorioCache); 
				 }
				 log4j.info(new StringBuilder(isFile ? "El Archivo":"El Directorio").append(" fisico: ").
					  append(pathAbsoluto).append(" , ha sido borrado correctamente.").toString());
			 } else{
				 log4j.error(new StringBuilder(isFile ? "El Archivo":"El Directorio").append(" fisico: ").
					  append(pathAbsoluto).append(" , no se pudo borrar.").toString());
			 }
			 
			 //Se revisa si la carpeta esta vacía, solo despues de borrar un archivo
			 if(isFile){
				 String pathCarpeta=pathAbsoluto.substring(0, pathAbsoluto.lastIndexOf("/"));
				 file =new File(pathCarpeta);
				 log4j.debug("%%%% carpeta_archivos="+file.list().length+
						     " nom_carpeta="+pathCarpeta.substring(pathCarpeta.lastIndexOf("/")+1,pathCarpeta.length())+
						     " idDirectorioCache="+idDirectorioCache);
				 //hay archivos?
				 if(file.list().length == 0){
					 //Al estar vacía la carpeta, se borra
					 deleteFolderoFileLocal(pathCarpeta,  idDirectorioCache);
				 }
			 }
		 }
	  }

	 
	 
	 /**
	 * Obtiene una cadena con formato Json en base a los parametros ingresados,
	 * en caso de parametro "errores" != null, concatena, dejando el detalle anterior
	 * al final de la cadena
	 * @param code
	 * @param type
	 * @param eMessage
	 * @return
	 */
	public static String getJsonMessageGson(String errores, Object object ) {
		StringBuilder sb=new StringBuilder("[");
		return (errores == null || errores.equals("")) ? 
			 		sb.append(new Gson().toJson(object)).append("]").toString():
			 		sb.append(new Gson().toJson(object)).append(",").
			 		append(errores.substring(1, errores.length())).toString();
	}
	
	
//	 /**
//	  * Regresa un json-dataconf de anio, con las claves minLength y maxLength
//	  * @param dataconfAnio, json data-conf
//	  * @param minMax, cadena que contiene los criterios min y max
//	  * @return json
//	  */
//	 public static String getDataConfAnio(String dataconfAnio, String minMax){
//		Gson gson=new Gson();
//		DataConfDto dataConfDto=gson.fromJson(dataconfAnio, DataConfDto.class);
//		StringTokenizer st=new StringTokenizer(minMax, ",");
//		int anio =DateUtily.getCalendar().get(Calendar.YEAR);
//		//importa el orden 
//		dataConfDto.setMaxLength(new Long(anio - Integer.valueOf(st.nextToken()).intValue()));
//		dataConfDto.setMinLength(new Long(anio - Integer.valueOf(st.nextToken()).intValue()));
//		return gson.toJson(dataConfDto);
//	 }
	 
	/**
	 * Returns altern element if first one is null, like NVL pl/sql function
	 * @param a,b (parameterized types)
	 * @return Not Null a||b
	 */
	public static <T> T nvl(T a, T b) {
	 return (a == null)?b:a;
	}	 
	 
//	/**
//	 * Asigna el anio base
//	 * @param anioDataConf, la cadena dataconf
//	 * @param inicio, true si se quiere MinLength o false si se quiere MaxLength
//	 * @param nacimiento, si el calculo es para anio de nacimiento
//	 * @param anioBase, si no es anio de nacimiento, este dato tiene un anio default
//	 * @return el anio
//	 */
//	public static int getAnioBaseToDataConf(String anioDataConf, boolean inicio, boolean nacimiento,Integer anioBase){
//		int resp;
//		DataConfDto dataConfDto=new Gson().fromJson(anioDataConf, DataConfDto.class);
//		if(inicio){
//			if(dataConfDto.getMinLength() != null)
//				resp= dataConfDto.getMinLength().intValue();
//			else{
//				if(nacimiento)
//					resp= ANIO_NACIMIENTO_BASE_MIN;
//				else
//					resp= anioBase;
//			}
//		}else{
//			if(dataConfDto.getMaxLength() != null)
//				resp= dataConfDto.getMaxLength().intValue();
//			else{
//				if(nacimiento)
//					resp= ANIO_NACIMIENTO_BASE_MAX;
//				else
//					resp= anioBase;
//			}
//		}
//		return resp;
//	}
	
	/**
	 * Returns absolute distance between two cartesian pointsS
	 * @param x1,y1,x2,y2
	 * @return double
	 */
	public static double distPlain(double x1, double y1, double x2, double y2) {
        return Math.sqrt(
            (x1 - x2) *  (x1 - x2) + 
            (y1 - y2) *  (y1 - y2)
        );
    }	

	/**
	 * Returns absolute distance between two geographical points latitude and longitude
	 * @param lat1,lng1,lat2,lng2
	 * @return double
	 */
	public static double distGeo(double lat1, double lng1, double lat2, double lng2) {
		//ver http://www.movable-type.co.uk/scripts/latlong.html
	    double a = Math.pow(Math.sin((double)(Math.toRadians(lat2-lat1) / 2)), 2) + 
	    		   (Math.pow(Math.sin((double)(Math.toRadians(lng2-lng1) / 2)), 2)
	            * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)));
	    return (double)(EARTH_RADIUS * (2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a))));
	   }	
    
	/**
	 * Returns distribution range classification according to provided method
	 * @param <T>
	 * @param List<T> completeObjectList,String valueAttribute, String rangeAttribute,  int statMethod
	 * @return <T> List<T>
	 */
    public static <T> List<T> getRankedDistribution(List<T> completeObjectList,String valueAttribute, String rangeAttribute,  int statMethod)
    				throws Exception{
	    log4j.debug("<getRankedDistribution> Start...");
	    log4j.debug("<getRankedDistribution> Method/valueAttribute/rangeAttribute :" + statMethod + "/" + valueAttribute + "/" + rangeAttribute);
	    log4j.debug("<getRankedDistribution> population.size :" + completeObjectList.size());

	    log4j.debug("<getRankedDistribution> Declaring objects");
	    Object object = new Object();
		Field field = null;
		Object value;
		Short range = 1;
		BigDecimal valueBigDecimal;

	    log4j.debug("<getRankedDistribution> Declaring stats objects");
		/* Stats */
		DescriptiveStatistics stats = new DescriptiveStatistics();
		
	    log4j.debug("<getRankedDistribution> Getting attributes sublist");
		/* IMPROVEMENT : Best way to get a sublist from a list of attributes of one object list */
	    Iterator<T> itListValues = completeObjectList.iterator();
	    while(itListValues.hasNext()){
	    	object = itListValues.next();
			field =Class.forName(object.getClass().getName()).getDeclaredField(valueAttribute);
			field.setAccessible(true);
			value = field.get(object);
		    log4j.debug("<getRankedDistribution> value :" + value);
		    log4j.debug("<getRankedDistribution> object.getClass() :" + object.getClass());
			if(value != null){
			    log4j.debug("<getRankedDistribution> value.getClass() :" + value.getClass());
				if(value instanceof Double){
				    log4j.debug("<getRankedDistribution> value is Double");
					stats.addValue((Double) value);	    						
				}else
					if(value instanceof BigDecimal){
					    log4j.debug("<getRankedDistribution> value is BigDecimal");
						valueBigDecimal = (BigDecimal) value;
						stats.addValue( valueBigDecimal.doubleValue() );	    							
					}
			}
	    }
	    
	    log4j.debug("<getRankedDistribution> Continuing process with sublist...size :" + stats.getN());
		Double currentValue = null;
		double maxRange1 = 0;
		double minRange2 = 0, maxRange2 = 0;
		double minRange3 = 0, maxRange3 = 0;
		double minRange4 = 0;

	    log4j.debug("<getRankedDistribution> mean/std/popVariance :" + stats.getMean() + "/" + stats.getStandardDeviation() + "/" + stats.getPopulationVariance());
	    log4j.debug("<getRankedDistribution> getPercentile 25/50/75 :" + stats.getPercentile(25) + "/" + stats.getPercentile(50) + "/" + stats.getPercentile(75));

	    /* For both methods RANGE: 1 = Lower values ... 4 = Greatest values */
	    if(statMethod == 1){
		    /* Method 1 Standard Deviation for Normal distributions 
		     RANGE 4 = [AritmeticMean + (1)StandardDeviation, MAX-LIMIT]
		     RANGE 3 = [AritmeticMean, AritmeticMean + (1)StandardDeviation
		     RANGE 2 = [AritmeticMean - (1)StandardDeviation, AritmeticMean)
		     RANGE 1 = [MIN-LIMIT, AritmeticMean - (1)StandardDeviation) 
		    */
		    minRange4 = stats.getMean() + stats.getStandardDeviation();
		    minRange3 = stats.getMean();
		    maxRange3 = stats.getMean() + stats.getStandardDeviation();
		    minRange2 = stats.getMean() - stats.getStandardDeviation();
		    maxRange2 = stats.getMean();
		    maxRange1 = stats.getMean() - stats.getStandardDeviation();
	    }
	    
	    if(statMethod == 2){
		    /* Method 2 Percentiles (Quartiles) for non-Normal distributions
		     RANGE 4 = [Q3, MAX-LIMIT]
		     RANGE 3 = [Q2, Q3)
		     RANGE 2 = [Q1, Q2)
		     RANGE 1 = [MIN-LIMIT, Q1) 
		    */
		    minRange4 = stats.getPercentile(75);
		    minRange3 = stats.getPercentile(50);
		    maxRange3 = stats.getPercentile(75);
		    minRange2 = stats.getPercentile(25);
		    maxRange2 = stats.getPercentile(50);
		    maxRange1 = stats.getPercentile(50);
	    }	    
		
		
	    log4j.debug("<getRankedDistribution> Assigning stat rank to every value");
	    Iterator<T> itList = completeObjectList.iterator();
	    while(itList.hasNext()){
	    	object = itList.next();

			field =Class.forName(object.getClass().getName()).getDeclaredField(valueAttribute);
			field.setAccessible(true);
			value = field.get(object);
			
			if(value != null){
				if(value instanceof Double){
				    log4j.debug("<getRankedDistribution> value is Double");
				    currentValue = (Double) value;	    						
				}else
					if(value instanceof BigDecimal){
					    log4j.debug("<getRankedDistribution> value is BigDecimal");
						valueBigDecimal = (BigDecimal) value;
						currentValue =  valueBigDecimal.doubleValue();	    							
					}

				if(currentValue >= minRange4){
			    	range = 4;
			    }else
			    if(currentValue >= minRange3 && currentValue < maxRange3){
			    	range = 3;
			    }else
			    if(currentValue >= minRange2 && currentValue < maxRange2){
			    	range = 2;
			    }else
			    if(currentValue < maxRange1){
			    	range = 1;
			    }
				log4j.debug("<getRankedDistribution> currentValue/range :" + currentValue + "/" + range);
				
				field =Class.forName(object.getClass().getName()).getDeclaredField(rangeAttribute);
				field.setAccessible(true);
				field.set(object, range);
			}	    	
	    }
	    
		log4j.debug("<getRankedDistribution> End...");
        return completeObjectList;
	}	
   
	
	/**
	 * Redondea un numero decimal de tipo double
	 * @param numero donde se aplica el redondeo
	 * @param digitos, numero de digitos a redondear
	 * @param roundingMode, modo de redondeo
	 * @return un numero redondeado
	 */
	public static double redondear(double numero, int digitos, RoundingMode roundingMode) {
		 	return new BigDecimal(String.valueOf(numero)).
						setScale(digitos,roundingMode).doubleValue();
	}
	
	/**
	 * Redondea un numero decimal de tipo BigDecimal
	 * @param numero donde se aplica el redondeo
	 * @param digitos, numero de digitos a redondear
	 * @param roundingMode, modo de redondeo
	 * @return un numero redondeado
	 */
	public static BigDecimal redondear(BigDecimal numero, int digitos, RoundingMode roundingMode) {
		 	return numero.setScale(digitos,roundingMode);
	}
	
	
	/**
	 * Filtra un texto en contexto gramatical 
	 * @param texto el cual se va a filtrar
	 * @return
	 */
	public static String filterGramaText(String texto){
		return removeStopWords(replaceEspecialChars(" ".concat(texto.toLowerCase()).concat(" ")));
	}
	
	/**
	 * Se reemplazan caracteres específicos del token
	 * @param token
	 * @return el token modificado
	 */
	public static String replaceEspecialChars(String token){
		return token.replaceAll(EXP_REG_A,"a").
					replaceAll(EXP_REG_E,"e").
					replaceAll(EXP_REG_I,"i").
					replaceAll(EXP_REG_O,"o").
					replaceAll(EXP_REG_U,"u");
	}
	
	/**
	 * Se eliminan palabras específicas del token
	 * @param token
	 * @return el token modificado
	 */
	public static String removeStopWords(String token){
		return token.replaceAll(EXP_REG_CARACTERES," ").
					replaceAll(",", " , ").
					replaceAll(";", " ; ").
					replaceAll(EXP_REG_CONJUNCION," ").
					replaceAll(EXP_REG_ADVERBIO," ").
					replaceAll(EXP_REG_ARTICULOS," ").
					replaceAll(EXP_REG_PREPOSICIONES," ").
					replaceAll(" por "," ").
					replaceAll(EXP_REG_PRONOMBRES," ").
					replaceAll(EXP_REG_ESPACIO," ");
	}
	
	/**
	 * 
	 * @param numero
	 * @param base
	 * @return
	 */
	public static double obtenerModulo(double numero,int base){
		double resp=numero/base;
		if(resp < 0){
			return resp;
		}else{
			return obtenerModulo(resp,base);
		}
	}
	
	/**
	 * Dado un numero se obtiene la base dependiendo del divisor
	 * @param numero, el cual se obtiene la  base
	 * @param divisor, se aplica al numero para obtener la base
	 * @return p.e.
	 * 			numero=256
	 * 			divisor=10 --> base=100		
	 */
	public static double obtenerBase(double numero,int divisor){
		int cont=0;
		 do {
			 numero=numero/divisor;
			 cont++;
			 } while (numero > 1);
		return  Math.pow(divisor,cont-1);
	}
	
	/**
	 * Regresa una cadena aleatoria dado max y min tamaño de esta
	 * @param longitud de la cadena
	 * @return
	 */
	public static String getCadenaAlfanumAleatoria (int longMin, int longMax){
		String cadenaAleatoria = "";
		int i = 0;
		int numRandom=0;
		//Encontrar un numero entre longMin y longMax para definir el tamaño de la cadena
		do{
			numRandom=new Random().nextInt(longMax);
		}while( longMin > numRandom );
		//Despues de obtener el tamaño de la cadena, se consigue la correspondiente
		while ( i < numRandom){
			char c = (char)new Random().nextInt(255);
			if ( (c >= '0' && c <='9') || (c >='A' && c <='Z') ){
				cadenaAleatoria += c;
				i ++;
			}
		}
		return cadenaAleatoria;
	}
	
	/**
	 * Fuerza un retardo
	 * @param ms Duración en segundos
	 * @author Osy
	 */
	public static void forceDelay(int ms){
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Método común para obtener mensaje de fechas (diaInicio, diaFin, etc) de un objeto con 
	 * dichas propiedades via <b>Reflection</b>
	 * @param compareInicio
	 * @param compareFin
	 * @return
	 */
	public static String mensajeFechas(int compareInicio, int compareFin,  Object object, boolean sendAllDateParams) throws Exception{
		String salida=null, valor=null;
		int mensajes=0;
		Field field =null;
		
		//Fecha fin es incorrecta				
		if(compareInicio == 0 && compareFin != 0){
			 mensajes=1;
		//Fecha inicio es incorrecta	
		}else if(compareInicio != 0 && compareFin == 0){
			 mensajes=2;
		}
		
		if(mensajes == 0 || mensajes == 2){
			/* La fecha inicial no puede ser mayor/Igual a la fecha final */
			field = Class.forName(object.getClass().getName()).getDeclaredField("diaInicio");
			field.setAccessible(true);
			valor=(String)field.get(object);
			if(valor!=null || sendAllDateParams){ //SE AGREGO ESTE IF PARA NO ENVIAR PARAMETRO SIN VALOR
				salida=UtilsTCE.getJsonMessageGson(salida, 
						new MensajeDto("diaInicio",valor,
					  	Mensaje.SERVICE_CODE_006_6,Mensaje.SERVICE_TYPE_ERROR,
						Mensaje.FILTER_DATACONF_DATAVALIDATION_COMPARE_F_INI)); 
			}
			
			field = Class.forName(object.getClass().getName()).getDeclaredField("mesInicio");
			field.setAccessible(true);
			valor=(String)field.get(object);
			if(valor!=null || sendAllDateParams){ //SE AGREGO ESTE IF PARA NO ENVIAR PARAMETRO SIN VALOR
				salida=UtilsTCE.getJsonMessageGson(salida, 
					new MensajeDto("mesInicio",valor,
				  	Mensaje.SERVICE_CODE_006_6,Mensaje.SERVICE_TYPE_ERROR,
					Mensaje.FILTER_DATACONF_DATAVALIDATION_COMPARE_F_INI));
			}
			field = Class.forName(object.getClass().getName()).getDeclaredField("anioInicio");
			field.setAccessible(true);
			valor=(String)field.get(object);
			if(valor!=null || sendAllDateParams){ //SE AGREGO ESTE IF PARA NO ENVIAR PARAMETRO SIN VALOR
				salida=UtilsTCE.getJsonMessageGson(salida,
					new MensajeDto("anioInicio",valor,
				  	Mensaje.SERVICE_CODE_006_6,Mensaje.SERVICE_TYPE_ERROR,
					Mensaje.FILTER_DATACONF_DATAVALIDATION_COMPARE_F_INI));
			}
		} 
		if(mensajes == 1){	 
			/* La fecha final no puede ser menor a la fecha inicial */
			field = Class.forName(object.getClass().getName()).getDeclaredField("diaFin");
			field.setAccessible(true);
			valor=(String)field.get(object);
			if(valor!=null || sendAllDateParams){ //SE AGREGO ESTE IF PARA NO ENVIAR PARAMETRO SIN VALOR
				salida=UtilsTCE.getJsonMessageGson(salida, 
				  	new MensajeDto("diaFin",valor,
				  	Mensaje.SERVICE_CODE_006_6,Mensaje.SERVICE_TYPE_ERROR,
					Mensaje.FILTER_DATACONF_DATAVALIDATION_COMPARE_F_FIN)); 
			}
			field = Class.forName(object.getClass().getName()).getDeclaredField("mesFin");
			field.setAccessible(true);
			valor=(String)field.get(object);
			if(valor!=null || sendAllDateParams){ //SE AGREGO ESTE IF PARA NO ENVIAR PARAMETRO SIN VALOR
				salida=UtilsTCE.getJsonMessageGson(salida, 
				  	new MensajeDto("mesFin",valor,
				  	Mensaje.SERVICE_CODE_006_6,Mensaje.SERVICE_TYPE_ERROR,
					Mensaje.FILTER_DATACONF_DATAVALIDATION_COMPARE_F_FIN));
			}
			field = Class.forName(object.getClass().getName()).getDeclaredField("anioFin");
			field.setAccessible(true);
			valor=(String)field.get(object);
			if(valor!=null || sendAllDateParams){ //SE AGREGO ESTE IF PARA NO ENVIAR PARAMETRO SIN VALOR
				salida=UtilsTCE.getJsonMessageGson(salida, 
				  	new MensajeDto("anioFin",valor,
				  	Mensaje.SERVICE_CODE_006_6,Mensaje.SERVICE_TYPE_ERROR,
					Mensaje.FILTER_DATACONF_DATAVALIDATION_COMPARE_F_FIN));
			}
		} 
		return salida;
	}

	/**
	 * Los objetos dataconf los convierte en mensajes json
	 * @param objectDataconf es el objeto dataConf
	 * @return el emnsaje json
	 */
	public static String dataconfToJson(Object objectDataconf){
		log4j.debug("&&&& dataconfToJson()--> object: :"+objectDataconf.getClass().getName());
		
		StringBuilder sbResp=new StringBuilder("[{");
		try {
			Field[] arrField = Class.forName(objectDataconf.getClass().getName()).getDeclaredFields();
			log4j.debug("&&&& dataconfToJson()--> arrField="+arrField.length);
			for (Field f : arrField) {
				f.setAccessible(true);
				//Se obtiene el nombre y el valor correspondiente(filtros Dataconf).  
				String propiedad=f.getName();
				String valorDataConf=(String)f.get(objectDataconf);
				log4j.debug("&&&& dataconfToJson()--> propiedad="+propiedad+" valorDataConf="+valorDataConf);
				//Se construye el mensaje json
				if(valorDataConf != null){
					sbResp.append("\"").append(propiedad).append("\":").append(valorDataConf).append(",");
				}
			}
			log4j.debug("&&&& dataconfToJson()--> sbResp_1="+sbResp.toString());
			//Se analiza si no esta vacío el mensaje
			if(sbResp.toString().indexOf(",") > -1){
				//Se elimina la última coma
				sbResp.delete(sbResp.length() -1, sbResp.length());
			}
			sbResp.append("}]");
			log4j.debug("&&&& dataconfToJson()--> sbResp_2="+sbResp.toString());
		} catch (Exception e) {
			sbResp.delete(0, sbResp.length());
			sbResp.append(new MensajeDto(null,null,Mensaje.SERVICE_CODE_000,
						Mensaje.SERVICE_TYPE_FATAL,Mensaje.MSG_ERROR));
			e.printStackTrace();
		} 
		
		return sbResp.toString();
	}
	
	/**
	 * Valida que el parametro sea un tipo entero positivo (ID) valido, por medio de expresion regular
	 * @param stId
	 * @return true si es valido (numerico)
	 */
	public static boolean isValidId(String stId){
		if(stId != null){
			Pattern pattern = Pattern.compile(".*[^0-9].*"); //original
			return !pattern.matcher(stId).matches();
		}
		return false;
	}
	/**
	 * Valida si la cadena parametro recibida es un RFC valido, determinando si es persona o Empresa en tipoRfc
	 * @param rfc Cadena-Rfc a validar
	 * @param tipoRfc 1 es Empresa, cualquier otro caso es persona
	 * @return
	 */
	public static boolean isValidRfc(String rfc, int tipoRfc){
		if(rfc==null || rfc.trim().equals("")){
			return false;
		}
		
		if(tipoRfc==1){
			return Pattern.matches(EXPRESION_REGULAR_RFC_EMPRESA, rfc.toUpperCase());
		}else{
			return Pattern.matches(EXPRESION_REGULAR_RFC_PERSONA, rfc.toUpperCase());
		}
	}
	
	/**
	 * Valida por medio de apache Commons si la cadena es numerica
	 * @param stNumber
	 * @return
	 */
	public static boolean isNumeric(String stNumber){
		return NumberUtils.isNumber(stNumber);
	}
	
	/**
	 * Valida por medio de apache Commons si la cadena es un numero positivo
	 * true si es Long Positivo
	 * @param stNumber
	 * @return
	 */
	public static boolean isPositiveLong(String stNumber){
		boolean res = false;
		
		if(isNumeric(stNumber)){
			if(new Long(stNumber).longValue() > 0){
				res = true;
			}
		}
		return res;
	}
	
	/**
	 * Dado una lista de tokens se crea una cadena con separadores
	 * @param lsString, lista de tokens
	 * @param separator, 
	 * @param numero, true es numero
	 * 				  false no es numero
	 * @return la cadena
	 */
	public static String getStringByList(List<String> lsString, char separator, boolean numero){
		StringBuilder res=null;
		if(lsString != null && lsString.size() > 0){
			res=new StringBuilder();
			Iterator<String> itString= lsString.iterator();
			while(itString.hasNext()){
				if(numero)
					res.append(itString.next()).append(separator);
				else
					res.append("'").append(itString.next()).append("'").append(separator);
			}
		}
		return (res != null ? res.substring(0,(res.length() - 1)).toString():null);
	}
	
//	/**
//	 * Procesa datos de entrada para generar el Id usado en Solr para el Documento respectivo
//	 * @param idEntidad
//	 * @param tipoEntidad
//	 * @return
//	 */
//	public static String obtieneIdSolr(String idEntidad, String tipoEntidad){
//		if(idEntidad!=null && tipoEntidad!=null){
//			return tipoEntidad.concat("-").concat(idEntidad);
//		}
//		return null;
//	}
	
	/**
	 * Obtiene el primer elemento  de la expresión proporcionada
	 * @param String str Expresion
	 * @return String str firstToken
	 * @author Osy
	 */
	public static String firstExpElement(String str){
		log4j.debug("<firstExpElement> Inicio...str :" + str);
		String element = str;
		boolean findPattern;
		
		Pattern p = Pattern.compile(".+(?=<)|.+(?=\\={2})|.+(?=>)|.+(?=\\!)");
		Matcher m = p.matcher(str);
		findPattern = m.find();
		log4j.debug("<firstExpElement> m.find() :" + findPattern);
		if(findPattern){
			log4j.debug("<firstExpElement> Patrón encontrado :" + m.group().toString());
			element = m.group().toString();										
		}

		element = element.trim();
		element = element.replace(".size()", "");		
		element = element.replace("(", "");		
		element = element.replace(")", "");		
		log4j.debug("<firstExpElement> End...element :" + element);
		return element;
	}
	
	
	/**
	 * Elimina los caracteres reservados, para evitar inyeccion de comandos y 
	 * acronimos comunes para mejorar la busqueda de empresas
	 * @param texto
	 * @return
	 */
	public static String cleanFreeText(String texto){
		if(texto!=null){
			final String[] INVTOKENS = {"%","&","#","\"", "$","!","?","¿","|", "=", "S.A.", "C.V.","S.C.", ";", "."};//punto siempre debe ir al final
			for(String token : INVTOKENS){
				texto = texto.replace(token, "");
	    	}
	    	return texto;
		}
		return null;
	}
	
	/**
	 * Pondera la similitud de dos cadenas
	 * @param searchName, string1
	 * @param candidateName,  string2
	 * @return un valor ponderado, si es 0 no fue mayor al maximo de similitud
	 */
	public static float similarity(String searchName, String candidateName) {
	  float score= new JaroWinkler().getSimilarity(searchName, candidateName);
	  if(score >= MAX_SCORE_SIMILARITY){
		  //Se verifica si el promedio entre Levenshtein y JaroWinkler es mayor al valor establecido
		  if(((new Levenshtein().getSimilarity(searchName, candidateName) +score)/2) < MAX_SCORE_SIMILARITY){
			  score= 0.0f;
		  }		  
	  }else{
		  score= 0.0f;
	  }
	  return score;
	}
	
	/**
	 * Ejecuta el paradigma de reflexion dado un objeto y el nombre de la propiedad o metodo, a aplicar
	 * @param object, objeto que se aplica reflexion
	 * @param name, nombre de la propiedad o metodo
	 * @param setProperty, el objeto que se va a inyectar solo a la propiedad
	 * @param argumentTypes,(solo para metodos)  se define un array de tipos de datos de los argumentos del metodo correspondiente
	 * @param arguments, (solo para metodos)  se define un array de los argumentos del metodo correspondiente
	 * @return object o null(si hubo un error)
	 */
	public static Object executeReflexion(Object object, String name,Object setProperty, 
							Class<?>[] argumentTypes,Object[] arguments) {
		Object objectResp=null;
		/*log4j.debug("#$% executeReflexion --> object="+object.getClass().getName()+
					", name="+name+
					", argumentTypes="+argumentTypes+
					", arguments="+arguments);*/
		try {
			//para una propiedad
			if(argumentTypes == null && arguments == null){
				//Field field = object.getClass().getDeclaredField(name);
				Field field = findUnderlying(object.getClass(),name);
				field.setAccessible(true);
				//se aplica el get
				if(setProperty == null){
					objectResp = field.get(object);
				//Se aplica el set
				}else{
					field.set(object,setProperty);
					objectResp=new Object();
				}
				
			//para un metodo	
			}else if(argumentTypes != null && arguments != null){
				// Class<?> clz =object.getClass();
				 Method method=object.getClass().getMethod(name, argumentTypes);
				 method.setAccessible(true);
				 objectResp =method.invoke(object.getClass().newInstance(), arguments);
			}
		} catch (Exception e) {
			log4j.error("Error al aplicar reflexion: object="+object.getClass().getName()+
						", name="+name+
						", argumentTypes="+argumentTypes+
						", arguments="+arguments);
			log4j.error("Error al aplicar reflexion:", e);
			e.printStackTrace();
		}
		//log4j.debug("#$% executeReflexion --> objectResp2:"+objectResp);
		return objectResp;
	}
	
	/**
	 * Busca la propiedad en la clase o superclase
	 * @param clazz
	 * @param fieldName
	 * @return
	 */
	public static Field findUnderlying(Class<?> clazz, String fieldName) {
	    Class<?> current = clazz;
	    do {
	       try {
	           return current.getDeclaredField(fieldName);
	       } catch(Exception e) {}
	    } while((current = current.getSuperclass()) != null);
	    return null;
	}
	
	/**
	 * Crea un string con la informacion de un archivo
	 * @param path, la ubicacion del archivo
	 * @param encoding, el charset
	 * @return 
	 * @throws IOException
	 */
	public static String readFile(String path, Charset encoding)  throws IOException {
		  return new String(Files.readAllBytes(Paths.get(path)), encoding);
		}
	
	
	/*  *********************** STRING-FILTERS (Vista y Reportes pdf)  ***************************** */
	public static String filtSiNo(String value){
		if(value==null || value.trim().equals("")){
			return "No";
		}else{
			if(value.equals("1")){
				return "Si";
			}else{
				return "No";
			}
		}
	}
	
	public static String filtFormtSalary(String value){
		if(value==null || value.trim().equals("")){
			return "$ 0.00";
		}else{
			try{
				NumberFormat nf = new DecimalFormat("$ #,###.00");
				
				Double dbl = new Double(value); 
						//nf.parse(value).doubleValue();
				return nf.format(dbl);
			}catch(Exception e){
				return "$ ";
			}
		}
	}
	public static String filtXLongLine(String email){
		if(email!=null){
			if(email.length()>25){
				if(email.indexOf("@")>25){
					email = email.substring(0,25)+(email.substring(25,email.length()).replace("@", "\n@"));
				}else{
					email=email.replace("@", "\n@");
				}
			}
		}
		return email;
	}
	
	public static String filtSinDef(String value){
		if(value==null || value.trim().equals("")){
			return "-Sin Definir-";
		}
		return value;
	}
	
	public static String filtFechaRang(String anIni, String anFin, String act){
		if(anIni==null){
			anIni = "";
		}
		if(anFin==null ){
			if(act!=null && act.equals("1")){
				anFin = "Actual";
			}else{
				anFin = "";
			}
		}
		return anIni+" - "+anFin;
	}
	
	public static String filtNotNull(String value){
		if(value == null){
			value = "";
		}
		return value;
	}
	
	

	/*public static void main(String[] args) {
		
		//System.out.println("isNumeric: " +isNumeric("4"));
		//System.out.println("nvl: " +nvl(null, "ddd"));
		//para una propiedad
		/*Object resp=executeReflexion(new CurriculumDto("4636",null),"idPersona",null, null,null);
		log4j.debug("#$% respField="+resp);
		log4j.debug("//////////////////");*/
		//para un metodo
		/*AreaTextoDto areaTextoDto=new AreaTextoDto();
		areaTextoDto.setPonderacion("98878");
		Class<?>[] argumentTypes = { int.class, AreaTextoDto.class};
		Object[] arguments = { new Integer(23), areaTextoDto};
		Object resp=executeReflexion(new CurriculumDto(),"prueba", argumentTypes,arguments);
		log4j.debug("#$% respMethod="+resp);
		log4j.debug("#$% getPonderacion="+((AreaTextoDto)resp).getPonderacion());
		log4j.debug("********************************************************************--------------------");*/
				//Class<?>[] argumentTypes,Object[] arguments
		/*String mensaje = "<b>Su cuenta esta creada,<prueba> pero es necesario confirmar de lo contrario esta se eliminara del sistema. </b><br/>Para dar de alta su cuenta, por favor haga click en el siguiente enlace <br/> <a href=\" <linkConfirmacion> \" target=\"_blank\" >Tce login</a>";
		HashMap<String, String> hmEtiquetas=new HashMap<String, String>();
		hmEtiquetas.put("linkConfirmacion", "HOOOOOOOOOOOOOOOOOLLLLLLLLLLLLLLLAAAAAAAAAAA");
		hmEtiquetas.put("prueba", "TURURUUUUUUU");
		System.out.println("mensaje=" +reemplazarEtiquetas(hmEtiquetas , mensaje));
		
		System.out.println("es numero positivo " + cadena + "?");
		System.out.println(isPositiveLong(cadena));*/
		//double num=-0.2525;
		
		//System.out.println("round1:"+UtilsTCE.redondear(num,1,RoundingMode.HALF_UP));
		
		//System.out.println("round2:"+new BigDecimal(String.valueOf(num)).setScale(1,RoundingMode.CEILING).toPlainString() );
		/*String cad="Licenciatura de Economía";
		System.out.println(filtroGramaTexto(cad));
	
		String boleano = "1"; //true | 1 | algo
		try{
			System.out.println("evaluando boleano: " + boleano );
			if(boleano!=null && Boolean.parseBoolean(boleano)){
				System.out.println("se calculan datos");
			}else{
				System.out.println("no se calculan");
			}
		}catch (Exception e){
			e.printStackTrace();
		}*/
		
		//System.out.println("distGeo: " +distGeo(50.06639, -5.71472, 58.64389, -3.07000));
	//}
}
