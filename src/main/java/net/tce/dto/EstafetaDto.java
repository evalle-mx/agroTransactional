package net.tce.dto;

public class EstafetaDto {
	private String code;
	private String type;
	private String message;
	
	public EstafetaDto(){}
	public EstafetaDto(String code,String type,String message){
		this.code=code;
		this.type=type;
		this.message=message;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	public String getCode() {
		return code;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
}
