package com.e7i.safetynetapi.dto;

import com.e7i.safetynetapi.data.UserData;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChildAlertDto implements Dto{
	String firstName;
	String lastName;
	int age;
	
	public ChildAlertDto (UserData userData) {
		this.firstName = userData.getFirstName();
		this.lastName = userData.getLastName();
		this.age = userData.getAge();
	}
}
