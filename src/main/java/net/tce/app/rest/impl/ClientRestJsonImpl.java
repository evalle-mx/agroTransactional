package net.tce.app.rest.impl;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import net.tce.app.exception.SystemTCEException;
import net.tce.app.rest.ClientRestJson;
import net.tce.util.Constante;


@Service("clientRestService")
public class ClientRestJsonImpl implements ClientRestJson{
	Logger log4j = Logger.getLogger( this.getClass());
	
	@Inject
	Gson gson;

	private WebTarget target;
	private Response response;
	private  @Value("${uri_operational_rest}")String uri_operational_rest;
	private StringBuffer sbError;
	
	/**
	 * Constructor que inicia cliente y recurso Web en base a la URL en constantes
	 */
	@PostConstruct
	public void initIt()  {
		 log4j.debug("<INITiT.ClientRestJsonImpl> Iniciando Cliente_REST.Operational :" + uri_operational_rest);
		 target =ClientBuilder.newClient().target(uri_operational_rest);		
		 log4j.debug("<INITiT.ClientRestJsonImpl> Conectado a [target.getUri().getHost()]: " + target.getUri().getHost());
	 }
	

	/**
	 * Metodo comun a todos los Clientes Rest para consumir recursos del servicio Operational (OperationalTCE)
	 * supone que los parametros json y uri son validos y no nulos
	 * @param outJson
	 * @param uriService
	 * @return
	 * @throws SystemTCEException
	 */
	public String getObjectFromService(String outJson, String uriService) throws SystemTCEException {
		log4j.debug("<getObjectFromService> "
				.concat("\n==> Json: ").concat(outJson)
				.concat("\n==> URI: ").concat(uriService));
		response = target.path(uriService).
	        		request(MediaType.APPLICATION_JSON+Constante.CONTENT_TYPE).
	        		post(Entity.entity(outJson, 
	        		MediaType.APPLICATION_JSON+Constante.CONTENT_TYPE),
	        		Response.class);
		/* Llamada Exitosa (Comunicación correcta) */
		if (response.getStatusInfo().getFamily() == Family.SUCCESSFUL) {
			return  response.readEntity(String.class).toString();
		}
		/* Error */
		else {
			sbError= new StringBuffer(String.valueOf(response.getStatus())).
					append(Constante.DIVISOR_HTTP).append(Constante.ERROR_LABEL_SERVICE)
					.append(uriService);
			 log4j.error("<getJsonFromService.Rest.ERROR> Respuesta= "+response.readEntity(String.class) + "\n" 
					+ "sbError= "+sbError.toString());
			 
			 throw new SystemTCEException(sbError.toString());
		}
	}

}
