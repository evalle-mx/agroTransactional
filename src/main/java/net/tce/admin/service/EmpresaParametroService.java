package net.tce.admin.service;

import net.tce.app.exception.SystemTCEException;
import net.tce.dto.EmpresaParametroDto;

public interface EmpresaParametroService {
	
	String get(EmpresaParametroDto empresaParametroDto) throws SystemTCEException;
	
	String create(EmpresaParametroDto empresaParametroDto) throws SystemTCEException;
	String update(EmpresaParametroDto empresaParametroDto) throws SystemTCEException;
	String delete(EmpresaParametroDto empresaParametroDto) throws SystemTCEException;
	String updateMultiple(String json) throws SystemTCEException;
	String reloadCache(EmpresaParametroDto empresaParametroDto) throws SystemTCEException;
}
