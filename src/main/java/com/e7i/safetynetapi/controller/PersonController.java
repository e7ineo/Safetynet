package com.e7i.safetynetapi.controller;

import com.e7i.safetynetapi.dao.PersonDao;
import com.e7i.safetynetapi.model.*;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {
	
	@GetMapping("/PersonsAll")
	public List<Person> getPersons() {				
		return PersonDao.getPersonDao();
	}
	
	@PostMapping("/personAdd")
	public ResponseEntity<String> addPerson(@RequestBody Person person) {
		boolean testSave = PersonDao.savePerson(person);
		if(!testSave) {
			return ResponseEntity.badRequest().body("Not Saved - Missing Fields in the request -> \n" + person);
		} else
			return ResponseEntity.ok().body("Person saved as :\n" + person.toString());
	}
	
	@PutMapping("/personEdit")
	public ResponseEntity<String> editPerson(@RequestBody Person person) {
		boolean testEdit = PersonDao.editPerson(person);
		if(!testEdit) {
			return ResponseEntity.badRequest().body("The user doesn't exist or is empty :\n" + person.toString());
		} else 
			return ResponseEntity.ok().body("Person Edited as :\n" + person.toString());
	}
	
	@DeleteMapping("/Persons/{firstName}/{lastName}")
	public ResponseEntity<String> deleteEntryByName(@PathVariable(value = "firstName") String firstName, @PathVariable(value = "lastName") String lastName) {
		boolean testDelete = PersonDao.deletePerson(firstName,lastName);
		if(testDelete) {
			return ResponseEntity.ok().body("User " + firstName + " " + lastName + " deleted");
		} else
			return ResponseEntity.badRequest().body("User " + firstName + " " + lastName + " couldn't be deleted");
	}
}
