package net.tce.dto;


import java.util.concurrent.ConcurrentHashMap;

public class CacheFileDto {

	private String carpetaFisico;
	private int concurrencias;
	private ConcurrentHashMap<String,FileDto> chmArchivos;
	
	public void setChmArchivos(ConcurrentHashMap<String,FileDto> chmArchivos) {
		this.chmArchivos = chmArchivos;
	}
	public ConcurrentHashMap<String,FileDto> getChmArchivos() {
		return chmArchivos;
	}
	public void setCarpetaFisico(String carpetaFisico) {
		this.carpetaFisico = carpetaFisico;
	}
	public String getCarpetaFisico() {
		return carpetaFisico;
	}
	public void setConcurrencias(int concurrencias) {
		this.concurrencias = concurrencias;
	}
	public int getConcurrencias() {
		return concurrencias;
	}
	
}
