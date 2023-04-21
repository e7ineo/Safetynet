package com.e7i.safetynetapi.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

public class DaoFactoryTest {
		
	@Test
	void testCreateDao() {
		DaoFactory df = new DaoFactory();
		boolean test = df.createDao(new File("src/main/resources/dataTest.json"));
		assertThat(PersonDao.getPersonDao().get(0).getCity()).isEqualTo("Culver");
		assertThat(MedicalRecordDao.getMedicalRecordDao().get(0).getFirstName()).isEqualTo("John");
		assertThat(FirestationDao.getFirestationDao().get(0).getStationNumber()).isEqualTo(3);
		assertThat(test).isTrue();
	}
	
	@Test 
	void testCreateDaoFail() {
		DaoFactory df = new DaoFactory();
		boolean test = df.createDao(new File("src/main/resources/idontexist.json"));
		assertThat(test).isFalse();
	}
	
	@AfterAll
	static void clean() {
		PersonDao.getPersonDao().clear();
		MedicalRecordDao.getMedicalRecordDao().clear();
		FirestationDao.getFirestationDao().clear();
	}
}
