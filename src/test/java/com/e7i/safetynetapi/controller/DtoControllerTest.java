package com.e7i.safetynetapi.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
	void getChildAlertWhereAddressIs() throws Exception {
		mockMvc.perform(get("/childAlert?address=1509 Culver St")).andExpect(status().isOk());
	}
	
	@Test
	void getPhoneAlertWhereFirestationIs() throws Exception {
		mockMvc.perform(get("/phoneAlert?")
				.param("firestation", "1"))
				.andExpect(status().isOk());
	}
	
	@Test
	void getFireWhereAddressIs() throws Exception {
		mockMvc.perform(get("/fire?")
				.param("address", "1509 Culver St"))
				.andExpect(status().isOk());
	}
	
	@Test
	void getFloodWhereStationNumberIs() throws Exception {
		String[] requestArray = {"2","3"};
		mockMvc.perform(get("/flood").param("stationNumbers", requestArray))
				.andExpect(status().isOk());
	}
	
	@Test
	void getPersonInfoWhereNameIs() throws Exception {
		mockMvc.perform(get("/personInfo?firstName=John&lastName=Boyd"))
				.andExpect(status().isOk());
	}
	
	@Test
	void getCommunityEmailsWhereCityIs() throws Exception {
		mockMvc.perform(get("/communityEmail?city=Culver"))
				.andExpect(status().isOk());
	}

}
