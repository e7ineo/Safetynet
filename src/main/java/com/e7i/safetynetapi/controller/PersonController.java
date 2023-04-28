package com.e7i.safetynetapi.controller;

import com.e7i.safetynetapi.dao.PersonDao;
import com.e7i.safetynetapi.model.*;

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

@RestController
public class PersonController {
	
	@GetMapping("/Persons")
	public ResponseEntity<List<Person>> getPersons() {				
		return new ResponseEntity<List<Person>>(PersonDao.getPersonDao(), HttpStatus.OK);
	}
	
	@PostMapping("/person")
	public ResponseEntity<Person> addPerson(@RequestBody Person person) {
		boolean testAdd = PersonDao.savePerson(person);
		if(testAdd) {
			return new ResponseEntity<Person>(person , HttpStatus.CREATED);
		} else
			return new ResponseEntity<Person>(person , HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/person")
	public ResponseEntity<Person> editPerson(@RequestBody Person person) {
		boolean testEdit = PersonDao.editPerson(person);
		if(testEdit) {
			return new ResponseEntity<Person>(person , HttpStatus.OK);
		} else 
			return new ResponseEntity<Person>(person , HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/person/{firstName}/{lastName}")
	public ResponseEntity<Person> deletePersonByName(@PathVariable(value = "firstName") String firstName, @PathVariable(value = "lastName") String lastName) {
		Person person = PersonDao.getPerson(firstName, lastName);
		boolean testDelete = PersonDao.deletePerson(firstName,lastName);
		if(testDelete) {
			return new ResponseEntity<Person>(person, HttpStatus.OK);
		} else
			return new ResponseEntity<Person>(person, HttpStatus.NOT_FOUND);
	}
}
