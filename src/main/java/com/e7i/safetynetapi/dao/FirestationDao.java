package com.e7i.safetynetapi.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.e7i.safetynetapi.model.Firestation;

public class FirestationDao {
	
	private static final Logger logger = LogManager.getLogger("FirestationDao");
	
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
		logger.info("ADDRESS IS NOT IN THE DATASOURCE");
		return -1;
	}
	
	public static boolean addFirestation(Firestation firestation) {
		if(firestation.getStationNumber() <= 0 || firestation.getAddress() == null) {
			return false;
		} else firestationDao.add(firestation);
		logger.info("FIRESTATION : " + firestation.getAddress() + " WITH STATION NUMBER : " + firestation.getStationNumber() + " HAS BEEN ADDED");
		return true;
	}
	
	public static boolean editFirestation(Firestation firestation) {
		int index = FirestationDao.getIndexFirestation(firestation.getAddress());
		if(index >= 0) {
			firestationDao.get(index).setStationNumber(firestation.getStationNumber());
			logger.info("FIRESTATION : " + firestation.getAddress() + " HAS BEEN MODIEFIED TO STATION NUMBER : " + firestation.getStationNumber());
			return true;
		} else
			return false;
	}
	
	public static boolean deleteFirestationByAddress(String address) {
		int index = FirestationDao.getIndexFirestation(address);
		if(index >= 0) {
			firestationDao.remove(index);
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
			return true;
		} else
			return false;	
	}
}
