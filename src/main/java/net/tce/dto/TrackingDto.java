package net.tce.dto;

import java.util.Date;
import java.util.List;

public class TrackingDto extends ComunDto {

	private String idTracking;

	//Comun
	private String nombre;
	private String descripcion;
	private String orden;
	private String activo;	
	
	//Plantilla_rol
	private String idPlantillaRol;
	private String idRol;
	
	//Tracking_plantilla
	private String idTrackingPlantilla;
//	private String idTrackingPlantillaRel;
	
	//Tracking_esquema (Posicion)
	private String idTrackingEsquema;
	private String idEsquemaPerfil;
	private String idPerfilPosicion;
	private String idTrackingEsquemaRel;
	private String subirArchivo;
	private String bajarArchivo;
	private String editarComentario;
	private String fechaInicio;
	private String fechaFin;
	
	private List<TrackingDto> tracking; //Estados
		
	private String idPersona;
	private String idPosicion;
	private String idCandidato;
	private String idContratante;
	
	
	public TrackingDto() {
	}
	/**
	 * Constructor para PLANTILLA_ROL
	 * @param idPlantillaRol
	 * @param idRol
	 * @param nombre
	 * @param descripcion
	 * @param activo
	 */
	public TrackingDto(Long idPlantillaRol, String nombre, String descripcion, Boolean activo, Long idRol) {		
		this.idPlantillaRol=idPlantillaRol!=null?String.valueOf(idPlantillaRol):null;
		this.nombre=nombre;
		this.descripcion=descripcion;
		this.activo=activo!=null?activo.booleanValue()?"1":"0":"1";
		this.idRol=idRol!=null?String.valueOf(idRol):null;
		this.setStatus("\n\n >>>>>>>>>>>   Constructor para PLANTILLA_ROL <<<<<<<<<<<<<<<<<<<<<<\n\n");
	}
	/**
	 * Constructor para TRACKING_PLANTILLA (Estado-plantilla)
	 * @param idTrackingPlantilla
	 * @param idTrackingPlantillaRel
	 * @param idPlantillaRol
	 * @param orden
	 * @param nombre
	 * @param descripcion
	 */
	public TrackingDto(Long idTrackingPlantilla, Long idPlantillaRol, 
			Short orden, String nombre, String descripcion) {
		this.idTrackingPlantilla=idTrackingPlantilla!=null?String.valueOf(idTrackingPlantilla):null;
//		this.idTrackingPlantillaRel=idTrackingPlantillaRel!=null?String.valueOf(idTrackingPlantillaRel):null;
		this.idPlantillaRol=idPlantillaRol!=null?String.valueOf(idPlantillaRol):null;
		this.orden=orden!=null?String.valueOf(orden):null;
		this.nombre=nombre;
		this.descripcion=descripcion;
		this.setStatus("\n\n >>>>>>>>>>>   Constructor para TRACKING_PLANTILLA <<<<<<<<<<<<<<<<<<<<<<\n\n");
	}
	
	
	/**
	 * Constructor para ESQUEMA_PERFIL
	 * @param idPlantillaRol
	 * @param idRol
	 * @param nombre
	 * @param descripcion
	 * @param activo
	 */
	public TrackingDto(Long idEsquemaPerfil, Long idPerfilPosicion, String nombre, String descripcion, Boolean activo) {		
		this.idEsquemaPerfil=idEsquemaPerfil!=null?String.valueOf(idEsquemaPerfil):null;
		this.idPerfilPosicion=idPerfilPosicion!=null?String.valueOf(idPerfilPosicion):null;
		this.nombre=nombre;
		this.descripcion=descripcion;
		this.activo=activo!=null?activo.booleanValue()?"1":"0":"1";
		this.setStatus("\n\n >>>>>>>>>>>   Constructor para ESQUEMA_PERFIL <<<<<<<<<<<<<<<<<<<<<<\n\n");
	}
	/**
	 * Constructor para TRACKING_ESQUEMA (Estados-TrackPosicion)
	 * @param idTrackingEsquema
	 * @param idTrackingPlantilla
	 * @param idEsquemaPerfil
	 * @param idTrackingEsquemaRel
	 * @param orden
	 * @param nombre
	 * @param descripcion
	 * @param subirArchivo
	 * @param bajarArchivo
	 * @param editarComentario
	 * @param fechaInicio
	 * @param fechaFin
	 */
	public TrackingDto(Long idTrackingEsquema, Long idTrackingEsquemaRel, Long idEsquemaPerfil, Short orden, String nombre, String descripcion, 
			Boolean subirArchivo, Boolean bajarArchivo, Boolean editarComentario, Date fechaInicio, Date fechaFin){
		
		this.idTrackingEsquema=idTrackingEsquema!=null?String.valueOf(idTrackingEsquema):null;
		this.idTrackingEsquemaRel=idTrackingEsquemaRel!=null?String.valueOf(idTrackingEsquemaRel):null;
		this.idEsquemaPerfil=idEsquemaPerfil!=null?String.valueOf(idEsquemaPerfil):null;
		
		this.orden=orden!=null?String.valueOf(orden):null;
		this.nombre=nombre;
		this.descripcion=descripcion;
		
		this.subirArchivo=subirArchivo!=null?subirArchivo.booleanValue()?"1":"0":"1";
		this.bajarArchivo=bajarArchivo!=null?bajarArchivo.booleanValue()?"1":"0":"1";
		this.editarComentario=editarComentario!=null?editarComentario.booleanValue()?"1":"0":"1";
		
		this.fechaInicio=fechaInicio!=null?String.valueOf(fechaInicio):null;
		this.fechaFin=fechaFin!=null?String.valueOf(fechaFin):null;
		this.setStatus("\n\n >>>>>>>>>>>   Constructor para TRACKING_ESQUEMA <<<<<<<<<<<<<<<<<<<<<<\n\n");
	}
	
	/*public TrackingDto(Long idTracking, Long orden, Long idTrackRef, Long idPerPos, Long idRol, 
			Boolean pUpload, Boolean pDownload, String fechaInicio, String fechaFin, Boolean pComentario,
			String descripcion, Boolean activo) {
		this.idTracking=idTracking!=null?String.valueOf(idTracking):null;
		this.orden=orden!=null?String.valueOf(orden):null;
		this.idTrackReferencia=idTrackRef!=null?String.valueOf(idTrackRef):null;
		this.idPerfilPosicion=idPerPos!=null?String.valueOf(idPerPos):null;
		this.idRol=idRol!=null?String.valueOf(idRol):null;
		this.subirArchivo=pUpload!=null?pUpload.booleanValue()?"1":"0":"0";
		this.bajarArchivo=pDownload!=null?pDownload.booleanValue()?"1":"0":"0";
		this.fechaInicio=fechaInicio;
		this.fechaFin=fechaFin;
		this.editarComentario=pComentario!=null?pComentario.booleanValue()?"1":"0":"0";
		this.descripcion=descripcion;
		this.activo=activo!=null?activo.booleanValue()?"1":"0":"1";
	}*/
	
	public String getIdTracking() {
		return idTracking;
	}
	public void setIdTracking(String idTracking) {
		this.idTracking = idTracking;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getOrden() {
		return orden;
	}
	public void setOrden(String orden) {
		this.orden = orden;
	}
	public String getActivo() {
		return activo;
	}
	public void setActivo(String activo) {
		this.activo = activo;
	}
	public String getIdPlantillaRol() {
		return idPlantillaRol;
	}
	public void setIdPlantillaRol(String idPlantillaRol) {
		this.idPlantillaRol = idPlantillaRol;
	}
	public String getIdRol() {
		return idRol;
	}
	public void setIdRol(String idRol) {
		this.idRol = idRol;
	}
	public String getIdTrackingPlantilla() {
		return idTrackingPlantilla;
	}
	public void setIdTrackingPlantilla(String idTrackingPlantilla) {
		this.idTrackingPlantilla = idTrackingPlantilla;
	}
//	public String getIdTrackingPlantillaRel() {
//		return idTrackingPlantillaRel;
//	}
//	public void setIdTrackingPlantillaRel(String idTrackingPlantillaRel) {
//		this.idTrackingPlantillaRel = idTrackingPlantillaRel;
//	}
	public String getIdTrackingEsquema() {
		return idTrackingEsquema;
	}
	public void setIdTrackingEsquema(String idTrackingEsquema) {
		this.idTrackingEsquema = idTrackingEsquema;
	}
	public String getIdEsquemaPerfil() {
		return idEsquemaPerfil;
	}
	public void setIdEsquemaPerfil(String idEsquemaPerfil) {
		this.idEsquemaPerfil = idEsquemaPerfil;
	}
	public String getIdPerfilPosicion() {
		return idPerfilPosicion;
	}
	public void setIdPerfilPosicion(String idPerfilPosicion) {
		this.idPerfilPosicion = idPerfilPosicion;
	}
	public String getIdTrackingEsquemaRel() {
		return idTrackingEsquemaRel;
	}
	public void setIdTrackingEsquemaRel(String idTrackingEsquemaRel) {
		this.idTrackingEsquemaRel = idTrackingEsquemaRel;
	}
	public String getSubirArchivo() {
		return subirArchivo;
	}
	public void setSubirArchivo(String subirArchivo) {
		this.subirArchivo = subirArchivo;
	}
	public String getBajarArchivo() {
		return bajarArchivo;
	}
	public void setBajarArchivo(String bajarArchivo) {
		this.bajarArchivo = bajarArchivo;
	}
	public String getEditarComentario() {
		return editarComentario;
	}
	public void setEditarComentario(String editarComentario) {
		this.editarComentario = editarComentario;
	}
	public String getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public String getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(String idPersona) {
		this.idPersona = idPersona;
	}
	public String getIdPosicion() {
		return idPosicion;
	}
	public void setIdPosicion(String idPosicion) {
		this.idPosicion = idPosicion;
	}
	public String getIdCandidato() {
		return idCandidato;
	}
	public void setIdCandidato(String idCandidato) {
		this.idCandidato = idCandidato;
	}
	public String getIdContratante() {
		return idContratante;
	}
	public void setIdContratante(String idContratante) {
		this.idContratante = idContratante;
	}
	public List<TrackingDto> getTracking() {
		return tracking;
	}
	public void setTracking(List<TrackingDto> tracking) {
		this.tracking = tracking;
	}
}
