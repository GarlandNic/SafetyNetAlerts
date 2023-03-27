package com.openclassrooms.safetynetalerts.repository;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Repository;

import com.openclassrooms.safetynetalerts.model.MedicalRecord;
import com.openclassrooms.safetynetalerts.model.PersonIdentity;

@Repository
public class MedicalRecordRepository extends JsonDataRepository {
	
	MedicalRecordRepository() throws FileNotFoundException, IOException, ParseException {
		super();
	}

	public void deleteById(PersonIdentity personId) {
		this.getAllData().getListOfMedicalRecords()
			.removeIf(medicalRecord -> medicalRecord.getFirstName() == personId.getFirstName() 
				&& medicalRecord.getLastName() == personId.getLastName());
	}

	public MedicalRecord save(MedicalRecord medicalRecord) {
		this.getAllData().getListOfMedicalRecords()
			.add(medicalRecord);
		return medicalRecord;
	}

}
