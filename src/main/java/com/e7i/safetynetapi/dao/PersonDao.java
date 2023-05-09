package com.e7i.safetynetapi.dao;

import java.util.ArrayList;
import java.util.List;

import com.e7i.safetynetapi.data.UserDataFactory;
import com.e7i.safetynetapi.model.Person;

public class PersonDao {
	
	private static List<Person> personDao = new ArrayList<>();
	
	public PersonDao(List<Person> personDao) {
		PersonDao.setPersonDao(personDao);
	}

	public static List<Person> getPersonDao() {
		return personDao;
	}

	public static void setPersonDao(List<Person> personDao) {
		PersonDao.personDao = personDao;
	}
	
	private static int getIndexPerson(String firstName, String lastName) {

		for(Person p : personDao) {
			if(p.getFirstName().equalsIgnoreCase(firstName) && p.getLastName().equalsIgnoreCase(lastName)) {
				return personDao.indexOf(p);
			}
		} 
		return -1;	
	}
	
	public static Person getPerson(String firstName, String lastName) {

		for(Person p : personDao) {
			if(p.getFirstName().equalsIgnoreCase(firstName) && p.getLastName().equalsIgnoreCase(lastName)) {
				return p;
			}
		} 
		return null;	
	}
	
	public static boolean savePerson(Person person) {
		if(person.getFirstName() == null || person.getLastName() == null) {
			return false;
		} else {
			personDao.add(person);
			UserDataFactory.createUsersModel();
			return true;		
		}
	}
	
	public static boolean editPerson(Person person) {
		int index = PersonDao.getIndexPerson(person.getFirstName(), person.getLastName());
		
		if(index >= 0 ) {
			if(person.getAddress() != null) {
				personDao.get(index).setAddress(person.getAddress());				
			}
			if(person.getCity() != null) {
				personDao.get(index).setCity(person.getCity());				
			}
			if(person.getEmail() != null) {
				personDao.get(index).setEmail(person.getEmail());				
			}
			if(!(person.getPhoneNumber() == null)) {
				personDao.get(index).setPhoneNumber(person.getPhoneNumber());				
			}
			if(person.getPostalCode() != null) {
				personDao.get(index).setPostalCode(person.getPostalCode());				
			}
			UserDataFactory.createUsersModel();
			return true;
		} else System.out.println("Person doesn't exist");
		return false;
	}
	
	public static boolean deletePerson(String firstName, String lastName) {
		int index = PersonDao.getIndexPerson(firstName, lastName);
			
		if(index >= 0 ) {
			personDao.remove(index);
			UserDataFactory.createUsersModel();
			return true;
		} else System.out.println("Person doesn't exist");
		return false;
	}
}
