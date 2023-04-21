package com.e7i.safetynetapi.dto;

import com.e7i.safetynetapi.data.UserData;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonInfoDto implements Dto {
	
	private String firstName;
	private String lastName;
	private String address;
	private int age;
	private String email;
	private String[] mediacations;
	private String[] allergies;
	
	public PersonInfoDto(UserData userData) {
		this.firstName = userData.getFirstName();
		this.lastName = userData.getLastName();
		this.address = userData.getAddress();
		this.age = userData.getAge();
		this.email = userData.getEmail();
		this.mediacations = userData.getMedications();
		this.allergies = userData.getAllergies();
	}
}
