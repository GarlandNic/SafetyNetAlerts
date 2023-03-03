package com.openclassrooms.sefetynetalerts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.sefetynetalerts.model.MedicalRecord;
import com.openclassrooms.sefetynetalerts.model.PersonIdentity;
import com.openclassrooms.sefetynetalerts.repository.MedicalRecordRepository;

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
