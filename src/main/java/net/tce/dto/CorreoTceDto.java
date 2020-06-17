package net.tce.dto;

import java.io.File;
import java.util.List;

public class CorreoTceDto {
	
	private String destinatario;
	private String remitente;
	private List<String> lsCcs;
	private List<String> lsBcs;	
	private String asunto;
	private String cuerpo;	
	private File adjunto;
	
	public CorreoTceDto(){}
	public CorreoTceDto(String remitente,String destinatario,String asunto,
						String cuerpo,File adjunto){
		this.remitente=remitente;
		this.destinatario=destinatario;
		this.asunto=asunto;
		this.cuerpo=cuerpo;
		this.adjunto=adjunto;
	}
	
	public String getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	public String getRemitente() {
		return remitente;
	}
	public void setRemitente(String remitente) {
		this.remitente = remitente;
	}
	public List<String> getLsCcs() {
		return lsCcs;
	}
	public void setLsCcs(List<String> lsCcs) {
		this.lsCcs = lsCcs;
	}
	public List<String> getLsBcs() {
		return lsBcs;
	}
	public void setLsBcs(List<String> lsBcs) {
		this.lsBcs = lsBcs;
	}
	public String getCuerpo() {
		return cuerpo;
	}
	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}
	public java.io.File getAdjunto() {
		return adjunto;
	}
	public void setAdjunto(java.io.File adjunto) {
		this.adjunto = adjunto;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	

}
