package net.tce.dto;

public class EmpresaConfDto {

	private Long idEmpresaConf;
	private Long idEmpresa;
	private Long idConf;
	
	public EmpresaConfDto(){}
	public EmpresaConfDto(Long idEmpresaConf,Long idEmpresa, Long idConf){
		this.idEmpresaConf=idEmpresaConf;
		this.idEmpresa=idEmpresa;
		this.idConf=idConf;
	}
	
	
	
	public Long getIdEmpresaConf() {
		return idEmpresaConf;
	}
	public void setIdEmpresaConf(Long idEmpresaConf) {
		this.idEmpresaConf = idEmpresaConf;
	}
	public Long getIdConf() {
		return idConf;
	}
	public void setIdConf(Long idConf) {
		this.idConf = idConf;
	}
	public Long getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	
	
}
