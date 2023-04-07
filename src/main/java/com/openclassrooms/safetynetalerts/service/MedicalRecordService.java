package com.openclassrooms.safetynetalerts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.safetynetalerts.model.MedicalRecord;
import com.openclassrooms.safetynetalerts.model.Person;
import com.openclassrooms.safetynetalerts.model.PersonIdentity;
import com.openclassrooms.safetynetalerts.repository.MedicalRecordRepository;

import lombok.Data;

@Data
@Service
public class MedicalRecordService {

	@Autowired
	private MedicalRecordRepository medicalRecordRepository;
	
	//post
	public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord) {
		MedicalRecord savedMedicalRecord = medicalRecordRepository.save(medicalRecord);
		return savedMedicalRecord;
	}
	
	//delete
	public void deleteMedicalRecord(PersonIdentity personId) {
		medicalRecordRepository.deleteById(personId);
	}
	
	//put
	public MedicalRecord replaceMedicalRecord(PersonIdentity personId, MedicalRecord medicalRecord) {
		deleteMedicalRecord(personId);
		return saveMedicalRecord(medicalRecord);
	}

}
