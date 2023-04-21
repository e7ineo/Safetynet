package com.e7i.safetynetapi.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SafetyNetList {
	
	List <Person> persons;
	@JsonProperty("medicalrecords")
	List <MedicalRecord> medicalRecords;
	List <Firestation> firestations;
		
	@Override
	public String toString() {
		
		for(Person p : persons) {
		System.out.println(p.toString()); 
		}
		
		for(Firestation f: firestations) {
		System.out.println(f.toString());
		}
		
		for(MedicalRecord mr : medicalRecords) {
		System.out.println(mr.toString());
		}
		
		return"File saved into 3 Lists";
	}
}
