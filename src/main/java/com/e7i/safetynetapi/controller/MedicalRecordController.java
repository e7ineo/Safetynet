package com.e7i.safetynetapi.controller;

import java.util.List;

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
	
	@GetMapping("/MedicalRecordsAll")
	public List<MedicalRecord> getMedicalRecords() {
		return MedicalRecordDao.getMedicalRecordDao();
	}
	
	@PostMapping("/medicalRecordAdd")
	public ResponseEntity<String> addMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
		boolean testPost = MedicalRecordDao.addMedicalRecord(medicalRecord);
		if(!testPost) {
			return ResponseEntity.badRequest().body("Not Saved - Missing Fields in the request -> \n" + medicalRecord);			
		} else 
			return ResponseEntity.ok("MedicalRecord Saved as :\n" + medicalRecord.toString());
	}
	
	@PutMapping("/medicalRecordEdit")
	public ResponseEntity<String> editMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
		boolean testEdit =MedicalRecordDao.editMedicalRecord(medicalRecord);
		System.out.println("Edit Result = " + testEdit);
		if(testEdit) {
			return ResponseEntity.ok().body("Data edited as :\n" + medicalRecord.toString());
		} else 
			return ResponseEntity.badRequest().body("The user doesn't exist or is empty :\n" + medicalRecord.toString());
	}
	
	@DeleteMapping("/MedicalRecords/{firstName}/{lastName}")
	public ResponseEntity<String> deleteMedicalRecord(@PathVariable String firstName,@PathVariable String lastName) {
		boolean testDelete = MedicalRecordDao.deleteMedicalRecord(firstName, lastName);
		if(testDelete) {
			return ResponseEntity.ok().body("User " + firstName + " " + lastName + " deleted");
		} else
			return ResponseEntity.badRequest().body("User " + firstName + " " + lastName + " couldn't be deleted");
	}
	
	
}
