package com.openclassrooms.safetynetalerts.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.safetynetalerts.model.MedicalRecord;
import com.openclassrooms.safetynetalerts.model.PersonIdentity;
import com.openclassrooms.safetynetalerts.repository.MedicalRecordRepository;

import lombok.Data;

@Data
@Service
public class MedicalRecordService {
	
	private final static Logger logger = LogManager.getLogger("MedicalRecordService");

	@Autowired
	private MedicalRecordRepository medicalRecordRepository;
	
	//post
	public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord) {
		logger.debug("service - saveMedicalRecord");
		MedicalRecord savedMedicalRecord = medicalRecordRepository.save(medicalRecord);
		return savedMedicalRecord;
	}
	
	//delete
	public void deleteMedicalRecord(PersonIdentity personId) {
		logger.debug("service - deleteMedicalRecord");
		medicalRecordRepository.deleteById(personId);
	}
	
	//put
	public MedicalRecord replaceMedicalRecord(PersonIdentity personId, MedicalRecord medicalRecord) {
		logger.debug("service - replaceMedicalRecord");
		deleteMedicalRecord(personId);
		return saveMedicalRecord(medicalRecord);
	}

}
