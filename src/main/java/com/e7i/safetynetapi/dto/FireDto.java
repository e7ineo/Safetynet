package com.e7i.safetynetapi.dto;

import com.e7i.safetynetapi.data.UserData;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FireDto implements Dto {
	
	private int stationNumber;
	private String firstName;
	private String lastName;
	private int age;
	private String phoneNumber;
	private String[] mediacations;
	private String[] allergies;
	
	public FireDto(UserData userData) {
		this.stationNumber = userData.getFirestationNumber();
		this.firstName = userData.getFirstName();
		this.lastName = userData.getLastName();
		this.phoneNumber = userData.getPhoneNumber();
		this.age = userData.getAge();
		this.mediacations = userData.getMedications();
		this.allergies = userData.getAllergies();
	}

}
