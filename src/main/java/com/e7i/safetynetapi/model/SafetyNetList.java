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
}
