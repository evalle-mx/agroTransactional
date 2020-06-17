package net.tce.admin.adapter.jaxws;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import  net.tce.admin.adapter.jaxws.MensajeDto;
import  net.tce.admin.adapter.jaxws.StoreDocumentRequest;
import  net.tce.admin.adapter.jaxws.StoreDocumentResponse;
import  net.tce.admin.adapter.jaxws.MensajeDtoArray;
import net.tce.admin.service.FileServer;
import net.tce.assembler.FileAssembler;
import net.tce.util.Mensaje;


@Endpoint
public class FileAdapterJAXWSImpl {
	Logger log4j = Logger.getLogger(this.getClass());

	@Autowired
	private FileServer fileService;
	
	@Autowired
	private FileAssembler fileAssembler;
	
	@Inject
	private ConversionService converter;
	
	private static final String NAMESPACE_URI = "http://jaxws.adapter.admin.tce.net/";

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "storeDocumentRequest")
	@ResponsePayload
	public StoreDocumentResponse storeDocument(@RequestPayload StoreDocumentRequest request) {
		log4j.debug("%%%% storeDocument() -> getTipoContenido="+request.getFileDto().getIdTipoContenido()+
				" TipoArchivo="+request.getFileDto().getTipoArchivo()+
				" "+request.getFileDto().getDhContenido());
		StoreDocumentResponse response = new StoreDocumentResponse();
		MensajeDtoArray mensajeDtoArray=new MensajeDtoArray();
		try {
			//Objeto dto_ws a objeto dto
			List<net.tce.dto.MensajeDto>  lsMensajeDto = fileService.fileUpload(fileAssembler.setfileDto(
														 request.getFileDto(), new net.tce.dto.FileDto()));
			log4j.debug("%%%% storeDocument() -> resp lsMensajeDto="+lsMensajeDto.size());
			Iterator<net.tce.dto.MensajeDto> itMensajeDto=lsMensajeDto.listIterator();
			while(itMensajeDto.hasNext()){
				//Objeto dto a objeto dto_ws
				mensajeDtoArray.getItem().add( converter.convert(itMensajeDto.next(),MensajeDto.class));				
			}			
		} catch (Exception e) {
			MensajeDto mensajeDto=new MensajeDto();
			mensajeDto.setMessage(Mensaje.SERVICE_MSG_ERROR_UPLOAD);
			mensajeDto.setCode(Mensaje.SERVICE_CODE_012);
			mensajeDto.setType(Mensaje.SERVICE_TYPE_FATAL);
			mensajeDtoArray.getItem().add(mensajeDto);
			e.printStackTrace();
		}
		log4j.debug("%%%% storeDocument() -> resp mensajeDtoArray.getItem()="+mensajeDtoArray.getItem().size());
		response.setMensajeDtoArray(mensajeDtoArray);
		return response;
	}
	

	
	
}
