package com.e7i.safetynetapi.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(DtoController.class)
public class DtoControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void getFirestationWhereStationNumberIs() throws Exception {
		mockMvc.perform(get("/firestation?stationNumber=1")).andExpect(status().isOk());
	}
	
	@Test
	void getFirestationWhereStationNumberIsIncorrect() throws Exception {
		mockMvc.perform(get("/firestation?stationNumber=10")).andExpect(status().isOk())
		.andExpect(jsonPath("$", aMapWithSize(0)))
		.andExpect(jsonPath("$").isEmpty());
	}
	
	@Test
	void getChildAlertWhereAddressIs() throws Exception {
		mockMvc.perform(get("/childAlert?address=1509 Culver St")).andExpect(status().isOk());
	}
	
	@Test
	void getChildAlertWhereAddressIsIncorrect() throws Exception {
		mockMvc.perform(get("/childAlert?address=200 Impasse St"))
		.andExpect(status().isOk()).andExpect(jsonPath("$", aMapWithSize(0)));
	}
	
	@Test
	void getPhoneAlertWhereFirestationIs() throws Exception {
		mockMvc.perform(get("/phoneAlert?")
				.param("firestation", "1"))
				.andExpect(status().isOk());
	}
	
	@Test
	void getPhoneAlertWhereFirestationIsIncorrect() throws Exception {
		mockMvc.perform(get("/phoneAlert?")
				.param("firestation", "1111"))
				.andExpect(status().isOk()).andExpect(jsonPath("$", aMapWithSize(0)));
	}
	
	@Test
	void getFireWhereAddressIs() throws Exception {
		mockMvc.perform(get("/fire?")
				.param("address", "1509 Culver St"))
				.andExpect(status().isOk());
	}
	
	@Test
	void getFireWhereAddressIsIncorrect() throws Exception {
		mockMvc.perform(get("/fire?")
				.param("address", "1509 Banana St"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", aMapWithSize(0)));
				
	}
	
	@Test
	void getFloodWhereStationNumberIs() throws Exception {
		String[] requestArray = {"2","3"};
		mockMvc.perform(get("/flood").param("stationNumbers", requestArray))
				.andExpect(status().isOk());
	}
	
	@Test
	void getFloodWhereStationNumberIsIncorrect() throws Exception {
		String[] requestArray = {"200","300"};
		mockMvc.perform(get("/flood").param("stationNumbers", requestArray))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", aMapWithSize(1))); //because it contains the default Map
	}
	
	@Test
	void getPersonInfoWhereNameIs() throws Exception {
		mockMvc.perform(get("/personInfo?firstName=John&lastName=Boyd"))
				.andExpect(status().isOk());
	}
	
	@Test
	void getPersonInfoWhereNameIsIncorrect() throws Exception {
		mockMvc.perform(get("/personInfo?firstName=Idonthave&lastName=Aname"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", aMapWithSize(0)));
	}
	
	@Test
	void getCommunityEmailsWhereCityIs() throws Exception {
		mockMvc.perform(get("/communityEmail?city=Culver"))
				.andExpect(status().isOk());
	}
	
	@Test
	void getCommunityEmailsWhereCityIsIncorrect() throws Exception {
		mockMvc.perform(get("/communityEmail?city=CityThatDoesntExist"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", aMapWithSize(0)));
	}

}
