package net.tce.dto;

public class FileDataConfDto extends ComunDto{
	
	private String idContenido;
	private String idPersona;
	private String idEmpresa;
	private String idPersonaHandCheck;
	private String idEmpresaHandCheck;
	private String contenido;
	private String idTipoContenidoArchivo;
	private String idTipoContenido;
	private String descripcion;
	private String fechaCarga;
	  
	public FileDataConfDto(){}
	
	public FileDataConfDto(String idEmpresaConf,String idTipoContenido){
		this.idTipoContenido=idTipoContenido;
		this.setIdEmpresaConf(idEmpresaConf);
	}
	
	public String getIdContenido() {
		return idContenido;
	}
	public void setIdContenido(String idContenido) {
		this.idContenido = idContenido;
	}
	
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public String getIdTipoContenidoArchivo() {
		return idTipoContenidoArchivo;
	}
	public void setIdTipoContenidoArchivo(String idTipoContenidoArchivo) {
		this.idTipoContenidoArchivo = idTipoContenidoArchivo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getFechaCarga() {
		return fechaCarga;
	}
	public void setFechaCarga(String fechaCarga) {
		this.fechaCarga = fechaCarga;
	}
	public void setIdTipoContenido(String idTipoContenido) {
		this.idTipoContenido = idTipoContenido;
	}
	public String getIdTipoContenido() {
		return idTipoContenido;
	}
	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public String getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdPersona(String idPersona) {
		this.idPersona = idPersona;
	}
	public String getIdPersona() {
		return idPersona;
	}
	public void setIdPersonaHandCheck(String idPersonaHandCheck) {
		this.idPersonaHandCheck = idPersonaHandCheck;
	}
	public String getIdPersonaHandCheck() {
		return idPersonaHandCheck;
	}
	public void setIdEmpresaHandCheck(String idEmpresaHandCheck) {
		this.idEmpresaHandCheck = idEmpresaHandCheck;
	}
	public String getIdEmpresaHandCheck() {
		return idEmpresaHandCheck;
	}
	
}
