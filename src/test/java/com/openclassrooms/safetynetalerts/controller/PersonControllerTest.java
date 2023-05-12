package com.openclassrooms.safetynetalerts.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
class PersonControllerTest {

	@Autowired
	private MockMvc mockMvc;
	   
	@Test
	public void testPostPerson() throws Exception {
		mockMvc.perform(post("/person")
				.contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstName\":\"Alfred\", \"lastName\":\"Nobel\", \"address\":\"ici\", \"city\":\"La-bas\", \"zip\":\"012345\", \"phone\":\"06-36-66-66-66\", \"email\":\"alf@email.com\" }"))
			.andExpect(status().isOk());
	}

	@Test
	public void testDeletePerson() throws Exception {
		mockMvc.perform(delete("/person/Alfred/Nobel")
				.param("firstName", "Alfred")
				.param("lastName", "Nobel"))
			.andExpect(status().isOk());
	}

	@Test
	public void testUpdatePerson() throws Exception {
		mockMvc.perform(put("/person/Alfred/Nobel")
				.param("firstName", "Alfred")
				.param("lastName", "Nobel")
				.contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstName\":\"Alfred\", \"lastName\":\"Nobel\", \"address\":\"ici\", \"city\":\"La-bas\", \"zip\":\"012345\", \"phone\":\"06-36-66-66-66\", \"email\":\"alf@email.com\" }"))
			.andExpect(status().isOk());
	}

	@Test
	public void testGetAllPeopleForFirestation() throws Exception {
		mockMvc.perform(get("/firestation")
				.param("stationNumber", "1"))
			.andExpect(status().isOk());
	}

	@Test
	public void testGetAllChildForAddress() throws Exception {
		mockMvc.perform(get("/childAlert")
				.param("address", "1509 Culver St"))
			.andExpect(status().isOk());
	}
	
	@Test
	public void testGetAllPhonesForFirestation() throws Exception {
		mockMvc.perform(get("/phoneAlert")
				.param("firestation", "1"))
			.andExpect(status().isOk());
	}
	
	@Test
	public void testGetAllPeopleInAddress() throws Exception {
		mockMvc.perform(get("/fire")
				.param("address", "1509 Culver St"))
			.andExpect(status().isOk());
	}
	
	@Test
	public void testGetAllHousesAndResidentsForFirestations() throws Exception {
		mockMvc.perform(get("/flood/stations")
				.param("stations", "1")
				.param("stations", "2"))
			.andExpect(status().isOk());
	}
	
	@Test
	public void testGetPersonalInformation() throws Exception {
		mockMvc.perform(get("/personInfo")
				.param("firstName", "John")
				.param("lastName", "Boyd"))
			.andExpect(status().isOk());
	}
	
	@Test
	public void testGetAllEmailForCity() throws Exception {
		mockMvc.perform(get("/communityEmail")
				.param("city", "Culver"))
			.andExpect(status().isOk());
	}
	
}
