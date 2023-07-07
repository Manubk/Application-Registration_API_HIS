package com.ar.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ar.dto.ARdto;
import com.ar.serviceinterface.IApplicationRegistrationService;

@CrossOrigin
@RestController
public class ApplicationRegistrationController {
	
	
	private static final Logger log = LoggerFactory.getLogger(ApplicationRegistrationController.class);

	@Autowired
	private IApplicationRegistrationService appRegService;
	
	@PostMapping("/appReg")
	public ResponseEntity<String> validateAndSave(@RequestBody ARdto arDto){
		log.info("validateAndSave");
		
		Long isSaved = appRegService.save(arDto);
		
		if(isSaved > 0) {
			return new ResponseEntity<String>("Citizen Eligible & AppId : "+isSaved,HttpStatus.OK);
		}else
			return new ResponseEntity<String>("Sorry You are not eligible",HttpStatus.NOT_ACCEPTABLE);

	}
	
	@GetMapping("/applicants")
	public ResponseEntity<List< ARdto>> getAllApplicants() {
	log.info("getAllApplicants");
	
	List<ARdto> applicants = appRegService.getAllApplicants();
	
	return new ResponseEntity<List<ARdto>>(applicants,HttpStatus.OK);
	
	}
}
