package com.ar.serviceinterface;

import java.util.List;

import com.ar.dto.ARdto;

public interface IApplicationRegistrationService {
	public Long save(ARdto arDto) ;
	public List<ARdto> getAllApplicants();
}
