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

import com.e7i.safetynetapi.model.Person;


@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonDaoTest {
	
	@Mock
	Person personMock;
	
	@BeforeAll
	static void initTest() {
		List<Person> listTest = new ArrayList<>();
		Person personTest;
		personTest = new Person();
		personTest.setFirstName("Derrick");
		personTest.setLastName("Rose");
		personTest.setAddress("1002 Wicker Park Lane");
		personTest.setPostalCode("60622");
		personTest.setEmail("DRose@chi.town");
		personTest.setPhoneNumber("123-456-789");
		personTest.setCity("Chicago");
		listTest.add(personTest);
		PersonDao.setPersonDao(listTest);
	}
	@BeforeEach
	void beforeEach() {
	}

	@Test
	@Order(0)
	void testEditPersonTrue() {
		when(personMock.getFirstName()).thenReturn("Derrick");
		when(personMock.getLastName()).thenReturn("Rose");
		when(personMock.getCity()).thenReturn("New York");
		when(personMock.getAddress()).thenReturn("2584 25a 5th Avenue");
		when(personMock.getPostalCode()).thenReturn("10001");
		PersonDao.editPerson(personMock);
		assertThat(PersonDao.getPersonDao().get(0).getCity()).isEqualTo("New York");
		assertThat(PersonDao.getPersonDao().get(0).getPhoneNumber()).isEqualTo("123-456-789");
	}
	
	@Test
	@Order(2)
	void testEditPersonFalse() {
		when(personMock.getFirstName()).thenReturn(null);
		boolean test = PersonDao.editPerson(personMock);
		assertThat(test).isFalse();
	}	
	
	@Test
	@Order(3)
	void testDeletePersonFalse() {
		when(personMock.getFirstName()).thenReturn(null);
		boolean test = PersonDao.deletePerson(personMock.getFirstName(), personMock.getLastName());
		assertThat(test).isFalse();	
	}
	
	@Test
	@Order(4)
	void testDeletePersonTrue() {
		when(personMock.getFirstName()).thenReturn("DerriCk");
		when(personMock.getLastName()).thenReturn("RoSe");
		boolean test = PersonDao.deletePerson(personMock.getFirstName(), personMock.getLastName());
		assertThat(test).isTrue();
		assertThat(PersonDao.getPersonDao()).isEmpty();
	}
	
	@Test 
	@Order(5)
	void testSavePersonTrue() {
		when(personMock.getFirstName()).thenReturn("Derrick");
		when(personMock.getLastName()).thenReturn("Rose");
		boolean test = PersonDao.savePerson(personMock);
		assertThat(test).isTrue();
	}
	
	@Test
	@Order(6)
	void testSavePersonFalse() {
		when(personMock.getFirstName()).thenReturn(null);
		boolean test = PersonDao.savePerson(personMock);
		assertThat(test).isFalse();		
	}
}


