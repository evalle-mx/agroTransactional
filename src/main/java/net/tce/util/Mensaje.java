package net.tce.util;

public abstract class Mensaje {

	/**
	 * General
	 */
	public final static String MSG_OK_DELETE="Fue borrado satisfactoriamente ";
	public final static String MSG_OK_CREATE_PERSONA="Fue creado satisfactoriamente. Por favor verificar su correo para concluir la inscripcion ";
	public final static String MSG_SUCCESS_EMAIL_SEND = "Se ha enviado un correo electrónico, verifique su bandeja de entrada";
	public final static String MSG_OK_CUOTA="Cumple la cuota";
	public final static String MSG_ERROR="La construcción del mensaje no es correcta, favor de verificar la especificación correspondiente";
	public final static String MSG_WARNING="Notificación del sistema";
	public final static String MSG_ERROR_SISTEMA="Error de sistema, conectarse mas tarde";
	public final static String MSG_ERROR_SUPERA_MAX="Superó el máximo de archivos permitidos";
	public final static String MSG_ERROR_EMAILING="No se pudo enviar el correo de confirmación ";
	public final static String MSG_ERROR_EMPTY = "No existen datos registrados, verifique la información proporcionada";
	public final static String MSG_ERROR_ENCRIPT = "Error al encriptar el dato: ";
	public final static String MSG_ERROR_DATACONF = "No hay informacion en la tabla de EMPRESA_PARAMETRO relacionada con el Dataconf de:";
	public final static String MSG_ERROR_RELATION="Relación de empresa persona ";
	public final static String MSG_ERROR_DUPLICATE = "Ya existen los datos registrados";
	public final static String MSG_FATAL_DATACONF = "Excepcion en filtros DataConf";
	public final static String MSG_SOLR_SYSTEM_ERROR = "Error de comunicación/interacción con el servidor Solr";
	public final static String MSG_PUBLICATION_FAIL = "No se cumplieron algunos criterios en la publicación de los datos";
	public final static String MSG_PUBLICATION_MODIFICATION = "Se cambió una propiedad en un contexto de Publicación";
	public final static String MSG_UNAUTHORIZED_LINKEDIN = "No autorizado para realizar la operación (Verificar Key's)";	
	public final static String MSG_MASIVE_DUPLICATEEMAIL = "El correo electronico ya esta registrado para esta empresa";
	public final static String MSG_ASENTAMIENTO_REQUERIDO = "No se pudo obtener Asentamiento requerido";
	public final static String MSG_ERROR_PWD_INVALID = "La contraseña ingresada no es correcta";
	public final static String MSG_ERROR_PWD_DIFERENT = "Las contraseñas nuevas no coinciden";
	public final static String MSG_ERROR_PWD_DUPLICATE = "La contraseña nueva no puede ser igual a la actual";
	public final static String MSG_SUCCESS_PWD_UPDATE = "La contraseña fue modificada correctamente";
	public final static String MSG_FATAL_OPERATIONAL_STRUCTURED = "Error de comunicación con OPERACIONAL_STRUCTURED, conectarse mas tarde";

	//Criterios de filtros dataconf
	public final static String FILTER_DATACONF_RESTRICTION_ALFA="El tipo de dato debe contener solo letras ";
	public final static String FILTER_DATACONF_RESTRICTION_NUM="El tipo de dato debe ser numerico positivo";
	public final static String FILTER_DATACONF_RESTRICTION_NUM_PARC=" debe ser numerico positivo";
	public final static String FILTER_DATACONF_RESTRICTION_NUM_NEG="El tipo de dato debe ser numerico";
	public final static String FILTER_DATACONF_RESTRICTION_ALFANUM="El tipo de dato debe contener solo letras o numeros";
	public final static String FILTER_DATACONF_PRECISIONSCALE="La precision del numero de ser: (";
	public final static String FILTER_DATACONF_RANGE_MIN="La longitud del dato debe ser mayor o igual a ";
	public final static String FILTER_DATACONF_RANGE_MAX="La longitud del dato debe ser menor o igual a ";
	public final static String FILTER_DATACONF_VALUESET="El dato solo puede tener uno de los siguientes valores: ";
	public final static String FILTER_DATACONF_PATTERN_PWD="El formato del password es incorrecto";
	public final static String FILTER_DATACONF_PATTERN_EMAIL="El formato del correo es incorrecto";
	public final static String FILTER_DATACONF_PATTERN_RFC="El formato del RFC es incorrecto";
	public final static String FILTER_DATACONF_DATEVALIDATION="La fecha no es correcta";
	public final static String FILTER_DATACONF_DATAVALIDATION_COMPARE_F_INI="La fecha inicial no puede ser mayor a la fecha final";
	public final static String FILTER_DATACONF_DATAVALIDATION_COMPARE_F_FIN="La fecha final no puede ser menor a la fecha inicial";
	public final static String FILTER_DATACONF_DATEGREATERTHANTODAY="La fecha no puede ser menor o igual a la fecha actual";
	public final static String FILTER_DATACONF_VALUELESS_SALARIO="El salario mínimo no puede ser mayor al salario máximo";
	public final static String FILTER_DATACONF_VALUES_MAYOR_EDAD="La edad mínima no puede ser mayor a la edad máxima";
	public final static String FILTER_DATACONF_VALUES_ESTATUS_ACADEMICO_MAX="Se requiere del grado academico máximo, para crear la política";
	public final static String FILTER_DATACONF_VALUES_ESTATUS_ACADEMICO_MIN="Se requiere del grado academico mínimo, para crear la política";
	public final static String FILTER_DATACONF_VALUES_GRADO_ACADEMICO="El nivel del grado academico mínimo no puede ser mayor al nivel del grado academico máximo";
	public final static String FILTER_DATACONF_VALUES_ESTATUS_ACADEMICO="El nivel del estatus academico mínimo no puede ser mayor al nivel del estatus academico máximo";
	public final static String FILTER_EMAIL_EXISTE="El correo existe";
		
	//Criterios de filtros 
	public final static String FILTER_MAX_MIN_VALUE="email";
	
	/**
	 * Mensajes de los servicios
	 */
	
	//Tipos de mensaje
	public final static String SERVICE_TYPE_FATAL="F";
	public final static String SERVICE_TYPE_ERROR="E";
	public final static String SERVICE_TYPE_WARNING="W";
	public final static String SERVICE_TYPE_INFORMATION="I";
	
	
	//Codigos de Mensajes
	//Error de sistema
	public final static String SERVICE_CODE_000="000";
	//Se requiere el objeto
	public final static String SERVICE_CODE_001="001";
	// El objeto no existe
	public final static String SERVICE_CODE_002="002";
	// El objeto no tiene informacion
	public final static String SERVICE_CODE_003="003";
	// El objeto fue creado
	public final static String SERVICE_CODE_004="004";
	// El objeto fue actualizado
	public final static String SERVICE_CODE_005="005";
	// El objeto no es correcto
	public final static String SERVICE_CODE_006="006";
	// El objeto no cumple el criterio de Restriction
	public final static String SERVICE_CODE_006_1="006.1";
	// El objeto no cumple el criterio de PrecisionScale
	public final static String SERVICE_CODE_006_2="006.2";
	// El objeto no cumple el criterio de Range
	public final static String SERVICE_CODE_006_3="006.3";
	// El objeto no cumple el criterio de ValueSet
	public final static String SERVICE_CODE_006_4="006.4";
	// El objeto no cumple el criterio de Pattern
	public final static String SERVICE_CODE_006_5="006.5";
	// El objeto no cumple el criterio de Date Validation
	public final static String SERVICE_CODE_006_6="006.6";
	// El valor máximo es menor al valor mínimo
	public final static String SERVICE_CODE_006_7="006.7";
	// El objeto fue borrado
	public final static String SERVICE_CODE_007="007";
	// No se cumple el mínimo 
	public final static String SERVICE_CODE_008="008";
	// Supera el máximo permitido
	public final static String SERVICE_CODE_009="009";
	// Se cumple con la cuota
	public final static String SERVICE_CODE_010="010";
	// El objeto no fue creado
	public final static String SERVICE_CODE_011="011";
	// El objeto no fue persistido en la base de datos
	public final static String SERVICE_CODE_012="012";	
	//No Autorizado para acceso/proceso solicitado
	public final static String SERVICE_CODE_013="013";
	// Se cambió una propiedad en un CV, Empresa o Posicion publicada
	public final static String SERVICE_CODE_014="014";
	
	/* ** Los siguientes mensajes parten de 100 para referirse a empresa */
	// No se tiene acceso a los datos solicitados
	public final static String SERVICE_CODE_100="100";
	// Verifica baja de empresa
	public final static String SERVICE_CODE_101="101";
	// Verifica delegar administración de empresa
	public final static String SERVICE_CODE_102="102";
	// La persona es un unico administrador
	public final static String SERVICE_CODE_103="103";
	// La persona es almenos un representante de una empresa
	public final static String SERVICE_CODE_104="104";
	// La persona almenos tiene una posición asignada de la empresa que pertenece
	public final static String SERVICE_CODE_105="105";
	// Existen datos (relaciones) entre empresa-usuario
	public final static String SERVICE_CODE_110="110";
	
	// Sobre la conectividad con otros APP
	// Comunicación con Operational
	// HTTP 404 
	public final static String SERVICE_CODE_200="200";
	
	
	////**  Servicios  **////
	
	//Comun
	public final static String SERVICE_MSG_001="La llave primaria no debe ser nula o igual a cero";
	public final static String SERVICE_MSG_002="El registro fue persistido";
	public final static String SERVICE_MSG_OK_JSON="[]";
	public final static String SERVICE_MSG_ERROR_DATACONF="No se pudo aplicar el IdEmpresaConf";

	//Service Catalogue
	public final static String SERVICE_MSG_CATALOGUE_001="El nombre del Catálogo es requerido.";
	public final static String SERVICE_MSG_CATALOGUE_002="No existe el Catálogo: ";
	public final static String SERVICE_MSG_CATALOGUE_003="El registro fue creado, en el Catálogo: ";
	public final static String SERVICE_MSG_CATALOGUE_004="El registro fue actualizado, en el Catálogo: ";
	
	//Service Persona (Curriculum)
	//public final static String SERVICE_CV_ERROR_EMPTY = "No existen datos registrados, verifique la información proporcionada";
	public final static String SERVICE_DATA_EXISTS = "Existen datos registrados, verifique la información proporcionada";
	
	//Service Empresa
	public final static String SERVICE_MSG_EMPRESA_PARAMETROS_001 = "Faltan datos de entrada";
	
	//Service File
	public final static String SERVICE_MSG_FILE_001="No se pudo efecturar la operación ya que la carpeta fisica no existe y por ende el archivo ";
	public final static String SERVICE_MSG_ERROR_UPLOAD="Error al subir archivo";
	//Dejar asi la ñ(Ã±), sino marca un error al mandar el mensaje directo(response)
	public final static String SERVICE_MSG_ERROR_UPLOAD_SIZE_MAX="El tamaño del archivo es mayor al permitido";
	public final static String SERVICE_MSG_ERROR_UPLOAD_SIZE_MIN="El tamaño del archivo es menor al permitido";
	public final static String SERVICE_MSG_ERROR_UPLOAD_TYPE="El tipo de archivo no es el correcto";
	public final static String SERVICE_MSG_ERROR_UPLOAD_ERROR="La construcción del mensaje no es correcta,  favor de verificar la especificación correspondiente";
	public final static String SERVICE_MSG_ERROR_DATA_PERMISSION="No tiene acceso a los datos";
	public final static String SERVICE_MSG_ERROR_AWS="Existió un error al procesar con AWS";
	
	//Service Cvs masivos
	public final static String SERVICE_MSG_ERROR_SIMILARITY="No se encontro su similar correspondiente ";

	
	
}
