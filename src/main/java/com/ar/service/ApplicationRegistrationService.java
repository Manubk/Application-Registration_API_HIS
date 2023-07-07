package com.ar.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

import com.ar.constants.AppConstants;
import com.ar.dto.ARdto;
import com.ar.entity.ApplicationRegistration;
import com.ar.exception.InvalidSsnException;
import com.ar.repo.ApplicationRegistrationRepo;
import com.ar.serviceinterface.IApplicationRegistrationService;


@Service
public class ApplicationRegistrationService  implements IApplicationRegistrationService{

	
	private static final Logger log = LoggerFactory.getLogger(ApplicationRegistrationService.class);

	@Autowired
	private ApplicationRegistrationRepo appRegRepo;
	
	
	@Override
	public Long save(ARdto arDto) throws InvalidSsnException {
		log.info("save");
		
		String stateName = "" ;
		
		stateName = getStateName(arDto.getSsn());
		
		//TODO ssn validate webclient login by calling ssn api
		
		if(stateName.equalsIgnoreCase(AppConstants.NEW_JERSEY)) {
			ApplicationRegistration appReg = new ApplicationRegistration();
			
			BeanUtils.copyProperties(arDto, appReg);
			
			appReg.setStateName(stateName);
			appReg.setActiveSw(AppConstants.ACTIVE);
			
			log.info(arDto.toString());
			log.info(appReg.toString());
			
			ApplicationRegistration appRegSaved = appRegRepo.save(appReg);
			
			return appRegSaved.getAppId();
		}else {
			return (long) 0;
		}
	
	}
	
	
	private String getStateName(String ssnNum){
			log.info("getStatename");
	
			if(ssnNum == null || ssnNum.equals("") || ssnNum.length() < 9)
				throw new InvalidSsnException(AppConstants.INVALID_SSN);
		
			String stateCode = ssnNum.substring(0,3);
			
			if(Integer.parseInt(stateCode) >= 135 && Integer.parseInt(stateCode) <= 158)
				return AppConstants.NEW_JERSEY;
			else
				throw new InvalidSsnException(AppConstants.INVALID_SSN);

	}


	@Override
	public List<ARdto> getAllApplicants() {
		log.info("getAllApplicants");
		
		List<ApplicationRegistration> allApp= appRegRepo.findAll();
		List<ARdto> ArDtos = new ArrayList<>();
		
		ARdto arDto ;
		
		for(ApplicationRegistration arEntity : allApp) {
			arDto = new ARdto();
			
			BeanUtils.copyProperties(arEntity, arDto);
			ArDtos.add(arDto);
		}
		
		return ArDtos;
	}
}
