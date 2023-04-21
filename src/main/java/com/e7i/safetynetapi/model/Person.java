package com.e7i.safetynetapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person {

	String firstName;
	String lastName;
	String address;
	String city;
	@JsonProperty("zip")
	String postalCode;
	@JsonProperty("phone")
	String phoneNumber;
	String email;
	
	@Override
	public String toString(){
		return 	"FirstName : " + firstName +
				"\nLastName : " + lastName +
				"\nAddress : " + address + 
				"\nZip " + postalCode +
				"\nCity " + city +
				"\nPhone : " + phoneNumber +
				"\nemail : " + email;
	}
}
