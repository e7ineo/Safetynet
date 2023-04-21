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
	
	public List<UserData> createUsersModel(){
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
		return usersData;
	}
}
