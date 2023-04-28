package com.e7i.safetynetapi.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import com.e7i.safetynetapi.dao.PersonDao;

@WebMvcTest(PersonController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@Order(1)
	void testGetPersonsAll() throws Exception {
		mockMvc.perform(get("/Persons"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(23)))
		.andExpect(jsonPath("$.[2].firstName", containsString("Tenley")));
	}
	
	@Test
	void testPersonAdd() throws Exception {
		String mockPersonJson = "{ \"firstName\":\"Derrick\","
				+ " \"lastName\":\"Cadigan\", \"address\":\"951 LoneTree Rd\","
				+ " \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-7458\","
				+ " \"email\":\"gramps@email.com\" }";
		int preAddSize = PersonDao.getPersonDao().size();
		mockMvc.perform(post("/person")
		.contentType(APPLICATION_JSON)				
		.content(mockPersonJson)
		.accept(APPLICATION_JSON))
		.andExpect(status().isCreated());
		assertThat(PersonDao.getPersonDao().size()).isEqualTo(preAddSize + 1);
		assertThat(PersonDao.getPersonDao().get(preAddSize).getFirstName()).isEqualTo("Derrick");
	}
	
	@Test
	void testPersonEdit() throws Exception {
		String mockPersonJson = "{ \"firstName\":\"Shawna\","
				+ " \"lastName\":\"Stelzer\", \"address\":\"951 LoneTree Rd\","
				+ " \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-7458\","
				+ " \"email\":\"gramps@email.com\" }";
		mockMvc.perform(put("/person")
				.contentType(APPLICATION_JSON)				
				.content(mockPersonJson)
				.accept(APPLICATION_JSON))
		.andExpect(status().isOk());
		assertThat(PersonDao.getPersonDao().get(19).getFirstName()).isEqualTo("Shawna");
		assertThat(PersonDao.getPersonDao().get(19).getAddress()).isEqualTo("951 LoneTree Rd");
		assertThat(PersonDao.getPersonDao().get(19).getEmail()).isEqualTo("gramps@email.com");
	}
	
	@Test
	void testPersonDelete() throws Exception {
		int sizePreDelete = PersonDao.getPersonDao().size();
		mockMvc.perform(delete("/person/Eric/Cadigan")).andExpect(status().isOk());
		assertThat(PersonDao.getPersonDao().size()).isEqualTo(sizePreDelete - 1);	
	}	
}
