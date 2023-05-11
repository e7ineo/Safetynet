package com.e7i.safetynetapi.data;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import com.e7i.safetynetapi.model.Firestation;
import com.e7i.safetynetapi.model.MedicalRecord;
import com.e7i.safetynetapi.model.Person;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserData {

	private String firstName;
	private String lastName;
	private String birthdate;
	private int age;
	private boolean adult;
	private String address;
	private String postalCode;
	private String city;
	private String phoneNumber;
	private String email;
	private int firestationNumber;
	private String[] medications;
	private String[] allergies;
	
	public UserData (Person person, MedicalRecord medicalRecord, Firestation firestation) {
		this.firstName = person.getFirstName();
		this.lastName = person.getLastName();
		this.birthdate = medicalRecord.getBirthdate();
		this.age = setAge(medicalRecord.getBirthdate());
		this.adult = this.setAdult(this.age);
		this.address = person.getAddress();
		this.postalCode = person.getPostalCode();
		this.city = person.getCity();
		this.phoneNumber = person.getPhoneNumber();
		this.email = person.getEmail();
		this.firestationNumber = firestation.getStationNumber();
		this.medications = medicalRecord.getMedications();
		this.allergies = medicalRecord.getAllergies();
	}
	
	public UserData (Person person, MedicalRecord medicalRecord) {
		this.firstName = person.getFirstName();
		this.lastName = person.getLastName();
		this.birthdate = medicalRecord.getBirthdate();
		this.age = setAge(medicalRecord.getBirthdate());
		this.adult = this.setAdult(this.age);
		this.address = person.getAddress();
		this.postalCode = person.getPostalCode();
		this.city = person.getCity();
		this.phoneNumber = person.getPhoneNumber();
		this.email = person.getEmail();
		this.medications = medicalRecord.getMedications();
		this.allergies = medicalRecord.getAllergies();
	}
		
	public UserData(Person person, Firestation firestation) {
		this.firstName = person.getFirstName();
		this.lastName = person.getLastName();
		this.adult = this.setAdult(this.age);
		this.address = person.getAddress();
		this.postalCode = person.getPostalCode();
		this.city = person.getCity();
		this.phoneNumber = person.getPhoneNumber();
		this.email = person.getEmail();
		this.firestationNumber = firestation.getStationNumber();
		
	}
	
	public UserData(Person person) {
		this.firstName = person.getFirstName();
		this.lastName = person.getLastName();
		this.address = person.getAddress();
		this.postalCode = person.getPostalCode();
		this.city = person.getCity();
		this.phoneNumber = person.getPhoneNumber();
		this.email = person.getEmail();		
	}
	
	public UserData(MedicalRecord medicalRecord) {
		this.firstName = medicalRecord.getFirstName();
		this.lastName = medicalRecord.getLastName();
		this.birthdate = medicalRecord.getBirthdate();
		this.age = setAge(medicalRecord.getBirthdate());
		this.adult = this.setAdult(this.age);
		this.medications = medicalRecord.getMedications();
		this.allergies = medicalRecord.getAllergies();
	}

	public int setAge(String birthDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate born = LocalDate.parse(birthDate, formatter);
		LocalDate today = LocalDate.now();
		return Period.between(born, today).getYears();
	}
		
	public boolean setAdult(int age) {
		if(age > 18) return true;
		else return false;
	}	
}
