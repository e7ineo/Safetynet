package com.e7i.safetynetapi.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import com.e7i.safetynetapi.dao.FirestationDao;

@WebMvcTest(FirestationController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FirestationControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@Order(1)
	void getFirestations() throws Exception {
		mockMvc.perform(get("/Firestations"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.[0].address", containsString("1509 Culver St")));
	}
	
	@Test
	void postAddFirestaion() throws Exception {
		String mockFirestationJson = "{ \"address\":\"Diagonal 123\", \"station\":\"10\" }" ;
		int preSize = FirestationDao.getFirestationDao().size();
		mockMvc.perform(post("/firestation")
				.contentType(APPLICATION_JSON)
				.content(mockFirestationJson)
				.accept(APPLICATION_JSON))
				.andExpect(status().isCreated());
		assertThat(FirestationDao.getFirestationDao().size()).isEqualTo(preSize + 1);
		assertThat(FirestationDao.getFirestationDao().get(FirestationDao.getFirestationDao().size()-1).getStationNumber()).isEqualTo(10);
	}
	
	@Test
	void putFirestation() throws Exception {
		String mockFirestationJson = "{ \"address\":\"1509 Culver St\", \"station\":\"10\" }" ;
		mockMvc.perform(put("/firestation")
				.contentType(APPLICATION_JSON)
				.content(mockFirestationJson)
				.accept(APPLICATION_JSON));
		assertThat(FirestationDao.getFirestationDao().get(0).getStationNumber()).isEqualTo(10);
	}
	
	@Test
	void deleteByAddressFirestation() throws Exception {
		int preSize = FirestationDao.getFirestationDao().size();
		mockMvc.perform(delete("/firestationByAddress/{address}","1509 Culver St")).andExpect(status().isOk());
		assertThat(FirestationDao.getFirestationDao().size()).isEqualTo(preSize - 1);
	}
	
	@Test
	void deleteByIdFirestation() throws Exception {
		int preSize = FirestationDao.getFirestationDao().size();
		mockMvc.perform(delete("/firestationById/{station}", "1")).andExpect(status().isOk());
		assertThat(FirestationDao.getFirestationDao().size()).isEqualTo(preSize - 3);
	}
	
	@Test
	void deleteByIdFirestationNotOk() throws Exception {
	mockMvc.perform(delete("/firestationById/{station}", "1000")).andExpect(status().is4xxClientError());
	}
	
	@Test
	void deleteByAddressFirestationNotOk() throws Exception {
		mockMvc.perform(delete("/firestationByAddress/{address}","xxxxxxxxx")).andExpect(status().is4xxClientError());
	}
	
}
