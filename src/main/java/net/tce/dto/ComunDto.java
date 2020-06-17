package net.tce.dto;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;


public class ComunDto extends EstafetaDto {
	private String messages;
	private String idEmpresaConf;
	private String modo;
	private String anioInicioOrig;
	private String mesInicioOrig;
	private String diaInicioOrig;
	private String anioFinOrig;
	private String mesFinOrig;
	private String diaFinOrig;
	private Boolean parcial;
	private String status;
	
	public String getAnioInicioOrig() {
		return anioInicioOrig;
	}

	public void setAnioInicioOrig(String anioInicioOrig) {
		this.anioInicioOrig = anioInicioOrig;
	}

	public String getMesInicioOrig() {
		return mesInicioOrig;
	}

	public void setMesInicioOrig(String mesInicioOrig) {
		this.mesInicioOrig = mesInicioOrig;
	}

	public String getDiaInicioOrig() {
		return diaInicioOrig;
	}

	public void setDiaInicioOrig(String diaInicioOrig) {
		this.diaInicioOrig = diaInicioOrig;
	}

	public String getAnioFinOrig() {
		return anioFinOrig;
	}

	public void setAnioFinOrig(String anioFinOrig) {
		this.anioFinOrig = anioFinOrig;
	}

	public String getMesFinOrig() {
		return mesFinOrig;
	}

	public void setMesFinOrig(String mesFinOrig) {
		this.mesFinOrig = mesFinOrig;
	}

	public String getDiaFinOrig() {
		return diaFinOrig;
	}

	public void setDiaFinOrig(String diaFinOrig) {
		this.diaFinOrig = diaFinOrig;
	}

	
	

	public ComunDto(){}
	
	public ComunDto(String messages){
		this.messages=messages;
	}
	
	public ComunDto(String code,String type ,String message){
		this.setCode(code);
		this.setType(type);
		this.setMessage(message);
	}
	

	public void setMessages(String messages) {
		this.messages = messages;
	}

	public String getMessages() {
		return messages;
	}

	public void setModo(String modo) {
		this.modo = modo;
	}

	public String getModo() {
		return modo;
	}

	public Boolean getParcial() {
		return parcial;
	}

	public void setParcial(Boolean parcial) {
		this.parcial = parcial;
	}

    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIdEmpresaConf() {
		return idEmpresaConf;
	}

	public void setIdEmpresaConf(String idEmpresaConf) {
		this.idEmpresaConf = idEmpresaConf;
	}
}
