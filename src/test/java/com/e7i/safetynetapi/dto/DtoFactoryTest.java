package com.e7i.safetynetapi.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.e7i.safetynetapi.data.UserData;
import com.e7i.safetynetapi.data.UserDataFactory;

@ExtendWith(MockitoExtension.class)
public class DtoFactoryTest {

	@Mock
	UserData userDataMock;
	
	@Mock
	FirestationDto firestationDtoMock;
	
	DtoFactory dtf;
	List<Dto> userDtoTest;

	
	@BeforeAll
	static void initTest() {
		
	}
	
	@BeforeEach
	void setUpEach() {
		dtf = new DtoFactory();
		userDtoTest = new ArrayList<>();
		UserDataFactory.getUsersData().add(userDataMock);		
	}
	
	@Test
	void testCreateFirestationDto() {
		when(userDataMock.getFirestationNumber()).thenReturn(1);
		userDtoTest = dtf.createFirestationDto(1);
		assertThat(userDtoTest).isNotEmpty();
	}
	
	@Test
	void testFailCreateFirestationDto() {
		userDtoTest = dtf.createFirestationDto(-1);
		assertThat(userDtoTest).isEmpty();		
	}
	
	@Test
	void testCreateChildAlert() {
		when(userDataMock.getAddress()).thenReturn("address");
		userDtoTest = dtf.createChildAlertDto("address");
		assertThat(userDtoTest).isNotEmpty();		
	}
	
	@Test
	void testFailCreateChildAlert() {
		userDtoTest = dtf.createChildAlertDto("-1 NoAddress");
		assertThat(userDtoTest).isEmpty();		
	}
	
	@Test
	void testCreatePhoneNumberDto() {
		when(userDataMock.getFirestationNumber()).thenReturn(1);
		userDtoTest = dtf.createPhoneNumberDto(1);
		assertThat(userDtoTest).isNotEmpty();
	}
	
	@Test
	void testFailCreatePhoneNumberDto() {
		userDtoTest = dtf.createPhoneNumberDto(-1);
		assertThat(userDtoTest).isEmpty();
	}
	
	@Test
	void testCreateFireDto() {
		when(userDataMock.getAddress()).thenReturn("address");
		userDtoTest = dtf.createFireDto("address");
		assertThat(userDtoTest).isNotEmpty();	
	}
	
	@Test
	void testCreateFloodDTo() {
		int[] array = {1};
		when(userDataMock.getFirestationNumber()).thenReturn(1);
		userDtoTest = dtf.createFloodDto(array);
		assertThat(userDtoTest).isNotEmpty();
	}
	
	@Test
	void testCreatePersonInfoDto() {
		when(userDataMock.getFirstName()).thenReturn("Derrick");
		when(userDataMock.getLastName()).thenReturn("Rose");
		userDtoTest = dtf.createPersonInfoDto("Derrick", "Rose");
		assertThat(userDtoTest).isNotEmpty();	
	}
	
	@Test
	void testCommunityEmailDto() {
		when(userDataMock.getCity()).thenReturn("Chicago");
		userDtoTest = dtf.createCommunityEmailDto("Chicago");
		assertThat(userDtoTest).isNotEmpty();	
	}	
}
