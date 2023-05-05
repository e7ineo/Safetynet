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

import com.e7i.safetynetapi.dao.FirestationDao;
import com.e7i.safetynetapi.model.Firestation;

@RestController
public class FirestationController {
		
	@GetMapping("/Firestations")
	public ResponseEntity<List<Firestation>> getFirestations() {
		return new ResponseEntity<List<Firestation>>(FirestationDao.getFirestationDao(), HttpStatus.OK);
	}
	
	@PostMapping("/firestation")
	public ResponseEntity<Firestation> addFirestation(@RequestBody Firestation firestation) {
		boolean testAdd = FirestationDao.addFirestation(firestation);
		if(testAdd) {
			return new ResponseEntity<Firestation>(firestation, HttpStatus.CREATED);
		} else
			return new ResponseEntity<Firestation>(firestation, HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/firestation")
	public ResponseEntity<Firestation> editFirestation(@RequestBody Firestation firestation) {
		boolean testEdit = FirestationDao.editFirestation(firestation);
		if(testEdit) {
			return new ResponseEntity<Firestation>(firestation, HttpStatus.OK);
		} else
			return new ResponseEntity<Firestation>(firestation, HttpStatus.BAD_REQUEST);
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
			return ResponseEntity.badRequest().body("The station number doesn't exist :\n" + station);
	}
}
