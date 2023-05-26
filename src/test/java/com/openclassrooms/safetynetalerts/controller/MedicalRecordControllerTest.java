package com.openclassrooms.safetynetalerts.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
class MedicalRecordControllerTest {

	@Autowired
	private MockMvc mockMvc;
	   
	@Test
	public void testPostMedicalRecord() throws Exception {
		mockMvc.perform(post("/medicalRecord")
				.contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstName\":\"Alfred\", \"lastName\":\"Nobel\", \"birthdate\":\"10/21/1833\", \"medications\":[], \"allergies\":[] }"))
			.andExpect(status().isOk());
	}

	@Test
	public void testDeleteMedicalRecord() throws Exception {
		mockMvc.perform(delete("/medicalRecord/Alfred/Nobel")
				.param("firstName", "Alfred")
				.param("lastName", "Nobel"))
			.andExpect(status().isOk());
	}

	@Test
	public void testUpdateMedicalRecord() throws Exception {
		mockMvc.perform(put("/medicalRecord/Alfred/Nobel")
				.param("firstName", "Alfred")
				.param("lastName", "Nobel")
				.contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstName\":\"Alfred\", \"lastName\":\"Nobel\",\"birthdate\":\"10/21/1833\", \"medications\":[], \"allergies\":[]}"))
			.andExpect(status().isOk());

		mockMvc.perform(put("/medicalRecord/Alfred/Nobel")
				.param("firstName", "Alfred")
				.param("lastName", "Nobel")
				.contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstName\":\"Alfred\", \"lastName\":\"Notnobel\",\"birthdate\":\"10/21/1833\", \"medications\":[], \"allergies\":[]}"))
			.andExpect(status().isOk());

		mockMvc.perform(put("/medicalRecord/Alfred/Nobel")
				.param("firstName", "Alfred")
				.param("lastName", "Nobel")
				.contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstName\":\"Notalfred\", \"lastName\":\"Nobel\",\"birthdate\":\"10/21/1833\", \"medications\":[], \"allergies\":[]}"))
			.andExpect(status().isOk());
}

}
