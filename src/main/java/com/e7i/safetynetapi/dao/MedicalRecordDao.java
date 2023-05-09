package com.e7i.safetynetapi.dao;

import java.util.ArrayList;
import java.util.List;

import com.e7i.safetynetapi.data.UserDataFactory;
import com.e7i.safetynetapi.model.MedicalRecord;

public class MedicalRecordDao {
	
	private static List<MedicalRecord> medicalRecordDao = new ArrayList<>();
	
	public MedicalRecordDao(List<MedicalRecord> medicalRecordDao) {
		MedicalRecordDao.setMedicalRecordDao(medicalRecordDao);;
	}

	public static List<MedicalRecord> getMedicalRecordDao() {
		return medicalRecordDao;
	}

	public static void setMedicalRecordDao(List<MedicalRecord> medicalRecordDao) {
		MedicalRecordDao.medicalRecordDao = medicalRecordDao;
	}
	
	private static int getIndexMedicalRecord(String firstName, String lastName) {

		for(MedicalRecord mr : medicalRecordDao) {
			if(mr.getFirstName().equalsIgnoreCase(firstName) && mr.getLastName().equalsIgnoreCase(lastName)) {
				return medicalRecordDao.indexOf(mr);
			}
		} 
		return -1;
	}
	
	public static MedicalRecord getMedicalRecord(String firstName, String lastName) {

		for(MedicalRecord mr : medicalRecordDao) {
			if(mr.getFirstName().equalsIgnoreCase(firstName) && mr.getLastName().equalsIgnoreCase(lastName)) {
				return mr;
			}
		} 
		return null;	
	}
	
	public static boolean addMedicalRecord(MedicalRecord medicalRecord) {
		if(medicalRecord.getFirstName() == null|| medicalRecord.getLastName() == null) {
			return false; 
		} else 
			medicalRecordDao.add(medicalRecord);
			UserDataFactory.createUsersModel();
		return true;
	}
	
	public static boolean editMedicalRecord(MedicalRecord medicalRecord) {
		int index = MedicalRecordDao.getIndexMedicalRecord(medicalRecord.getFirstName(), medicalRecord.getLastName());
		if(index >= 0) {
			medicalRecordDao.get(index).setAllergies(medicalRecord.getAllergies());
			medicalRecordDao.get(index).setBirthdate(medicalRecord.getBirthdate());
			medicalRecordDao.get(index).setMedications(medicalRecord.getMedications());
			UserDataFactory.createUsersModel();
			return true;
		} else 
			return false;
	}
	
	public static boolean deleteMedicalRecord(String firstName, String lastName) {
		int index = MedicalRecordDao.getIndexMedicalRecord(firstName, lastName);
		if(index >= 0) {
			medicalRecordDao.remove(index);
			UserDataFactory.createUsersModel();
			return true;
		} else 
			return false;
	}
}
