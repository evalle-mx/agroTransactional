package net.tce.dto;


public class EmpresaParametroDto extends ComunDto {

	private String idEmpresaParametro;
	private String idTipoParametro;
	private String orden;
	private String contexto;
	private String valor;
	private String condicion;
	private String descripcion;
	private String idEmpresa;
	private String fechaCreacion;
	private String fechaModificacion;
	private String funcion;
	
	private String idConf;
	
	public EmpresaParametroDto(){}
	
	public EmpresaParametroDto(String contexto, String valor){
		this.contexto=contexto;
		this.valor=valor;
	}
	
	public EmpresaParametroDto(String idTipoParametro, String idEmpresaConf, String dummy){
		this.idTipoParametro=idTipoParametro;
		this.setIdEmpresaConf(idEmpresaConf);
	}

	public String getContexto() {
		return contexto;
	}

	public void setContexto(String contexto) {
		this.contexto = contexto;
	}

	public String getCondicion() {
		return condicion;
	}

	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setIdEmpresaParametro(String idEmpresaParametro) {
		this.idEmpresaParametro = idEmpresaParametro;
	}

	public String getIdEmpresaParametro() {
		return idEmpresaParametro;
	}

	public void setIdTipoParametro(String idTipoParametro) {
		this.idTipoParametro = idTipoParametro;
	}

	public String getIdTipoParametro() {
		return idTipoParametro;
	}

	public void setOrden(String orden) {
		this.orden = orden;
	}

	public String getOrden() {
		return orden;
	}

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(String fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getFuncion() {
		return funcion;
	}

	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}

	public String getIdConf() {
		return idConf;
	}

	public void setIdConf(String idConf) {
		this.idConf = idConf;
	}

	
}