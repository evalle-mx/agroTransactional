package net.tce.admin.service;

import net.tce.dto.EncuestaDto;

public interface EncuestaService {

	Object get(EncuestaDto dto);
	String read(EncuestaDto dto);
	String update(EncuestaDto dto);
	Object questionary(EncuestaDto dto);
}

