package com.openclassrooms.sefetynetalerts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.sefetynetalerts.model.MedicalRecord;
import com.openclassrooms.sefetynetalerts.model.PersonIdentity;
import com.openclassrooms.sefetynetalerts.service.MedicalRecordService;

@RestController
public class MedicalRecordController {
	
	@Autowired
	private MedicalRecordService medicalRecordService;
	
	// POST
	@PostMapping("/medicalRecord")
	public MedicalRecord postMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
		return medicalRecordService.saveMedicalRecord(medicalRecord);
	}
	
	// DELETE
	@DeleteMapping("/medicalRecord/{firstName}/{lastName}")
	public void deleteMedicalRecord(@PathVariable("firstName") final String firstName, @PathVariable("lastName") final String lastName) {
		PersonIdentity personId = new PersonIdentity(firstName, lastName);
		medicalRecordService.deleteMedicalRecord(personId);
	}
	
	// PUT
	@PutMapping("/medicalRecord/{firstName}/{lastName}")
	public MedicalRecord updateMedicalRecord(@PathVariable("firstName") final String firstName, @PathVariable("lastName") final String lastName, @RequestBody MedicalRecord medicalRecord) {
		if(!medicalRecord.getFirstName().equals(firstName) || !medicalRecord.getLastName().equals(lastName)) {
			// medicalRecord not found ! (log)
			return null;
		} else {
			PersonIdentity personId = new PersonIdentity(firstName, lastName);
			return medicalRecordService.replaceMedicalRecord(personId, medicalRecord);
		}
	}

}
