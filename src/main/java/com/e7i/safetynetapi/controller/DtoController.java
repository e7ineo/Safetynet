package com.e7i.safetynetapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.e7i.safetynetapi.dto.DtoFactory;

@RestController
public class DtoController {
	
	@GetMapping("/firestation")
	public ResponseEntity<DtoFactory> firestation(@RequestParam int stationNumber) {
		DtoFactory udf = new DtoFactory();
		udf.createFirestationDto(stationNumber);
		if(!udf.getUsersDto().isEmpty()) {
			return new ResponseEntity<DtoFactory>(udf, HttpStatus.OK);			
		} else {
			udf.setErrorMessage("stationNumber Incorect");
			return new ResponseEntity<DtoFactory>(udf, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/childAlert")
	public ResponseEntity<DtoFactory> childAlert(@RequestParam String address) {
		DtoFactory udf = new DtoFactory();
		udf.createChildAlertDto(address);		
		return new ResponseEntity<DtoFactory>(udf, HttpStatus.OK);
	}
	
	@GetMapping("/phoneAlert")
	public ResponseEntity<DtoFactory> phoneAlert(@RequestParam int firestation) {
		DtoFactory udf = new DtoFactory();
		udf.createPhoneNumberDto(firestation);		
		return new ResponseEntity<DtoFactory>(udf,HttpStatus.OK);
	}
	
	@GetMapping("/fire")
	public ResponseEntity<DtoFactory> fire(@RequestParam String address) {
		DtoFactory udf = new DtoFactory();
		udf.createFireDto(address);		
		return new ResponseEntity<DtoFactory>(udf,HttpStatus.OK);
	}
	
	@GetMapping("/flood")
	public ResponseEntity<DtoFactory> flood(@RequestParam int[] stationNumbers) {
		DtoFactory udf = new DtoFactory();
		udf.createFloodDto(stationNumbers);		
		return new ResponseEntity<DtoFactory>(udf,HttpStatus.OK);
	}
	
	@GetMapping("/personInfo")
	public ResponseEntity<DtoFactory> personInfo(@RequestParam String firstName, String lastName) {
		DtoFactory udf = new DtoFactory();
		udf.createPersonInfoDto(firstName, lastName);
		return new ResponseEntity<DtoFactory>(udf,HttpStatus.OK);
	}
	
	@GetMapping("/communityEmail")
	public ResponseEntity<DtoFactory> communityEmail(@RequestParam String city) {
		DtoFactory udf = new DtoFactory();
		udf.createCommunityEmailDto(city);		
		return new ResponseEntity<DtoFactory>(udf,HttpStatus.OK);
	}
}
