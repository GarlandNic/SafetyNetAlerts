package com.openclassrooms.safetynetalerts.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class FirestationControllerTest {

	@Autowired
	private MockMvc mockMvc;
	   
	@Test
	public void testPostFirestation() throws Exception {
		mockMvc.perform(post("/firestation")
				.contentType(MediaType.APPLICATION_JSON)
                .content("{\"address\":\"ici\", \"station\":\"1\"}"))
			.andExpect(status().isOk());
	}

	@Test
	public void testDeleteFirestation() throws Exception {
		mockMvc.perform(delete("/firestation/ici")
				.param("address", "ici"))
			.andExpect(status().isOk());
	}

	@Test
	public void testUpdateFirestation() throws Exception {
		mockMvc.perform(put("/firestation/ici")
				.param("address", "ici")
				.contentType(MediaType.APPLICATION_JSON)
                .content("{\"address\":\"ici\", \"station\":\"1\"}"))
			.andExpect(status().isOk());
	}

}
