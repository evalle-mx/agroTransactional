package net.tce.assembler;

import net.tce.dto.FileDto;

public class FileAssembler {

	/**
	 * 
	 * @param fileDtoWS
	 * @param FileDto
	 */
	public FileDto setfileDto(net.tce.admin.adapter.jaxws.FileDto fileDtoWS, FileDto fileDto){
		if(fileDtoWS.getIdEmpresaConf() != null){
			fileDto.setIdEmpresaConf(fileDtoWS.getIdEmpresaConf());
		}	
		if(fileDtoWS.getDescripcion() != null){
			fileDto.setFileDescripcion(fileDtoWS.getDescripcion());
		}
		if(fileDtoWS.getDhContenido() != null){
			fileDto.setDhContenido(fileDtoWS.getDhContenido());
		}
		if(fileDtoWS.getIdContenido() != null){
			fileDto.setIdContenido(fileDtoWS.getIdContenido());
		}
		if(fileDtoWS.getIdEmpresa() != null){
			fileDto.setIdEmpresa(fileDtoWS.getIdEmpresa());
		}	
		if(fileDtoWS.getIdPersona() != null){
			fileDto.setIdPersona(fileDtoWS.getIdPersona());
		}
		if(fileDtoWS.getIdTipoContenido() != null){
			fileDto.setIdTipoContenido(fileDtoWS.getIdTipoContenido());
		}
		if(fileDtoWS.getTipoArchivo() != null){
			fileDto.setTipoArchivo(fileDtoWS.getTipoArchivo());
		}			
		return fileDto;
	}
	
}
