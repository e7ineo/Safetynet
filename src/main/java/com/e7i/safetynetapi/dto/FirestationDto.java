package com.e7i.safetynetapi.dto;

import com.e7i.safetynetapi.data.UserData;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FirestationDto implements Dto {
	
	private String firstName;
	private String lastName;
	private boolean adult;
	private String address;
	private String phoneNumber;
	private int firestationNumber;
	
	public FirestationDto(UserData userData) {
		this.firstName = userData.getFirstName();
		this.lastName = userData.getLastName();
		this.adult = userData.isAdult();
		this.address = userData.getAddress();
		this.phoneNumber = userData.getPhoneNumber();
		this.firestationNumber = userData.getFirestationNumber();
	}
}
