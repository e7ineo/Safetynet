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

import com.e7i.safetynetapi.model.Firestation;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FirestationDaoTest {
	@Mock
	Firestation firestationMock;
	
	@BeforeAll
	static void initTest() {
		List<Firestation> listTest = new ArrayList<>();
		Firestation firestationTest;
		firestationTest = new Firestation();
		firestationTest.setAddress("Gran Via 33");
		firestationTest.setStationNumber(5);
		listTest.add(firestationTest);
		FirestationDao.setFirestationDao(listTest);
	}
	@BeforeEach
	void beforeEach() {
	}

	@Test
	@Order(1)
	void testEditFirestationTrue() {
		when(firestationMock.getAddress()).thenReturn("Gran Via 33");
		when(firestationMock.getStationNumber()).thenReturn(1);
		FirestationDao.editFirestation(firestationMock);		
		assertThat(FirestationDao.getFirestationDao().get(0).getAddress()).isEqualTo("Gran Via 33");
		assertThat(FirestationDao.getFirestationDao().get(0).getStationNumber()).isEqualTo(1);
	}
	
	@Test
	@Order(2)
	void testEditFirestationFalse() {
		when(firestationMock.getAddress()).thenReturn(null);
		boolean test = FirestationDao.editFirestation(firestationMock);
		assertThat(test).isFalse();
	}	
	
	@Test
	@Order(3)
	void testDeleteFirestationByAddressFalse() {
		when(firestationMock.getAddress()).thenReturn(null);
		boolean test = FirestationDao.deleteFirestationByAddress(firestationMock.getAddress());
		assertThat(test).isFalse();	
	}
	
	@Test
	@Order(4)
	void testDeleteFirestationByStationNumberTrue() {
		when(firestationMock.getStationNumber()).thenReturn(1);
		boolean test = FirestationDao.deleteFirestationByStation(firestationMock.getStationNumber());
		assertThat(test).isTrue();
		assertThat(FirestationDao.getFirestationDao()).isEmpty();
	}
	
	@Test 
	@Order(5)
	void testAddFirestationTrue() {
		when(firestationMock.getAddress()).thenReturn("Diagonal 123");
		when(firestationMock.getStationNumber()).thenReturn(1);
		boolean test = FirestationDao.addFirestation(firestationMock);
		assertThat(test).isTrue();
	}
	
	@Test
	@Order(6)
	void testDeleteFirestationByAddressTrue() {
		when(firestationMock.getAddress()).thenReturn("Diagonal 123");
		boolean test = FirestationDao.deleteFirestationByAddress(firestationMock.getAddress());
		assertThat(test).isTrue();
		assertThat(FirestationDao.getFirestationDao()).isEmpty();
	}
}
