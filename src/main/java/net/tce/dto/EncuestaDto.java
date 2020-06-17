package net.tce.dto;

import java.util.List;

public class EncuestaDto extends ComunDto {

	private String idEvaluacion;
	private String idUsuario;
	private String usuario;
	private String idEvaluado;
	private String evaluado;
	private String total;
	private String idCorrelacion;
	private String correlacion;
	private String terminado;
//	private List<ReactivoDto> reactivos;
//	private List<ReactivoDto> respuestas;
	
	public EncuestaDto() {}
//	public EncuestaDto(Long idEvaluacion, Long idUsuario, Long idEvaluado, Long idCorrelacion, Boolean bTerminado) {
//		this.idEvaluacion=idEvaluacion!=null?String.valueOf(idEvaluacion):null;
//		this.idUsuario=idUsuario!=null?String.valueOf(idUsuario):null;
//		this.idEvaluado=idEvaluado!=null?String.valueOf(idEvaluado):null;
//		this.idCorrelacion=idCorrelacion!=null?String.valueOf(idCorrelacion):null;
//		this.terminado = bTerminado!=null?(bTerminado?"1":"0"):"0";
//	}
	public EncuestaDto(Long idEvaluacion, Long idUsuario, String usuario, 
			Long idEvaluado, String evaluado, Long idCorrelacion, String correlacion, Boolean bTerminado) {
		this.idEvaluacion=idEvaluacion!=null?String.valueOf(idEvaluacion):null;
		this.idUsuario=idUsuario!=null?String.valueOf(idUsuario):null;
		this.usuario=usuario;
		this.idEvaluado=idEvaluado!=null?String.valueOf(idEvaluado):null;
		this.evaluado=evaluado;
		this.idCorrelacion=idCorrelacion!=null?String.valueOf(idCorrelacion):null;
		this.correlacion = correlacion;
		this.terminado = bTerminado!=null?(bTerminado?"1":"0"):"0";
	}
	
	public EncuestaDto(Long total,Long idEvaluado,String evaluado) {
		this.total=total.toString();
		this.idEvaluado=idEvaluado!=null?String.valueOf(idEvaluado):null;
		this.evaluado=evaluado;
	}
	
	
	
	
	public String getIdEvaluacion() {
		return idEvaluacion;
	}
	public void setIdEvaluacion(String idEvaluacion) {
		this.idEvaluacion = idEvaluacion;
	}
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = evaluado;
	}
	public String getIdEvaluado() {
		return idEvaluado;
	}
	public void setIdEvaluado(String idEvaluado) {
		this.idEvaluado = idEvaluado;
	}
	public String getEvaluado() {
		return evaluado;
	}
	public void setEvaluado(String evaluado) {
		this.evaluado = evaluado;
	}
	public String getIdCorrelacion() {
		return idCorrelacion;
	}
	public void setIdCorrelacion(String idCorrelacion) {
		this.idCorrelacion = idCorrelacion;
	}
	public String getCorrelacion() {
		return correlacion;
	}
	public void setCorrelacion(String correlacion) {
		this.correlacion = correlacion;
	}
	public String getTerminado() {
		return terminado;
	}
	public void setTerminado(String terminado) {
		this.terminado = terminado;
	}
//	public List<ReactivoDto> getReactivos() {
//		return reactivos;
//	}
//	public void setReactivos(List<ReactivoDto> reactivos) {
//		this.reactivos = reactivos;
//	}
//	public List<ReactivoDto> getRespuestas() {
//		return respuestas;
//	}
//	public void setRespuestas(List<ReactivoDto> respuestas) {
//		this.respuestas = respuestas;
//	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
}
