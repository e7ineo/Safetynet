package com.e7i.safetynetapi.model;

import java.util.Arrays;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicalRecord {

	String firstName;
	String lastName;
	String birthdate;
	String[] medications;
	String[] allergies;
	
	@Override
	public String toString() {
		return "FirstName : " + firstName +
				"\nLastName : " + lastName +
				"\nBirthday : " + birthdate +
				"\nMedication : " + Arrays.toString(medications)+
				"\nAllergies : " + Arrays.toString(allergies);
	}
}
