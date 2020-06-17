package net.tce.util;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;


public class DBInterpreter {
	
	
	@Value("${hibernate_manager}")
    private  String hibernate_manager;
	
	 Logger log4j = Logger.getLogger( DBInterpreter.class );
	
	/*public  void main(String[] args) {
//		String param = "POSICION.idPosicion";
//		String res = fnToChar(param, 0);
//		System.out.println(res);
		System.out.println(fnBoolean("1"));
	}*/
	
	/**
	 * Interpreta numero (1/0) para obtener booleano para BD en Sistema
	 * @param param
	 * @return
	 */
	public  String fnBoolean(String param){
		if(param==null){
			param = "0";
		}
//		if(Constante.DB_MANAGER_PSG.toUpperCase().equals(hibernate_manager)){
			return param.trim().equals("1")?"true":"false";
//		}
//		return param;
	}
	
//	/**
//	 * Obtains 'to_Char' valid function for DB
//	 * @param param Pojo's parameter into function
//	 * @param type format output (for Postgre) number=0,Date=1 +*
//	 * @return
//	 * 
//	 * +*. For Number using FM9999999999999999999, for Date: DD/MM/YY
//	 */
//	public  String fnToChar(String param, int type){
//		String toCharted = " to_char(";
//		if(param!=null){
//			if(Constante.DB_MANAGER_PSG.toUpperCase().equals(hibernate_manager)){
//				if(type==0){	//number => to_char(PARAM, 'FM9999999999999999999')
//					toCharted+=param.concat(",").concat(Constante.PSG_TOCHAR_BIGINT);
//				}else if(type==1) {//date => 
//					toCharted+=param.concat(",").concat(Constante.TOCHAR_DATE);
//				}else{
//					toCharted+=param;
//				}
//			}else if(Constante.DB_MANAGER_ORACLE.toUpperCase().equals(hibernate_manager)){
//				if(type==1) {//date => 
//					toCharted+=param.concat(",").concat(Constante.TOCHAR_DATE);
//				}else{				
//					toCharted+=param;				
//				}
//				
//			}else{
//				toCharted+=param;
//			}
//			toCharted+=")";
//			return toCharted;
//		}else{
//			return param;
//		}
//	}
	 
	
//	public  Object getFilterForBoolean(String valorBooleano){
//		if(valorBooleano!=null){
//			if(Constante.DB_MANAGER_PSG.toUpperCase().equals(hibernate_manager)){
//				if(valorBooleano.trim().equals("1")){
//					return new Boolean(true);
//				}else{
//					return new Boolean(false);
//				}
//			}else if(Constante.DB_MANAGER_ORACLE.toUpperCase().equals(hibernate_manager)){
//				//Long.parseLong(valorBooleano);
//				return new Long(valorBooleano);
//			}
//		}
//		return valorBooleano;
//	}
	
	
	public  StringBuffer getSecuencia(final String nombreSeq) {
		StringBuffer query=new StringBuffer();
		//log4j.debug("DB_APP_MANAGER: " + hibernate_manager );
//		if(Constante.DB_MANAGER_PSG.toUpperCase().equals(hibernate_manager)){
			query.append(" select nextval ('").append(nombreSeq).append("') ");
//		}else if(Constante.DB_MANAGER_ORACLE.toUpperCase().equals(hibernate_manager)){
//			query.append(" select ").append(nombreSeq).append(".nextval from dual ");
//		}else{ //Default Oracle
//			query.append(" select ").append(nombreSeq).append(".nextval from dual ");
//		}
		
		return query;
	}
	
//	public  String fnToCharBooleanEval(String tablePropertie){
//		if(tablePropertie!=null && !tablePropertie.trim().equals("")){
//				if(Constante.DB_MANAGER_ORACLE.toUpperCase().equals(hibernate_manager)){
//					return " to_char("+tablePropertie+") ";
//				}else{
//					return " (case when "+tablePropertie+" is true then '"+Constante.ESTATUS_REGISTRO_ACTIVO_S+"' else '" + Constante.ESTATUS_REGISTRO_INACTIVO_S +"' end) ";
//				}
//		}
//		return null;
//	}

}
