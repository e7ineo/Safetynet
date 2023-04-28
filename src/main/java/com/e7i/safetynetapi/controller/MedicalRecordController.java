package com.e7i.safetynetapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.e7i.safetynetapi.dao.MedicalRecordDao;
import com.e7i.safetynetapi.model.MedicalRecord;

@RestController
public class MedicalRecordController {
	
	@GetMapping("/MedicalRecords")
	public ResponseEntity<List<MedicalRecord>> getMedicalRecords() {
		return new ResponseEntity<List<MedicalRecord>>(MedicalRecordDao.getMedicalRecordDao(), HttpStatus.OK);
	}
	
	@PostMapping("/medicalRecord")
	public ResponseEntity<MedicalRecord> addMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
		boolean testAdd = MedicalRecordDao.addMedicalRecord(medicalRecord);
		if(testAdd) {
			return new ResponseEntity<MedicalRecord>(medicalRecord, HttpStatus.OK);			
		} else 
			return new ResponseEntity<MedicalRecord>(medicalRecord, HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/medicalRecord")
	public ResponseEntity<MedicalRecord> editMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
		boolean testEdit =MedicalRecordDao.editMedicalRecord(medicalRecord);
		System.out.println("Edit Result = " + testEdit);
		if(testEdit) {
			return new ResponseEntity<MedicalRecord>(medicalRecord, HttpStatus.OK);
		} else 
			return new ResponseEntity<MedicalRecord>(medicalRecord, HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/medicalRecord/{firstName}/{lastName}")
	public ResponseEntity<MedicalRecord> deleteMedicalRecord(@PathVariable String firstName,@PathVariable String lastName) {
		MedicalRecord medicalRecord = MedicalRecordDao.getMedicalRecord(firstName, lastName);
		boolean testDelete = MedicalRecordDao.deleteMedicalRecord(firstName, lastName);
		if(testDelete) {
			return new ResponseEntity<MedicalRecord>(medicalRecord, HttpStatus.OK);
		} else
			return new ResponseEntity<MedicalRecord>(medicalRecord, HttpStatus.BAD_REQUEST);
	}	
}
