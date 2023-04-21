package com.e7i.safetynetapi.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.e7i.safetynetapi.dao.FirestationDao;
import com.e7i.safetynetapi.model.Firestation;

@RestController
public class FirestationController {
	
	private static final Logger logger = LogManager.getLogger("FirestationController");
	
	@GetMapping("/FirestationsAll")
	public List<Firestation> getFirestations() {
		return FirestationDao.getFirestationDao();
	}
	
	@PostMapping("/firestationAdd")
	public ResponseEntity<String> addFirestation(@RequestBody Firestation firestation) {
		boolean testAdd = FirestationDao.addFirestation(firestation);
		if(testAdd) {
			return ResponseEntity.ok().body("Firestation Saved as :\n" + firestation);
		} else
			return ResponseEntity.badRequest().body("Not Saved - Missing Fields in the request -> " + firestation);
	}
	
	@PutMapping("/firestationEdit")
	public ResponseEntity<String> editFirestation(@RequestBody Firestation firestation) {
		boolean testEdit = FirestationDao.editFirestation(firestation);
		if(testEdit) {
			return ResponseEntity.ok().body("Firestation Edited as :\n" + firestation);
		} else
			return ResponseEntity.badRequest().body("The address doesn't exist or is empty :\n" + firestation);
	}
	
	@DeleteMapping("/firestationByAddress/{address}")
	public ResponseEntity<String> deleteFirestationByAddress(@PathVariable(value="address") String address) {
		boolean testDelete = FirestationDao.deleteFirestationByAddress(address);
		if(testDelete) {
			return ResponseEntity.ok().body("Firestation with address : " + address + " Deleted");
		} else
			return ResponseEntity.badRequest().body("The address doesn't exist or is empty :\n" + address);
	}
	
	@DeleteMapping("/firestationById/{station}")
	public ResponseEntity<String> deleteFirestationByStation(@PathVariable(value="station") int station) {
		boolean testDelete = FirestationDao.deleteFirestationByStation(station);
		if(testDelete) {
			return ResponseEntity.ok().body("Firestations with station number : " + station + " Deleted");
		} else
			return ResponseEntity.badRequest().body("The station number doesn't exist or is empty :\n" + station);
	}
}
