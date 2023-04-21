package com.e7i.safetynetapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.e7i.safetynetapi.dto.DtoFactory;

@RestController
public class DtoController {
	
	@GetMapping("/firestation")
	public DtoFactory firestation(@RequestParam int stationNumber) {
		DtoFactory udf = new DtoFactory();
		udf.createFirestationDto(stationNumber);
		return udf;
	}
	
	@GetMapping("/childAlert")
	public DtoFactory childAlert(@RequestParam String address) {
		DtoFactory udf = new DtoFactory();
		udf.createChildAlertDto(address);		
		return udf;
	}
	
	@GetMapping("/phoneAlert")
	public DtoFactory phoneAlert(@RequestParam int firestation) {
		DtoFactory udf = new DtoFactory();
		udf.createPhoneNumberDto(firestation);		
		return udf;
	}
	
	@GetMapping("/fire")
	public DtoFactory fire(@RequestParam String address) {
		DtoFactory udf = new DtoFactory();
		udf.createFireDto(address);		
		return udf;
	}
	
	@GetMapping("/flood")
	public DtoFactory flood(@RequestParam int[] stationNumbers) {
		DtoFactory udf = new DtoFactory();
		udf.createFloodDto(stationNumbers);		
		return udf;
	}
	
	@GetMapping("/personInfo")
	public DtoFactory personInfo(@RequestParam String firstName, String lastName) {
		DtoFactory udf = new DtoFactory();
		udf.createPersonInfoDto(firstName, lastName);
		return udf;
	}
	
	@GetMapping("/communityEmail")
	public DtoFactory communityEmail(@RequestParam String city) {
		DtoFactory udf = new DtoFactory();
		udf.createCommunityEmailDto(city);		
		return udf;
	}
}
