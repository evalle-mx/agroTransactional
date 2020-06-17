package net.tce.util;


import java.util.Calendar;
//import java.util.Date;
import java.util.HashMap;

/**
 * 
 * @author Goyo
 *
 */
public abstract class Constante {

	/* Comunes 	 */
//	//BOOLEANOS DE EVALUACION DE PARAMETROS REQUERIDOS DE INDICES EN ALTA DE CV MASIVO
//	//public final static Boolean MASIVE_IPG = new Boolean(true), MASIVE_IAS = new Boolean(true), MASIVE_IAP = new Boolean(true);
	public final static String OS = System.getProperty("os.name").toLowerCase();
//	public final static String IDCONF_DEFAULT="0";
//	public final static String ID_EMPRESA_ZERO_SISTEMA = "0";
//	public final static String EMPTY="";
	
//	
//	
	//HTTP
	public final static String DIVISOR_HTTP=" -HTTP- ";
	public final static int HTTP_ERROR_CLIENT_4xx_INI=400;
	public final static int HTTP_ERROR_CLIENT_4xx_FIN=431;	
//	
//	//Publicacion
//	public final static String PUBLICACION_FIELD_NAME="entity";
//	public final static String PUBLICACION_CV="cv";
//	public final static String PUBLICACION_EMPRESA="em";
//	public final static String PUBLICACION_POSICION="ps";
//	
//	// EmpresaParametro:Publicacion unica
//	public final static String IDCONF_EMPRESA_DEFAULT="1"; //
//	
//	//Lo usa octavio
//	public final static String IDCONF_NULL="N"; //TODO documentar en encabezado
//	
//	/**
//	 * EmpresaParametro:Publicación masiva(Create)
//	 */
//	public final static String IDCONF_MASIVO="2";//EmpresaParametro:Publicación masiva(Create)	
//	public final static String NO_PARCIAL="0";
	public final static String ACEPT_REST_JSON="Accept=application/json";
//	public final static byte CLASIFICACION_CONTACTO_TEL_CEL=1;
//	public final static byte CLASIFICACION_CONTACTO_LINK=2;
//	public final static byte CLASIFICACION_CONTACTO_APP=3;
//	public final static byte CLASIFICACION_CONTACTO_EMAIL=4;
//	public final static byte CLASIFICACION_CONTACTO_CHAT=5;
//	public final static int ANIO_NACIMIENTO_BASE_MIN=(DateUtily.getCalendar().get(Calendar.YEAR))-75;
//	public final static int MES_INICIO_DEFAULT = 1;
//	public final static int MES_FIN_DEFAULT = 12;
//	public final static int DIA_INICIO_DEFAULT = 1;
//	public static final String DEFAULT_PATTERN_DATE = "dd/MM/yyyy";
//	public static final boolean ENVIO_ALLDATA_FECHA_ERROR = false;
//	
//    public final static int ANIO_NACIMIENTO_BASE_MAX=(DateUtily.getCalendar().get(Calendar.YEAR))-17;
//	public final static int ANIO_PROGRAMACION_BASE_MIN=(DateUtily.getCalendar().get(Calendar.YEAR));
//    public final static int ANIO_PROGRAMACION_BASE_MAX=(DateUtily.getCalendar().get(Calendar.YEAR))+3;
//    public final static int ANIO_ACTUAL=(DateUtily.getCalendar().get(Calendar.YEAR));
//	public final static byte PERSONA=1;
//	public final static byte EMPRESA=2;
//	public final static String AFIRMATIVO="si";
//	public final static int T_DIAS_CADUCIDAD = 30;//DIAS PARA CONFIRMAR-CORREO (T) O ELIMINAR (2T) CUENTA INACTIVA.
//	public final static byte MEXDF=9;
//	public final static String MEXDFDESC="DISTRITO FEDERAL";
//	public final static int EARTH_RADIUS=6371;
//	
//	//avisos
//	public final static String AVISO_EMPTY_PER_TRAB="EMPTY_PER_TRAB";
//	public final static String AVISO_EMPTY_DISP_HORA="EMPTY_DISP_HORA";
//	public final static String AVISO_EMPTY_CAMB_DOM="EMPTY_CAMB_DOM";
//	public final static String AVISO_EMPTY_EDO_CIVIL="EMPTY_EDO_CIVIL";
//	public final static String AVISO_WARN_TIPO_JORNADA="WARN_TIPO_JORNADA";
//	public final static String AVISO_EMPTY_TIPO_JORNADA="EMPTY_TIPO_JORNADA";
//	
//	//candidato
//	public final static boolean CANDIDATO_NO_CLASIFICADO=false;
//
//	// Estatus del candidato 
//	public final static byte ESTATUS_CANDIDATO_ACEPTADO=1;
//	public final static byte ESTATUS_CANDIDATO_RECHAZADO_SALARIO=2;
//	public final static byte ESTATUS_CANDIDATO_RECHAZADO_DISTANCIA=3;
//	public final static byte ESTATUS_CANDIDATO_RECHAZADO_DEMOGRAFICOS=4;
//	public final static byte ESTATUS_CANDIDATO_RECHAZADO_ACADEMICA=5;
//	public final static byte ESTATUS_CANDIDATO_RECHAZADO_LABORAL=6;
//	public final static byte ESTATUS_CANDIDATO_RECHAZADO_HABILIDADES=7;
//	public final static byte ESTATUS_CANDIDATO_POR_CALCULAR=8;
//	public final static byte ESTATUS_CANDIDATO_INACTIVO=9;
//	
//	// Estatus operativo del candidato
//	public final static Byte ESTATUS_CANDIDATO_OPERATIVO_NUEVO=1;
//	public final static Byte ESTATUS_CANDIDATO_OPERATIVO_VISTO=2;
//	public final static Byte ESTATUS_CANDIDATO_OPERATIVO_INVITADO=3;
//	public final static Byte ESTATUS_CANDIDATO_OPERATIVO_INACTIVO=4;
//	public final static Byte ESTATUS_CANDIDATO_OPERATIVO_INTERESADO=5;
//	public final static Byte ESTATUS_CANDIDATO_OPERATIVO_NO_INTERESADO=6;
//	public final static Byte ESTATUS_CANDIDATO_OPERATIVO_ACEPTADO	=7;
//	public final static Byte ESTATUS_CANDIDATO_OPERATIVO_RECHAZADO=8;
//	public final static Byte ESTATUS_CANDIDATO_OPERATIVO_SELECCIONADO=9;
//
//	// Estatus inscripcion
//	public final static Byte ESTATUS_INSCRIPCION_CREADO=1;
//	public final static Byte ESTATUS_INSCRIPCION_ACTIVO=2;
//	public final static Byte ESTATUS_INSCRIPCION_PUBLICADO=3;
//	public final static Byte ESTATUS_INSCRIPCION_INACTIVO=4;
//	public final static Byte ESTATUS_INSCRIPCION_BORRADO=5;
//	public final static Byte ESTATUS_INSCRIPCION_LISTA_NEGRA=6;
//	public final static String EXPRESION_REGULAR_RFC_PERSONA = "^[A-Z]{4}[0-9]{6}[A-Z0-9]{3}$";
//
//	// Estatus de la posicion
//	public final static Byte ESTATUS_POSICION_CREADA=1;
//	public final static Byte ESTATUS_POSICION_PUBLICADA=2;
//	public final static Byte ESTATUS_POSICION_ERROR=3;
//	public final static Byte ESTATUS_POSICION_DESACTIVADA=6;
//	
//	// Estatus del perfil
//	public final static Byte ESTATUS_PERFIL_CREADO=1;
//	public final static Byte ESTATUS_PERFIL_PUBLICADO=2;
//	public final static Byte ESTATUS_PERFIL_DESACTIVADA=6;
//
//	//Ponderacion perfil_posicion
//	public final static int PONDERACION_PERFIL_INTERNO = 1;
//	// empresa
////	public final static byte ID_RELACION_EMPRESA_PERSONA_DEFAULT=7;
//	public final static String EXPRESION_REGULAR_RFC_EMPRESA = "^[A-Z]{3}[0-9]{6}[A-Z0-9]{3}$";
//	public final static boolean ESTATUS_REGISTRO_ACTIVO = true;
//	public final static String ESTATUS_REGISTRO_ACTIVO_S = "1";
//	public final static boolean ESTATUS_REGISTRO_INACTIVO = false;
//	public final static String ESTATUS_REGISTRO_INACTIVO_S = "0";
//	
//	// Tipo de perfil
//	public final static byte TIPO_PERFIL_PUBLICO=1;
//	public final static byte TIPO_PERFIL_PRIVADO=2;
//	public final static byte TIPO_PERFIL_INTERNO=4;
//	public final static String NOMBRE_PERFIL_INTERNO="Interno";
//	/** BORRADO_PERFIL_FISICO: true= borrado fisico, false= borrado logico */
//	public final static boolean BORRADO_PERFIL_FISICO = false;
//	
//	// Rol
//	public final static Byte ROL_ADMINISTRADOR=1;
//
//	//Permiso
//	public final static Byte TIPO_PERMISO_URICODE=1;
//	public final static Byte TIPO_PERMISO_MENU=2;
//	public final static Byte TIPO_PERMISO_MENU_CATALOGO=3;
//
//	
//	/**
//	 * Empresa Parametros BD
//	 */
//	public final static String QOS_APPLICANTS="qosApplicants";
//	public final static String TIPO_PARAMETRO_4="4";
//	public final static String TIPO_PARAMETRO_APLICACION_GENERAL="4";
//	public final static String TIPO_PARAMETRO_ATRIBUTO_REQUERIDO="1";	
//	public final static String TIPO_PARAMETRO_ATRIBUTO_REQUERIDO_POSICION="5";
//	public final static String TIPO_PARAMETRO_ATRIBUTO_REQUERIDO_EMPRESA="6";	
//	public final static byte IDCONF_CVMASIVOS=1;
//	public final static String QOS_APPLICANTS_DEFAULT="3";
//	
//	/**
//	 * Secuencias BD
//	 */
//	public final static String SECUENCIA_BD_AREA_PERSONA="SEQ_AREA_PERSONA";
//	public final static String SECUENCIA_BD_AREA_PERFIL="SEQ_AREA_PERFIL";
//	public final static String SECUENCIA_BD_AREA_EMPRESA="SEQ_AREA_EMPRESA";
//	public final static String SECUENCIA_BD_AREA_TEXTO="SEQ_AREA_TEXTO";
//	public final static String SECUENCIA_BD_SEQ_AVISO="SEQ_AVISO";
//	public final static String SECUENCIA_BD_SEQ_AVISO_CAND="SEQ_AVISO_CANDIDATO";
//	public final static String SECUENCIA_BD_PERSONA="SEQ_PERSONA";
//	public final static String SECUENCIA_BD_HISTORICO_PASSWORD="SEQ_HISTORICO_PASSWORD";
//	public final static String SECUENCIA_BD_CONTACT="SEQ_CONTACTO";
//	public final static String SECUENCIA_BD_DOMICILIO="SEQ_DOMICILIO";
//	public final static String SECUENCIA_BD_NOTIFICACION="SEQ_NOTIFICACION";	
//	public final static String SECUENCIA_BD_TELEFONO="SEQ_TELEFONO";
//	public final static String SECUENCIA_BD_PERSONA_PAIS="SEQ_PERSONA_PAIS";
//	public final static String SECUENCIA_BD_EXPERIENCIA_LABORAL="SEQ_EXPERIENCIA_LABORAL";
//	public final static String SECUENCIA_BD_TEXTO_NGRAM="SEQ_TEXTO_NGRAM";
//	public final static String SECUENCIA_BD_HABILIDAD="SEQ_HABILIDAD";
//	public final static String SECUENCIA_BD_REFERENCIA="SEQ_REFERENCIA";
//	public final static String SECUENCIA_BD_ESCOLARIDAD="SEQ_ESCOLARIDAD";
//	public final static String SECUENCIA_BD_TEXTO_CONOCIMIENTO="SEQ_TEXTO_CONOCIMIENTO";
//	public final static String SECUENCIA_BD_CONTENIDO="SEQ_CONTENIDO";
//	public final static String SECUENCIA_BD_CANDIDATO="SEQ_CANDIDATO";	
//	public final static String SECUENCIA_BD_CONTROL_PROCESO="SEQ_CONTROL_PROCESO";	
//	public final static String SECUENCIA_BD_PERSONA_PERFIL="SEQ_PERSONA_PERFIL";
//	public final static String SECUENCIA_BD_DOCUMENTO_CLASIFICACION="SEQ_DOCUMENTO_CLASIFICACION";	
//	public final static String SECUENCIA_BD_POSICION="SEQ_POSICION";
//	public final static String SECUENCIA_BD_PERFIL_POSICION="SEQ_PERFIL_POSICION";
//	public final static String SECUENCIA_BD_PERFIL_TEXTO_NGRAM="SEQ_PERFIL_TEXTO_NGRAM";
//	public final static String SECUENCIA_BD_COMPETENCIA_PERFIL="SEQ_COMPETENCIA_PERFIL";	
//	public final static String SECUENCIA_BD_POLITICA_VALOR="SEQ_POLITICA_VALOR";
//	public final static String SECUENCIA_BD_POLITICA_M_GENERO="SEQ_POLITICA_M_GENERO";
//	public final static String SECUENCIA_BD_POLITICA_M_HABILIDAD="SEQ_POLITICA_M_HABILIDAD";
//	public final static String SECUENCIA_BD_POLITICA_M_DISP_VIAJAR="SEQ_POLITICA_M_DISP_VIAJAR";
//	public final static String SECUENCIA_BD_POLITICA_M_ESTADO_CIVIL="SEQ_POLITICA_M_ESTADO_CIVIL";
//	public final static String SECUENCIA_BD_POLITICA_ESCOLARIDAD="SEQ_POLITICA_ESCOLARIDAD";
//	public final static String SECUENCIA_BD_POLITICA_M_TIPO_JORNADA="SEQ_POLITICA_M_TIPO_JORNADA";
//	public final static String SECUENCIA_BD_POLITICA_MVALOR = "SEQ_POLITICA_M_VALOR";
//	public final static String SECUENCIA_BD_PERFIL="SEQ_PERFIL";
//	public final static String SECUENCIA_BD_EMPRESA="SEQ_EMPRESA";
//	public final static String SECUENCIA_BD_EMPRESA_PERFIL="SEQ_EMPRESA_PERFIL";
//	public final static String SECUENCIA_BD_REL_EMPRPERSONA="SEQ_RELACION_EMPRESA_PERSONA";
//	public final static String SECUENCIA_BD_CANDIDATO_RECHAZO="SEQ_CANDIDATO_RECHAZO";
//	public final static String SECUENCIA_BD_AREA = "SEQ_AREA";
//	public final static String SECUENCIA_BD_CORECLASS = "SEQ_CORE_CLASS";	
//	public final static String SECUENCIA_BD_PERSONAIDIOMA = "SEQ_PERSONAIDIOMA";//	seq_persona_idioma
//	public final static String SECUENCIA_BD_POSICIONIDIOMA = "SEQ_POSICIONIDIOMA";//	seq_posicion_idioma
//	public final static String SECUENCIA_BD_PERMISO="SEQ_PERMISO";
//	public final static String SECUENCIA_BD_ROL_PERMISO="SEQ_ROL_PERMISO";
//	public final static String SECUENCIA_BD_ROL="SEQ_ROL";
//	public final static String SECUENCIA_BD_CERTIFICACION="SEQ_CERTIFICACION";
//	public final static String SECUENCIA_BD_BONO="SEQ_BONO";
//	public final static String SECUENCIA_BD_VALE="SEQ_VALE";
//	public final static String SECUENCIA_BD_SEGURO="SEQ_SEGURO";
//	public final static String SECUENCIA_BD_ENC_RESPUESTA="SEQ_ENC_RESPUESTA";
//	
//	//bitacora posicion
//	public final static String SECUENCIA_BD_BTC_COMPETENCIA_PERFIL="SEQ_BITACORA_COMPETENCIA_PERFIL";
//	public final static String SECUENCIA_BD_BTC_DOMICILIO="SEQ_BITACORA_DOMICILIO";
//	public final static String SECUENCIA_BD_BTC_PERFIL_TEXTO_NGRAM="SEQ_BITACORA_PERFIL_TEXTO_NGRAM";
//	public final static String SECUENCIA_BD_BTC_POLITICA_VALOR="SEQ_BITACORA_POLITICA_VALOR";
//	public final static String SECUENCIA_BD_BTC_POSICION="SEQ_BITACORA_POSICION";
//	
//	//tracking
//	public final static String SECUENCIA_BD_TCK_MONITOR_PERSONA="SEQ_TRACKING_MONITOR_PERSONA";
//	public final static String SECUENCIA_BD_TCK_ESQUEMA_PERSONA="SEQ_TRACKING_ESQUEMA_PERSONA";
//	public final static String SECUENCIA_BD_TCK_ESQUEMA="SEQ_TRACKING_ESQUEMA";
//	public final static String SECUENCIA_BD_TCK_PLANTILLA="SEQ_TRACKING_PLANTILLA";
//	public final static String SECUENCIA_BD_TCK_BITACORA="SEQ_BITACORA_TRACKING";
//	public final static String SECUENCIA_BD_POSIBLE_CANDIDATO="SEQ_POSIBLE_CANDIDATO";
//	public final static String SECUENCIA_BD_ESQUEMA_PERFIL="SEQ_ESQUEMA_PERFIL";
//	public final static String SECUENCIA_BD_PLANTILLA_ROL="SEQ_PLANTILLA_ROL";
//	
//	/**
//	 * DAO'S
//	 */
//	public final static String SQL_ORDERBY="orderby";
//	public final static String SQL_KEY_SIMILARITY="key_similarity";
//	public final static String SQL_VALUE_SIMILARITY="value_similarity";
//	
//	/**
//	 * URI's
//	 */
	//Metodos CRUD* generales
	public final static String URI_UPDATE="/update";
	public final static String URI_MULTIPLE_UPDATE="/updmultiple";
	public final static String URI_RELOAD="/reload";
	public final static String URI_CREATE="/create";
	public final static String URI_RECOVERY_MAIL="/recovery";
	public final static String URI_RESTORE_PWD="/restore";
	public final static String URI_UPD_PWD="/updpwd";
	public final static String URI_READ="/read";
	public final static String URI_DELETE="/delete";
	public final static String URI_GET="/get";
	public final static String URI_DATA_CONF="/dataconf";
	public final static String URI_EXISTS="/exists";
	public final static String URI_INITIAL="/initial";
	public final static String URI_CLONE="/clone";
	public final static String URI_FIND_SIMILAR="/findSimilar";
	public final static String URI_PING="/ping";
	public final static String URI_ENABLE_EDITION="/enableEdition";
	public final static String URI_DISASSOCIATE="/disassociate";
	public final static String URI_CREATECOMPLETE = "/createcomplete";
	public final static String URI_CREATEMASIVE = "/createMasive";
	public final static String URI_DATA_CONF_MASIVE="/dataconfmasive";
	public final static String URI_CREATE_ASSOCIATION = "/requestAssociation";
	public final static String URI_UPD_ASSOCIATION = "/updateAssociation";
	public final static String URI_DISABLE="/disable";	
	public final static String URI_UPDATE_STATUS="/updatestatus";
	public final static String URI_GET_ASSOCIATES="/getAssociates";
	public final static String URI_URICODES="/uricodes";
	public final static String URI_MERGE = "/merge"; //Usado en API (LinkedIn)
	public final static String URI_TEXT_CREATE = "/textcreate";
	public final static String URI_TEXT_UPDATE = "/textupdate";
	public final static String URI_TEXT_DELETE = "/textdelete";
	public final static String URI_TEXT_GET = "/textget";
	public final static String URI_MENU = "/menu";
	public final static String URI_SET= "/set";
//	
//	
//	/**
//	 * Services
//	 */	
//	//Dataconf
//	public final static String RESTRICTION_ALFA="alfa";
//	public final static String RESTRICTION_ALFANUM="alfanum";
//	public final static String RESTRICTION_NUMBERS="numbers";
//	public final static String RESTRICTION_NEGATIVES="negatives";
//	public final static String SIN_RESTRICTION_="sinRestriction";
//	public final static String PATERRN_EMAIL="email";
//	public final static String PATERRN_PASSWORD="password";
//	public final static Byte PARAMETRO_DATACONF=8;
//	
	//Metodos
	public final static int M_CREATE = 1;
	public final static int M_UPDATE = 2;
	public final static int M_READ = 3;
	public final static int M_DELETE = 4;
	public final static int M_GET =5 ;
//	public final static int F_DATACONF = 6;
//	public final static int F_EXISTS = 7;
//	public final static int F_CLONE = 8;
//	public final static int F_ENABLE_EDITION = 9;
	public final static int M_SEARCH = 10;
//	public final static int F_UPDATE_STATUS= 11;
//	public final static int F_CREATECOMPLETE = 12;
//	public final static int F_INITIAL = 13;
//	public final static int F_URICODES = 14;
//	public final static int F_MENU = 15;
//	public final static int F_UPDATEPWD = 16;
//	public final static int F_UPDATE_ASSING_PERMS = 17;
//	public final static int F_ADMIN = 18;
//	
	public final static int M_FULLTRACK = 20;
//
//	
//	//Service Admin
//	public final static String URI_ADMIN="/admin/management";
//	public final static String URI_LASTDATE_SYNC_DOCS = "/lastDateSyncDocs";
//	public final static String URI_LASTDATE_REMODEL = "/lastDateRemodel";
//	public final static String URI_LASTDATE_RELOAD_CORE_SOLR = "/lastDateReLoadCoreSolr";
//	public final static String URI_LASTDATE_RECLASS_DOCS = "/lastDateReclassDocs";
//	public final static byte MODO_ERROR_FATAL = 1;
//
//	
//	
//	//Service Catalogue
//	public final static String URI_CATALOGUE="/admin/catalogue";
//	public final static String URI_CATALOGUE_GET="/getCatalogue";
//	public final static String URI_CATALOGUE_GET_FILTER="/getCatalogueByFilter";
//	public final static String URI_CATALOGUE_CREATE="/createCatalogueRecord";
//	public final static String URI_CATALOGUE_UPDATE="/updateCatalogueRecord";
//	public final static String URI_CATALOGUE_AREA="/getAreas"; //Uso particular de Documentos clasificados
//	public final static String URI_CATALOGUE_GET_VALUES="/getCatalogueValues";
//
//	//Service Settlement
//	public final static String URI_SETTLEMENT="/module/settlement";
//	public final static String URI_SETTLEMENT_CREATE="/create";
//	
//	//Service Contact
//	public final static String URI_CONTACTO="/module/contact";
//	
//	//Service PersonSkill
//	public final static String URI_PERSONA_HABILIDAD="/module/personSkill";
//	
//	//Service PersonCert
//	public final static String URI_PERSONA_CERTIFICACION="/module/personCert";
//	
//	//Service Language
//	public final static String URI_LANGUAGE = "/module/language";
//	
//	//Service Permission
//	public final static String URI_PERMISSION = "/admin/permission";
//	
//	//Service Rol
//	public final static String URI_ROL = "/admin/rol";
//	public final static String URI_ROL_ASSIGN_PERMS ="/assignPerms";	
//	
//	//Service TipoPermiso
//	public final static String URI_TIPO_PERMISO = "/admin/tipoPermiso";
//	
//	//Service CurriculumManagement
//	public final static String URI_CURRICULUM="/module/curriculumManagement";
//	public final static String MODO_EMAIL_CONFIRMACION="1";
//	public final static Byte SIZE_CLAVE_ALEATORIA_MIN=15;
//	public final static Byte SIZE_CLAVE_ALEATORIA_MAX=18;
//	public final static Byte SIZE_ID_ENCRIPTADO=32;
////	public final static Byte DELETE_NOTIFICATION_ENVIO_POSICION=1;
//	//public final static Byte DELETE_NOTIFICATION_ENVIO_CANDIDATO=2;
//	
//	//Service URI_COMPENSACION
//	public final static String URI_COMPENSACION = "/module/compensation";
//	
//	//Service Curriculum Empresarial
//	//public final static String URI_CURENTERPRISE="/module/curriculumEnterprise";
//	public final static String URI_CURRICULUM_EMPRESA="/module/curriculumCompany";
//	public final static String URI_CURRICULUM_EMPRESA_PUBLICATION="/setEnterpriseResumePublication";
////	public final static String URI_REST_TASK="/module/task";
//	public final static String URI_TEST="/test";
//
//	//Service password
//	public final static String URI_PASSWORD="/module/password";
//	
//	//Service WorkExperience
//	public final static String URI_WORK_EXPERIENCE="/module/workExperience";
//		
//	//Service textoNgram
//	//public final static String URI_TEXTO_NGRAM="/module/textNgram";
//	
//	//Service workReference
//	public final static String URI_WORK_REFERENCE="/module/reference";
//
//	//Service academicBackground
//	public final static String URI_ACADEMIC_BACKGROUND="/module/academicBackground";
//	
//	//Service automovil
//	public final static String URI_AUTOMOVIL="/module/automobile";
//	
//	//Service sueldo
//	public final static String URI_SUELDO="/module/salary";
//	
//	//Service bono
//	public final static String URI_BONO="/module/bond";
//	
//	//Service Seguro
//	public final static String URI_SEGURO="/module/insurance";
//	
//	//Service Seguro
//	public final static String URI_PLAN="/module/plan";
//	
//	//Service vale
//	public final static String URI_VALE="/module/voucher";
//	
//	//Service vacaciones
//	public final static String URI_VACACIONES="/module/vacation";
//		
//	//Service LocationService
//	public final static String URI_LOCATION="/module/location";
//	public final static String TIPO_DOMICILIO_PRINCIPAL="1";
//	
//	//Service file
	public final static String URI_FILE="/module/file";
	public final static int FILE_SIZE_RANDOM=1000000;
	public final static byte FILE_SIZE_RANDOM_CADENA=3;
//	public final static int BODY_SIN_ARCHIVO_BYTES=51;
//	public final static byte IMG_PROFILE_TYPE = 1;
//	
//	
//	//Service LogOut
//	public final static String URI_LOGOUT="/logout";
//	
	//Service  EnterpriseParameters
	public final static String URI_ENTERPRISE="/module/enterpriseParameter";
//	
//	//Service Notify
//	public final static String URI_NOTIFY="/module/notify";
//
//	// Service Applicant
//	public final static String URI_APPLICANT="/module/applicant";
//	public final static String URI_APPLICANT_GET_APPLICANTS="/getApplicants";
//	public final static String URI_APPLICANT_SEARCH_APPLICANTS="/searchApplicants";
//	public final static String URI_APPLICANT_SET_RESUME_PUBLICATION="/setResumePublication";
//	public final static String URI_APPLICANT_READ_VACANCY="/readVacancy";
//	public final static int MULT_MILISEG_A_DIAS =(24 * 60 * 60 * 1000);
//	public final static int MULT_MILISEG_A_SEGUNDOS =(1000);	
//	public final static float MAX_SCORE_SIMILARITY = 0.8f;
//	
//	
//	// Service Social Api
//	public final static String URI_MOD_LINKEDIN="/module/linkedin";
//	// Service (multiple) Report
//	public final static String URI_REPORT="/module/report";
//	//Service's Tracking
	public final static String URI_TRACKING = "/module/tracking";
//	public final static String URI_TRACK_PLANTILLA = "/module/tracktemplate";
//	public final static String MODO_COMPLETO = "1";
//	
	//Service ENCUESTA
	public final static String URI_ENCUESTA = "/module/encuesta";
//	
//	/* ***********************************************************************  */
//
//	// Claves de Politicas
//	public final static String POLITICA_VALOR_EXP_LABORAL_KO="EXP_LABORAL_KO";
//	public final static String POLITICA_VALOR_FORM_ACADEMICA_KO_MIN="FORM_ACADEMICA_KO_MIN";
//	public final static String POLITICA_VALOR_FORM_ACADEMICA_KO_MAX="FORM_ACADEMICA_KO_MAX";
//	public final static String POLITICA_VALOR_RANGO_EDAD_KO="RANGO_EDAD_KO";
//	public final static String POLITICA_VALOR_SEXO_KO="SEXO_KO";
//	public final static String POLITICA_VALOR_EDO_CIVIL_KO="EDO_CIVIL_KO";
//	public final static String POLITICA_VALOR_PER_TRABAJO_KO="PERMISO_TRABAJO_KO";
//	public final static String POLITICA_VALOR_DISP_HORARIO_KO="DISP_HORARIO_KO";
//	public final static String POLITICA_VALOR_CAMBIO_DOM_KO="CAMBIO_DOMICILIO_KO";
//	public final static String POLITICA_VALOR_DISP_VIAJAR_KO="DISP_VIAJAR_KO";
//	public final static short POLITICA_PONDERACION_DEFAULT=50;
//	public final static short POLITICA_PONDERACION_TEXTO_DEFAULT=1;
//	public final static short POLITICA_INDISTINTO = 0;	
//	public final static short POLITICA_INDISTINTO_BOOLEAN = 2;	
//	public final static String CLAVE_POLITICA_HABILIDAD = "HABILIDAD_KO";
//	public final static String CLAVE_POLITICA_IDIOMA = "IDIOMA_KO";
//	public final static String CLAVE_POLITICA_CERTIFICACION = "CERTIFICACIONES";
//	public final static short ID_POLITICA_HABILIDAD = 31;	//TODO REmover
//	public final static short ID_POLITICA_IDIOMA = 32; //TODO remover
//	public final static short ID_POLITICA_CERTIFICACION = 34; //TODO remover
//
//	// Secciones de politicas
//	public final static byte SECCION_DEMOGRAFICO = 5;
//	
//	// Conceptos de politicas (TODO remover por redundancia de tablas)
//	public final static byte CONCEPTO_ESCOLARIDAD=10;
//	public final static byte CONCEPTO_ESTADO_CIVIL=11;
//	public final static byte CONCEPTO_TIPO_GENERO=13;
//	public final static byte CONCEPTO_PERMISO_TRABAJO=14;
//	public final static byte CONCEPTO_DISPONIBILDAD_HORARIO=15;
//	public final static byte CONCEPTO_EDAD=16;
//	public final static byte CONCEPTO_CAMBIO_DOMICILIO=17;
//	public final static byte CONCEPTO_EXPERIENCIA_LABORAL=18;
//	public final static byte CONCEPTO_DISP_VIAJAR=12;
//	
//	public final static byte ID_CONCEPTO_IDIOMA=20;
//	public final static byte ID_CONCEPTO_CERTIFICACION=21;
//	public final static byte ID_CONCEPTO_HABILIDAD=22;
//
//	//Service VacancyService set
//	public final static String URI_VACANCY="/module/vacancy";
//	public final static String URI_VACANCYACADEMICWEIGHING="/module/vacancyAcademicWeighing";
//	public final static String URI_VACANCYPUBLICATION="/setVacancyPublication";
//	
//	public final static String URI_POSICION_HABILIDAD = "/module/positionSkill";
//	public final static String URI_POSICION_CERTIFICACION = "/module/positionCert";
//	public final static String URI_POSICION_COMPETENCIA = "/module/positionCompet";
//	
//
//	//public final static String URI_VACANCYTEXT="/module/vacancyText";	//TODO borrar y generar metodos textCreate, textUpdate en Vacancy/
//	
//	//Service Profile set
//	public final static String URI_PROFILE="/module/profile";
//	public final static String URI_CREATE_PROFPUBLIC="/createpublic";
//	public final static String URI_CREATE_PROFPRIVATE="/createprivate";
//	
//	public final static String URI_PROFILENGRAM="/module/perfilNgram";
//	
//	
//	//Service VacancyService set
//	public final static String URI_AREATEXT="/module/areaText";
//	public final static String URI_CREATEPRECLASSDOCS="/createPreclassifiedDocs";
//
//	// Service Task (scheduler) 
//	public final static String URI_TASK="/module/task";
//    public final static String URI_TASK_SYNC_CLASS_DOCS="/syncClassDocs";   
//	public final static String URI_TASK_RECLASSIFICATION="/reClassification";
//	public final static String URI_TASK_SEARCHCANDIDATES="/searchCandidates";
//	public final static String URI_TASK_RELOAD_CORE_SOLR="/reloadCoreSolr";
//
//	public final static String URI_TASK_REMODEL="/reModel";
//
//	
//	public final static String URI_DOCS_CLASS="/module/classify";
//	public final static String URI_DOCS_LOADTOKENS="/loadTokens";
//	public final static String URI_DOCS_CLASSIFYBYLOT="/classifyByLot";
//	
//	//Service CheckAndRouteDocumentsTask
//	public final static Short RECLASSIFICATION_AUTO=1;
//	public final static int DEFAULT_NUM_DOCS_MODEL=0;
//
//
//	public final static Byte TIPO_PROCESO_REMODEL_CLASS=1;
//	public final static Byte TIPO_PROCESO_SYNC_DOCS=2;
//	public final static Byte TIPO_PROCESO_RECLASS_DOCS=3;
//
//	//Service Notification set
//	public final static String URI_NOTIFICATION="/module/notification";
//	
//	//Service HandShake set
//	public final static String URI_HANDSHAKE ="/module/handshake";
//	
//	//IAP
//	public final static float PONDERACION_DEMOGRAFICOS=(float) 0.50;
//	public final static float PONDERACION_EXPERIENCIA_LABORAL=(float) 0.50;
//	public final static float PONDERACION_ESCOLARIDAD=(float) 0.50;
//	//public final static float PONDERACION_HABILIDADES=(float) 0.33;
//	public final static String PONDERACION_DEMOGRAFICOS_KEY="DEMOGRAFICO_PONDERA_IAP";
//	public final static String PONDERACION_EXPERIENCIA_LABORAL_KEY="EXPERIENCIA_LAB_PONDERA_IAP";
//	public final static String PONDERACION_ESCOLARIDAD_KEY="ESCOLARIDAD_PONDERA_IAP";
//	public final static String PONDERACION_HABILIDADES_KEY="HABILIDAD_PONDERA_IAP";
//	
//	//Experiencia Laboral
//	public final static short CADUCIDAD=1820;
//	public final static short DURACION_VALIDA=180;
//	public final static float CASTIGO=(float) 0.33;
//	public final static String FORMULA="(1)*A+(0.5)*B+(0.8)*C+(0.4)*D+(0.3)E+(0.1)F";
//	public final static byte TIPO_JORNADA=1;
//	public final static String NM_DURACION_VALIDA="DURACION_VALIDA";
//	public final static String NM_CADUCIDAD="CADUCIDAD";
//	public final static String NM_CASTIGO="CASTIGO";
//	public final static String TRABAJO_ACTUAL="1";
//	
//	//Escolaridad
//	public final static String GRADO_ACADEMICO_SEC_PREP="1";
//	public final static String GRADO_ACADEMICO_TEC_COM="2";
//	public final static String GRADO_ACADEMICO_DIP_CUR="3";
//	public final static String GRADO_ACADEMICO_LIC_ING="4";
//	public final static String GRADO_ACADEMICO_CERT="5";
//	public final static String GRADO_ACADEMICO_MAEST="6";
//	public final static String GA_NIVEL="NIVEL";
//	public final static String GA_DOMINIO="DOMINIO";
//	public final static String GA_DEGRADACION="DEGRADACION";
//	public final static String GA_DURACION="DURACION";
//	public final static String EE_NIVEL="NIVEL";
//	public final static String EE_PREPONDERANCIA="PREPONDERANCIA";
//	public final static String FORM_ACADEMICA_KO_GA="FORM_ACADEMICA_KO_GA";
//	public final static String FORM_ACADEMICA_KO_EE="FORM_ACADEMICA_KO_EE";
//	public final static float CASTIGO_X_CADUCIDAD=(float) 0.15;
//	public final static float ESCOLARIDAD_SUPERIOR=(float) 0.25;
//	public final static float ESTATUS_INCOMPLETOS=(float) 0.25;
//	public final static String NM_CASTIGO_X_CADUCIDAD="CASTIGO_X_CADUCIDAD";
//	public final static String NM_ESCOLARIDAD_SUPERIOR="ESCOLARIDAD_SUPERIOR";
//	public final static String NM_ESTATUS_INCOMPLETOS="ESTATUS_INCOMPLETOS";
//	public final static String ESTATUS_ESCOLAR_ESTUDIANTE="3";
//	public final static String NAME_CAT_GRADOACAD = "GRADO_ACADEMICO";
//
//	//Domicilios
//	public final static String TIPO_DOMICILIO_PRIMARIO="1";
//	
	//Service Indices
//	public final static String EXP_REG_A="[àáâãäå]";
//	public final static String EXP_REG_E="[èéêë]";
//	public final static String EXP_REG_I="[ìíîï]";
//	public final static String EXP_REG_O="[òóôõö]";
//	public final static String EXP_REG_U="[ùúûü]";	
//	public final static String EXP_REG_CARACTERES="[^0-9a-zA-Z ;,ñ]";
//	public final static String EXP_REG_ESPACIO="\\s+";
//	public final static String EXP_REG_ARTICULOS=" el | la | lo | las | los | un | una | unos | unas | al | del ";
//	public final static String EXP_REG_CONJUNCION=" ni | bien | pero | sino | aunque |".
//					concat(" que | ya que | puesto que | pues | porque | como ya |").
//					concat(" si | siempre que | aunque | como | para | para que |").
//					concat(" asi que | de modo que | de manera que | luego | por tanto |").
//					concat(" cuando | mientras | tan pronto como | despues de que | hasta que ");
//	// | y | e | o | u | no se quitan
//	
//	public final static String EXP_REG_ADVERBIO=" aqui | ahi | alli | alla | aca | arriba | abajo | cerca | lejos |".
//					concat(" delante | encima | debajo | enfrente | atras | alrededor |").
//					concat(" pronto | tarde | temprano | todavia | aun | ya | ayer | hoy |").
//					concat(" mañana | siempre | nunca | jamas |").
//					concat(" anoche | enseguida | ahora | mientras | anteriormente |").
//					concat(" bien | mal | regular | despacio | deprisa | asi | aprisa |").
//					concat(" adrede | peor | mejor | bastante | muy | mas | menos | algo |").
//					concat(" demasiado | casi | solo | nada | tambien | cierto | claro |").
//					concat(" exacto | obvio | no | tampoco | quiza | quizas | acaso | tal vez |").
//					concat(" puede | puede ser | a lo mejor | solo | aun | inclusive | ademas |").
//					concat(" incluso | viceversa | siquiera ");
//	
//	public final static String EXP_REG_PREPOSICIONES=" a | ante | bajo | cabes | con | contra |".
//					 concat(" de | desde | durante | en | entre |").
//					 concat(" excepto | hacia | hasta | mediante |").
//					 concat(" pro | salvo | segun | sin |").
//					 concat(" so | sobre | tras | via ");
//	
//	public final static String EXP_REG_PRONOMBRES=" yo | mi | me | conmigo | tu | ti | te | contigo | usted |".
//					concat(" el | ella | ello | le | se | consigo | si |").
//					concat(" nosotros | nosotras | nos | vosotros | vosotras | os |").
//					concat(" ellos | ellas | les | se |").
//					concat(" este | esta | esto | estos | estas |").
//					concat(" ese | esa | eso | esos | esas |").
//					concat(" aquel | aquella | aquello | aquellos | aquellas |").
//					concat(" mio | tuyo | suyo | nuestro | vuestro |").
//					concat(" mios | tuyos | suyos | nuestros | vuestros |").
//					concat(" mia | tuya | suya | nuestra | vuestra |").
//					concat(" mias | tuyas | suyas | nuestras | vuestras |").
//					concat(" aquel | aquella | aquello | aquellos | aquellas |").
//					concat(" ningun | ninguno | ninguna | nada | ningunos | ningunas |").
//					concat(" uno | una | algun | alguno | alguna | alguna | algo | algunos | algunas |").
//					concat(" ningun | ninguno | ninguna | nada | ningunos | ningunas |").
//					concat(" poco | poca | pocos | pocas |").
//					concat(" escaso | escasa | escasos | escasas |").
//					concat(" mucho | mucha | muchos | muchas |").
//					concat(" demasiado | demasiada | demasiados | demasiadas |").
//					concat(" todo | toda | todos | todas |").
//					concat(" mismo | misma | mismos | mismas |").
//					concat(" tan | tanto | tanta | tantos | tantas |").
//					concat(" varios | varias | alguien | nadie | cualquier |  cualquiera | cualesquiera |").
//					concat(" quienquiera | quienesquiera | tal | tales | demás | bastante | bastantes |").
//					concat(" que | cual | cuales | quien | quienes | cuyo | cuya | cuyos |  cuyas | donde |").
//					concat(" cuanto | cuanta ");
	
//	/**
//	 * Catalogos
//	 */
//	public final static String CAT_PATH="net.tce.model.";
//	public final static String CAT_PAIS="Pais";
//	public final static String CAT_ESTADO="Estado";
//	public final static String CAT_MUNICIPIO="Municipio";
//	public final static String CAT_ASENTAMIENTO="Asentamiento";
//	public final static String CAT_CIUDAD="Ciudad";
//	public final static String CAT_EDO_CIVIL="EstadoCivil";
////	public final static String CAT_TIPO_TELEFONO="TipoTelefono";
//	public final static String CAT_TIPO_ESTATUS_PADRES="TipoEstatusPadres";
//	public final static String CAT_TIPO_CONVIVENCIA="TipoConvivencia";
//	public final static String CAT_TIPO_VIVIENDA="TipoVivienda";
//	public final static String CAT_AREA="Area";
//	
//	
//	//Contiene la ubicacion de los pojos relacionados a catalogos
//	@SuppressWarnings("serial")
//	public final static	HashMap<String, String> hmCatalogos= new HashMap<String, String>(){
//    	{
//    		put(CAT_PAIS.toUpperCase(), CAT_PATH.concat(CAT_PAIS));
//    		put(CAT_ESTADO.toUpperCase(), CAT_PATH.concat(CAT_ESTADO));
//    		put(CAT_MUNICIPIO.toUpperCase(), CAT_PATH.concat(CAT_MUNICIPIO));
//    		put(CAT_ASENTAMIENTO.toUpperCase(), CAT_PATH.concat(CAT_ASENTAMIENTO));
//    		put(CAT_CIUDAD.toUpperCase(), CAT_PATH.concat(CAT_CIUDAD));
//    		put(CAT_EDO_CIVIL.toUpperCase(), CAT_PATH.concat(CAT_EDO_CIVIL));
////    		put(CAT_TIPO_TELEFONO.toUpperCase(), CAT_PATH.concat(CAT_TIPO_TELEFONO));
//    		put(CAT_TIPO_ESTATUS_PADRES.toUpperCase(), CAT_PATH.concat(CAT_TIPO_ESTATUS_PADRES));
//    		put(CAT_TIPO_CONVIVENCIA.toUpperCase(), CAT_PATH.concat(CAT_TIPO_CONVIVENCIA));
//    		put(CAT_TIPO_VIVIENDA.toUpperCase(), CAT_PATH.concat(CAT_TIPO_VIVIENDA));
//    		put(CAT_AREA.toUpperCase(), CAT_PATH.concat(CAT_AREA)); 		
//    	}
//	};
//	
//	//Statistic methods
//	public final static byte STAT_SD_METHOD = 1;
//	public final static byte STAT_QUARTILE_METHOD = 2;
//	public final static byte STAT_AUTO_METHOD = 3;
//	
	//Manejo de fechas
	public final static String DATE_FORMAT = "yyyy-MM-dd";	
	public final static String DATE_FORMAT_JAVA = DATE_FORMAT+" HH:mm:ss";	
//	
//	// Servicios operativos
//	public final static Byte PROCESS_STATUS_IN_PROGRESS = 1;	
//	public final static Byte PROCESS_STATUS_CLOSED = 2;	
//	public final static Byte PROCESS_STATUS_ERROR = 4;	
//	public final static int ESTATUS_CLAS_VERIFICADO_MODELO = 3;
//	public final static int ESTATUS_CLAS_VERIFICADO_NOMODELO = 5;
//	public final static String CLASSIFICATION_STATUS_IN_CLAUSE = "3,5";//Se emplea: where estatusClasificacion in (?) 
//	
//	// Servicios REST
	public final static String ERROR_CODE_SYSTEM = "000";//Existi\u00F3 un error en la aplicaci\u00F3n (props)
	public final static String CONTENT_TYPE=";charset=UTF-8";
	public final static String ERROR_LABEL_SERVICE = "Error en el servicio REST";
//
//	//Tipo relacion persona
//	//public final static int EMPRESA_TIPO_RELACION_EMPLEADO = 1;
//	//public final static int EMPRESA_TIPO_RELACION_SOLICITANTE = 8;
////	public @Value("${COMPANY_ADMIN_REL}")	String COMPANY_ADMIN_REL;
//	//public final static int EMPRESA_TIPO_RELACION_ADMINISTRADOR = 9;
//	
//	//Relacion persona_empresa
//	public final static Boolean RELACION_EMPRESA_PERSONA_REPRESENTANTE=true;
//
//	
//	/* ARGUMENTOS PARA FUNCIONALIDAD EN PostgreSQL */
//	
//	public final static String PSG_TOCHAR_BIGINT = "'FM9999999999999999999'";
//	//public final static String PSG_TOCHAR_DATE = "'YYYY-MM-DD HH:MI:SS'";
//	public final static String TOCHAR_DATE = "'"+DATE_FORMAT+" HH24:MI:SS'";
//	public final static String PSG_TOCHAR_COUNT = "'FM9999'";	
//	public final static String DB_MANAGER_ORACLE = "ORA";
//	public final static String DB_MANAGER_PSG = "PSG";	
//	
//	/* ELEMENTOS PARA ARCHIVO  */
//	public final static Byte PDF_CVP_TYPE = 1;
//	public final static String PDFCV_NAME = "CV-E<idEmpConf>P<idPersona>.pdf";
//	public final static Byte DOC_FCOMPENSA_TYPE = 2;
//	public final static String DOC_FCOMPENSA_NAME = "FC-<idEmpConf>P<idPersona>.docx";
	
}
