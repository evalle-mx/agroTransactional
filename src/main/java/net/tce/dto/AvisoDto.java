package net.tce.dto;

public class AvisoDto {
	
	private Long idAvisoCandidato;
	private Long idAviso;
	private String claveAviso;
	private Boolean borrado;
	private String texto;
	
	public AvisoDto(){}
	
	public AvisoDto(String texto){
		this.texto=texto;
	}
	
	public AvisoDto(Long idAviso,String claveAviso,Long idAvisoCandidato, String borrado){
		this.idAvisoCandidato=idAvisoCandidato;
		this.claveAviso=claveAviso;
		this.idAviso=idAviso;
		this.borrado=borrado != null ? Boolean.valueOf(borrado):null;
	}
	
	
	public String getClaveAviso() {
		return claveAviso;
	}
	public void setClaveAviso(String claveAviso) {
		this.claveAviso = claveAviso;
	}
	public Long getIdAviso() {
		return idAviso;
	}
	public void setIdAviso(Long idAviso) {
		this.idAviso = idAviso;
	}
	public Long getIdAvisoCandidato() {
		return idAvisoCandidato;
	}
	public void setIdAvisoCandidato(Long idAvisoCandidato) {
		this.idAvisoCandidato = idAvisoCandidato;
	}
	public Boolean getBorrado() {
		return borrado;
	}
	public void setBorrado(Boolean borrado) {
		this.borrado = borrado;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	
}
