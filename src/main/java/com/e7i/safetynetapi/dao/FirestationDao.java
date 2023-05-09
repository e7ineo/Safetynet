package com.e7i.safetynetapi.dao;

import java.util.ArrayList;
import java.util.List;

import com.e7i.safetynetapi.data.UserDataFactory;
import com.e7i.safetynetapi.model.Firestation;

public class FirestationDao {
	
	private static List<Firestation> firestationDao;
	
	public FirestationDao(List<Firestation> firestation) {
		setFirestationDao(firestation);
	}

	public static List<Firestation> getFirestationDao() {
		return firestationDao;
	}

	public static void setFirestationDao(List<Firestation> firestationDao) {
		FirestationDao.firestationDao = firestationDao;
	}
	
	private static int getIndexFirestation(String address) {
		for(Firestation f : firestationDao) {
			if(f.getAddress().equalsIgnoreCase(address)) {
				return firestationDao.indexOf(f);
			}
		}
		return -1;
	}
	
	public static boolean addFirestation(Firestation firestation) {
		if(firestation.getStationNumber() <= 0 || firestation.getAddress() == null) {
			return false;
		} else {
			firestationDao.add(firestation);
			UserDataFactory.createUsersModel();
			return true;
		}
	}
	
	public static boolean editFirestation(Firestation firestation) {
		int index = FirestationDao.getIndexFirestation(firestation.getAddress());
		if(index >= 0) {
			firestationDao.get(index).setStationNumber(firestation.getStationNumber());
			UserDataFactory.createUsersModel();
			return true;
		} else
			return false;
	}
	
	public static boolean deleteFirestationByAddress(String address) {
		int index = FirestationDao.getIndexFirestation(address);
		if(index >= 0) {
			firestationDao.remove(index);
			UserDataFactory.createUsersModel();
			return true;
		} else
			return false;
	}
	
	public static boolean deleteFirestationByStation(int station) {
		List<Firestation> toRemove = new ArrayList<>();
		for(Firestation f : firestationDao) {			
			if(f.getStationNumber() == station) {
				toRemove.add(f);
			}
		}
		if(!toRemove.isEmpty()) {
			firestationDao.removeAll(toRemove);
			UserDataFactory.createUsersModel();
			return true;
		} else
			return false;	
	}
}
