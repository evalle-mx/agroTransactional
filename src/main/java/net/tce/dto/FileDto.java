package net.tce.dto;


import javax.activation.DataHandler;

public class FileDto extends ComunDto{

	  private String idContenido;
	  private String idPersona;
	  private String idSolicitante;
	  private String idPosicion;
	  private String idEmpresa;
	  private String tipoArchivo;
	  private String idTipoDocumento;
	  private DataHandler dhContenido;
	  private String idTipoContenido;
	  private String fileDescripcion;
	  private String repParams;
	  private String url;
	  private String isFile;
	  
	  private String fileTitle;
	  private String fileName;
	  private String fileFolder;

	  public FileDto(){}
	  public FileDto(String idEmpresaConf,String idPersona,String idEmpresa ,String idTipoContenido){
		   this.setIdEmpresaConf(idEmpresaConf);
		   this.idPersona=idPersona;
		   this.idTipoContenido=idTipoContenido;
		   this.idEmpresa=idEmpresa;
	   }
	  
	  public FileDto(String idEmpresaConf, String idSolicitante, String idTipoDocumento, String url, boolean isFile){
		  this.setIdEmpresaConf(idEmpresaConf);
		   this.idSolicitante=idSolicitante;
		   this.idTipoDocumento=idTipoDocumento;
		   this.url=url;
		   this.isFile=isFile?"1":"0";
	  }
	
		public void setTipoArchivo(String tipoArchivo) {
			this.tipoArchivo = tipoArchivo;
		}
		public String getTipoArchivo() {
			return tipoArchivo;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getUrl() {
			return url;
		}
		public void setIdTipoContenido(String idTipoContenido) {
			this.idTipoContenido = idTipoContenido;
		}
		public String getIdTipoContenido() {
			return idTipoContenido;
		}
		
		public void setDhContenido(DataHandler dhContenido) {
			this.dhContenido = dhContenido;
		}
		public DataHandler getDhContenido() {
			return dhContenido;
		}
		public void setIdContenido(String idContenido) {
			this.idContenido = idContenido;
		}
		public String getIdContenido() {
			return idContenido;
		}
		public void setIdPersona(String idPersona) {
			this.idPersona = idPersona;
		}
		public String getIdPersona() {
			return idPersona;
		}
		public void setIdEmpresa(String idEmpresa) {
			this.idEmpresa = idEmpresa;
		}
		public String getIdEmpresa() {
			return idEmpresa;
		}
		public String getIdPosicion() {
			return idPosicion;
		}
		public void setIdPosicion(String idPosicion) {
			this.idPosicion = idPosicion;
		}
		public String getFileDescripcion() {
			return fileDescripcion;
		}
		public void setFileDescripcion(String fileDescripcion) {
			this.fileDescripcion = fileDescripcion;
		}
		public String getRepParams() {
			return repParams;
		}
		public void setRepParams(String repParams) {
			this.repParams = repParams;
		}
		public String getIdSolicitante() {
			return idSolicitante;
		}
		public void setIdSolicitante(String idSolicitante) {
			this.idSolicitante = idSolicitante;
		}
		public String getIdTipoDocumento() {
			return idTipoDocumento;
		}
		public void setIdTipoDocumento(String idTipoDocumento) {
			this.idTipoDocumento = idTipoDocumento;
		}
		public String getIsFile() {
			return isFile;
		}
		public void setIsFile(String isFile) {
			this.isFile = isFile;
		}
		public String getFileTitle() {
			return fileTitle;
		}
		public void setFileTitle(String fileTitle) {
			this.fileTitle = fileTitle;
		}
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		public String getFileFolder() {
			return fileFolder;
		}
		public void setFileFolder(String fileFolder) {
			this.fileFolder = fileFolder;
		}
}
