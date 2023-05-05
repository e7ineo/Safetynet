package com.e7i.safetynetapi.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.hamcrest.Matchers.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import com.e7i.safetynetapi.dao.MedicalRecordDao;

@WebMvcTest(MedicalRecordController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MedicalRecordControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@Order(1)
	void testGetMedicalRecord() throws Exception {
		mockMvc.perform(get("/MedicalRecords"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(23)))
		.andExpect(jsonPath("$.[0].firstName" , containsString("John")));		
	}
	
	@Test
	void testAddMedicalRecord() throws Exception {
		String mockMedicalRecordJson = "{ \"firstName\":\"Derrick\", \"lastName\":\"Rose\","
				+ "\"birthdate\":\"01/08/1986\", \"medications\":[\"tetracyclaz:650mg\"], "
				+ "\"allergies\":[\"xilliathal\"] }";
		
		int sizePreAdd = MedicalRecordDao.getMedicalRecordDao().size();
		
		mockMvc.perform(post("/medicalRecord")
				.contentType(APPLICATION_JSON)
				.content(mockMedicalRecordJson))
		.andExpect(status().isOk());
		assertThat(MedicalRecordDao.getMedicalRecordDao().size()).isEqualTo(sizePreAdd + 1);
		assertThat(MedicalRecordDao.getMedicalRecordDao().get(sizePreAdd).getLastName()).isEqualTo("Rose");
	}
	
	@Test
	void testEditMedicalRecord() throws Exception {
		String mockMedicalRecordJson = "{ \"firstName\":\"Shawna\", \"lastName\":\"Stelzer\","
				+ "\"birthdate\":\"01/08/1986\", \"medications\":[\"LThyrox\", \"Omeprazol\"], "
				+ "\"allergies\":[\"None\"] }";
		
		mockMvc.perform(put("/medicalRecord")
				.contentType(APPLICATION_JSON)
				.content(mockMedicalRecordJson))
		.andExpect(status().isOk());
		assertThat(MedicalRecordDao.getMedicalRecordDao().get(19).getFirstName()).isEqualTo("Shawna");
		assertThat(MedicalRecordDao.getMedicalRecordDao().get(19).getAllergies()).contains("None");
		assertThat(MedicalRecordDao.getMedicalRecordDao().get(19).getMedications()).contains("LThyrox","Omeprazol");
	}
	
	@Test
	void testDeleteMedicalRecord() throws Exception {
		int sizePreDelete = MedicalRecordDao.getMedicalRecordDao().size();
		
		mockMvc.perform(delete("/medicalRecord/Eric/Cadigan")).andExpect(status().isOk());
		assertThat(MedicalRecordDao.getMedicalRecordDao().size()).isEqualTo(sizePreDelete - 1); 
	}
	
	@Test
	void testDeleteMedicalRecordNotOk() throws Exception {		
		mockMvc.perform(delete("/medicalRecord/Xxxxx/Xxxxx")).andExpect(status().is4xxClientError());
	}
}
