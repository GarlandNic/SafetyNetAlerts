package com.openclassrooms.safetynetalerts.repository;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Repository;

import com.openclassrooms.safetynetalerts.model.MedicalRecord;
import com.openclassrooms.safetynetalerts.model.Person;
import com.openclassrooms.safetynetalerts.model.PersonIdentity;

@Repository
public class MedicalRecordRepository extends JsonDataRepository {
	
	private final static Logger logger = LogManager.getLogger("MedicalRecordRepository");
	
	MedicalRecordRepository() throws FileNotFoundException, IOException, ParseException {
		super();
	}

	public void deleteById(PersonIdentity personId) {
		logger.debug("MedicalRecordRepository - deleteById: personId="+personId);
		this.getAllData().getListOfMedicalRecords()
			.removeIf(medicalRecord -> medicalRecord.getFirstName().equals(personId.getFirstName()) 
				&& medicalRecord.getLastName().equals(personId.getLastName()));
	}

	public MedicalRecord save(MedicalRecord medicalRecord) {
		logger.debug("MedicalRecordRepository - save: medicalRecord="+medicalRecord);
		this.getAllData().getListOfMedicalRecords()
			.add(medicalRecord);
		return medicalRecord;
	}

	public MedicalRecord getMedicalRecord(Person person) {
		logger.debug("MedicalRecordRepository - getMedicalRecord: person="+person);
		return this.getAllData().getListOfMedicalRecords().stream()
			.filter(medicalRecord -> ( medicalRecord.getFirstName().equals(person.getFirstName()) && 
							medicalRecord.getLastName().equals(person.getLastName()) )).findFirst().orElse(null);			
	}

}
