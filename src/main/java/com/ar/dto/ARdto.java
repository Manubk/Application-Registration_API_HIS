package com.ar.dto;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class ARdto {
	
	private String fullName;
	private String email;
	private String phoneNo;
	private String gender;
	private LocalDate dob;
	private String ssn;
}
