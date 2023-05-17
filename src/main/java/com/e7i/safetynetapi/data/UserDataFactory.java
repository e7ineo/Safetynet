package com.e7i.safetynetapi.data;

import java.util.ArrayList;
import java.util.List;

import com.e7i.safetynetapi.dao.FirestationDao;
import com.e7i.safetynetapi.dao.MedicalRecordDao;
import com.e7i.safetynetapi.dao.PersonDao;
import com.e7i.safetynetapi.model.Firestation;
import com.e7i.safetynetapi.model.MedicalRecord;
import com.e7i.safetynetapi.model.Person;

public class UserDataFactory {
	
	private static List<UserData> usersData = new ArrayList<>();
	
	public static List<UserData> getUsersData() {
		return usersData;
	}
	
	public static void createUsersModel(){
		UserData user;
		
		for(Person p : PersonDao.getPersonDao()) {
			for(MedicalRecord mr : MedicalRecordDao.getMedicalRecordDao()) {
				if(p.getFirstName().equalsIgnoreCase(mr.getFirstName()) && p.getLastName().equalsIgnoreCase(mr.getLastName())) {
					for(Firestation f : FirestationDao.getFirestationDao()) {
						if(p.getAddress().equalsIgnoreCase(f.getAddress())) {
							user = new UserData(p, mr, f);
							usersData.add(user);
						}
					}
				}
			}
		}
	}
	
	public static void updateUserDataPerson(Person person) {
		boolean medicalRecordExist = false;
		boolean firestationExist = false;
		MedicalRecord mrRecord = new MedicalRecord();
		Firestation fRecord = new Firestation();
		
		for(MedicalRecord mr : MedicalRecordDao.getMedicalRecordDao()) {
			if(person.getFirstName().equalsIgnoreCase(mr.getFirstName()) && person.getLastName().equalsIgnoreCase(mr.getLastName())) {
				mrRecord = mr;
				medicalRecordExist = true;
			}
		}
		
		for(Firestation f : FirestationDao.getFirestationDao()) {
			if(person.getAddress().equalsIgnoreCase(f.getAddress())) {
				fRecord = f;
				firestationExist = true;
			}
		}
		
		if(medicalRecordExist && firestationExist) {
			UserData userData = new UserData(person, mrRecord, fRecord );
			usersData.add(userData);
		}
		
		if(medicalRecordExist && !firestationExist) {
			UserData userData = new UserData(person, mrRecord);
			usersData.add(userData);
		}
		
		if(!medicalRecordExist && firestationExist) {
			UserData userData = new UserData(person, fRecord );	
			usersData.add(userData);
		}
		
		if(!medicalRecordExist && !firestationExist) {
			UserData userData = new UserData(person);
			usersData.add(userData);
		}		
	}
	
	public static void updateUserDataMedicalRecord(MedicalRecord medicalRecord) {
		boolean personExist = false;
		Person pRecord = new Person();
		
		for(Person p : PersonDao.getPersonDao()) {
			if(medicalRecord.getFirstName().equalsIgnoreCase(p.getFirstName()) && medicalRecord.getLastName().equalsIgnoreCase(p.getLastName())) {
				pRecord = p;
				personExist = true;
			}
		}
		
		if(personExist) {
			UserData userData = new UserData(pRecord, medicalRecord);
			usersData.add(userData);
		}
		
		if(!personExist) {
			UserData userData = new UserData(medicalRecord);
			usersData.add(userData);
		}	
	}

	public static void updateUserDataEditPerson(Person person) {
		
		for(UserData ud : usersData) {
			if(person.getFirstName().equalsIgnoreCase(ud.getFirstName()) && person.getLastName().equalsIgnoreCase(ud.getLastName())) {
				ud.setAddress(person.getAddress());
				ud.setPostalCode(person.getPostalCode());
				ud.setCity(person.getCity());
				ud.setPhoneNumber(person.getPhoneNumber());
				ud.setEmail(person.getEmail());	
				for(Firestation f : FirestationDao.getFirestationDao()) {
					if(person.getAddress().equalsIgnoreCase(f.getAddress())) {
						ud.setFirestationNumber(f.getStationNumber());
					}
				}
			}
		}
		
		for(MedicalRecord mr : MedicalRecordDao.getMedicalRecordDao()) {
			if(person.getFirstName().equalsIgnoreCase(mr.getFirstName()) && person.getLastName().equalsIgnoreCase(mr.getLastName())) {
				mr.setFirstName(person.getFirstName());
				mr.setLastName(person.getLastName());
			}
		}		
	} 
	
	
	public static void updateUserDataEditMedicalRecord(MedicalRecord medicalRecord) {
		
		for(UserData ud : usersData) {
			if(medicalRecord.getFirstName().equalsIgnoreCase(ud.getFirstName()) && medicalRecord.getLastName().equalsIgnoreCase(ud.getLastName())) {
				
				ud.setBirthdate(medicalRecord.getBirthdate());
				ud.setAge(medicalRecord.getBirthdate());
				ud.setAdult(ud.getAge());
				ud.setMedications(medicalRecord.getMedications());
				ud.setAllergies(medicalRecord.getAllergies());
			}
		}
		
		for(Person p : PersonDao.getPersonDao()) {
			if(medicalRecord.getFirstName().equalsIgnoreCase(p.getFirstName()) && medicalRecord.getLastName().equalsIgnoreCase(p.getLastName())) {
				p.setFirstName(medicalRecord.getFirstName());
				p.setLastName(medicalRecord.getLastName());
			}
		}		
	} 
	
	public static void updateUserDataDeletePerson(String firstName, String lastName) {
		int index = -1;		
		for(UserData ud : usersData) {
			if(firstName.equalsIgnoreCase(ud.getFirstName()) && lastName.equalsIgnoreCase(ud.getLastName())) {
				index = usersData.indexOf(ud);
			}
		}
		if(index != -1) {
			usersData.remove(index);			
		}
	} 
	
	public static void updateFirestationEdit(Firestation firestation) {
		for(UserData ud : usersData) {
			if(firestation.getAddress().equalsIgnoreCase(ud.getAddress())){
				ud.setFirestationNumber(firestation.getStationNumber());
			}
		}
	}
}
