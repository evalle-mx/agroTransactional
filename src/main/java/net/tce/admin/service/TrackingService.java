package net.tce.admin.service;

import net.tce.dto.TrackingDto;

public interface TrackingService {
	
	String create(TrackingDto trackDto) throws Exception;
	String read(TrackingDto trackDto) throws Exception;
	String update(TrackingDto trackDto) throws Exception;
	String delete(TrackingDto trackDto) throws Exception;

	Object get(TrackingDto dto) throws Exception;
	

}
