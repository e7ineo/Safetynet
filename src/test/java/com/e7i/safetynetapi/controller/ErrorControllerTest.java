package com.e7i.safetynetapi.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ErrorController.class)
public class ErrorControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testError() throws Exception {
		mockMvc.perform(get("/error")).andExpect(status().isBadRequest());
	}

}
