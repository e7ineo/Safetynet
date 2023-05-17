package com.e7i.safetynetapi.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.e7i.safetynetapi.model.MedicalRecord;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MedicalRecordDaoTest {

	@Mock
	MedicalRecord medicalRecordMock;
	
	@BeforeAll
	static void initTest() {
		List<MedicalRecord> listTest = new ArrayList<>();
		MedicalRecord medicalRecordTest;
		medicalRecordTest = new MedicalRecord();
		String[] medications = {"PainKillers"};
		String[] allergies = {"Pollen","Lactose"};
		medicalRecordTest.setFirstName("Derrick");
		medicalRecordTest.setLastName("Rose");
		medicalRecordTest.setBirthdate("15/10/1988");
		medicalRecordTest.setMedications(medications);
		medicalRecordTest.setAllergies(allergies);
		listTest.add(medicalRecordTest);
		MedicalRecordDao.setMedicalRecordDao(listTest);
	}
	@BeforeEach
	void beforeEach() {
	}

	@Test
	@Order(1)
	void testEditMedicalRecordTrue() {
		String[] medications = {"L-Thyroxine"};
		String[] allergies = {"None"};
		when(medicalRecordMock.getFirstName()).thenReturn("Derrick");
		when(medicalRecordMock.getLastName()).thenReturn("Rose");
		when(medicalRecordMock.getBirthdate()).thenReturn("08/04/1988");
		when(medicalRecordMock.getMedications()).thenReturn(medications);
		when(medicalRecordMock.getAllergies()).thenReturn(allergies);
		MedicalRecordDao.editMedicalRecord(medicalRecordMock);		
		assertThat(MedicalRecordDao.getMedicalRecordDao().get(0).getBirthdate()).isEqualTo("08/04/1988");
		assertThat(MedicalRecordDao.getMedicalRecordDao().get(0).getAllergies()).isEqualTo(allergies);
	}
	
	@Test
	@Order(2)
	void testEditMedicalRecordFalse() {
		when(medicalRecordMock.getFirstName()).thenReturn(null);
		boolean test = MedicalRecordDao.editMedicalRecord(medicalRecordMock);
		assertThat(test).isFalse();
	}	
	
	@Test
	@Order(3)
	void testDeleteMedicalReocrdFalse() {
		when(medicalRecordMock.getFirstName()).thenReturn(null);
		boolean test = MedicalRecordDao.deleteMedicalRecord(medicalRecordMock.getFirstName(), medicalRecordMock.getLastName());
		assertThat(test).isFalse();	
	}
	
	@Test
	@Order(4)
	void testDeleteMedicalRecordTrue() {
		when(medicalRecordMock.getFirstName()).thenReturn("DerriCk");
		when(medicalRecordMock.getLastName()).thenReturn("RoSe");
		boolean test = MedicalRecordDao.deleteMedicalRecord(medicalRecordMock.getFirstName(), medicalRecordMock.getLastName());
		assertThat(test).isTrue();
		assertThat(MedicalRecordDao.getMedicalRecordDao()).isEmpty();
	}
	
	@Test 
	@Order(5)
	void testAddMedicalRecordTrue() {
		when(medicalRecordMock.getFirstName()).thenReturn("Derrick");
		when(medicalRecordMock.getLastName()).thenReturn("Rose");
		when(medicalRecordMock.getBirthdate()).thenReturn("08/04/1988");
		boolean test = MedicalRecordDao.addMedicalRecord(medicalRecordMock);
		assertThat(test).isTrue();
	}
	
	@Test
	@Order(6)
	void testAddMedicalRecordFalse() {
		when(medicalRecordMock.getFirstName()).thenReturn(null);
		boolean test = MedicalRecordDao.addMedicalRecord(medicalRecordMock);
		assertThat(test).isFalse();		
	}
}
