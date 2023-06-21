package com.ar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ar.constants.AppConstants;

@RestControllerAdvice
public class ExceptionHandler {
	
	@org.springframework.web.bind.annotation.ExceptionHandler(InvalidSsnException.class)
	public ResponseEntity<ExceptionBean> invalidSsnException(InvalidSsnException e){
		
		ExceptionBean exception = new ExceptionBean();
		
		exception.setCode(AppConstants.INVALID_DETAILS_CODE);
		exception.setMsg(e.getMessage());
		
		return new ResponseEntity<ExceptionBean>(exception,HttpStatus.BAD_REQUEST);
	}
	
	
	@org.springframework.web.bind.annotation.ExceptionHandler(value = InvalidDetails.class)
	public ResponseEntity<ExceptionBean> invalidDetails(InvalidDetails e){
		
		ExceptionBean exception  = new ExceptionBean();
		exception.setCode(AppConstants.INVALID_DETAILS_CODE);
		exception.setMsg(e.getMessage());
		
		return new ResponseEntity<ExceptionBean>(exception,HttpStatus.BAD_REQUEST);
	}
}
