package net.tce.util;


public abstract class UtilsNotification {
	
	//Generales
	public final static String CLAVE_DIVISOR=":";
	//public final static String URI_FILTRO_SIGNUP="signup.do";

	//Categoria evento
	public final static Byte CATEGORIA_EVENTO_ADMINISTRACION=5;
	
	//Tipo Evento(clave)
	//Sistema
	public final static String CLAVE_EVENTO_ERROR_FATAL="ERROR_FATAL";
	public final static String CLAVE_EVENTO_CONF_INSC="CONFIRMA_INSCRIPCION";
	public final static String CLAVE_EVENTO_PUBLICA_CV="PUBLICA_CV";
	public final static String CLAVE_EVENTO_RECORDAR_PUBLICACION="RECORDAR_PUBLICACION";
	
	
	public final static String CLAVE_EVENTO_HK_INVITACION="HANDCHECK_INVITACION";
	public final static String CLAVE_EVENTO_HK_RECHAZO="HANDCHECK_RECHAZO";
	public final static String CLAVE_EVENTO_HK_INVITACION_OK="HANDCHECK_ACEPTAR_INVITACION";
	public final static String CLAVE_EVENTO_HK_INVITACION_NOT_OK="HANDCHECK_DENEGAR_INVITACION";
	public final static String CLAVE_EVENTO_HK_REINVITACION="HANDCHECK_REINVITACION";
	public final static String CLAVE_EVENTO_MODIFICA_CV_EMPRESA="MODIFICA_CV_EMPRESA";
	public final static String CLAVE_EVENTO_MODIFICA_CV_PERSONA="MODIFICA_CV_PERSONA";
	public final static String CLAVE_EVENTO_CV_PERSONA_INACTIVA="CV_PERSONA_INACTIVA";
	public final static String CLAVE_EVENTO_CV_PERSONA_INACTIVA_POSICION="CV_PERSONA_INACTIVA_POSICION";
	public final static String CLAVE_EVENTO_REINICIO_PWD="REINICIAR_PASSWORD";
	
	
	//Mecanismos
	public final static Byte MECANISMO_CORREO=5;
	
	//Tipo Emisor
	public final static Byte TIPO_EMISOR_PERSONA=1;
	public final static Byte TIPO_EMISOR_EMPRESA=2;
	public final static Byte TIPO_EMISOR_POSICION=3;
	public final static Byte TIPO_EMISOR_SISTEMA=4;
	
	//Receptores
	public final static Byte RECEPTOR_RESPONSABLES_POSICION = 1;
	public final static Byte RECEPTOR_CANDIDATOS_POSICION = 2;
	public final static Byte RECEPTOR_ADMINISTRADORES_EMPRESA = 3;
	public final static Byte RECEPTOR_ENVIADO_CREACION = 4;
	public final static Byte RECEPTOR_RESPONSABLES_POSICIONES_POR_EMPRESA = 5;
	public final static Byte RECEPTOR_RESPONSABLES_POSICIONES_POR_PERSONA = 6;
	public final static Byte RECEPTOR_CANDIDATOS_POR_POSICION_PERSONA = 7;
	
	//Emisor - Identificador específico
	public final static Byte EMISOR_SISTEMA=0;
	
	
	/*
	 * Etiquetas
	 */
	public final static String TAG_1="<nombreReceptor>";
	public final static String TAG_2="<ligaCorreo>";
	public final static String TAG_3="<ligaDothr>";
	public final static String TAG_4="<ligaImgDothr>";
	public final static String TAG_5="<nombreEmisor>";
	public final static String TAG_6="<nombreEmpresaEmisor>";
	public final static String TAG_7="<nombrePosicion>";
	public final static String TAG_8="<idCandidato>";
	public final static String TAG_9="<comentario>";
	public final static String TAG_10="<contentido>";
	public final static String TAG_11="<hostName>";
	public final static String TAG_12="<hostAddress>";
	
	/*
	 * Correo
	 */
	//public final static String MAIL_REMITENTE="tceMailing@dothr.net";
	//public final static String MAIL_ASUNTO="Bienvenido a TCE";
	public final static String MAIL_MESSTEXT_CHARSET = "UTF-8";
	public final static String MAIL_MESSTEXT_SUBTYPE = "html";
	public final static String MAIL_HTML_HEAD =new StringBuilder("<head><img src=\"").append(TAG_4).
											   append("\" alt=\"dothr\"><a href=\"").append(TAG_3).
											   append("\">DOTHR</a></head>").toString();
	public final static String MAIL_HTML_FOOTER = new StringBuilder("<footer>").
												  append("<p>Publicado por: DOTHR</p>").
												//  append("<p>Contacto: <a href=\"mailto:someone@dothr.com\">").
												 // append("someone@dothr.com</a>.</p>").
												  append("</footer> ").toString();
	
	
	
}
