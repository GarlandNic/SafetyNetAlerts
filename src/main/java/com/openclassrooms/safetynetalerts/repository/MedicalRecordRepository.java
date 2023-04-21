package com.openclassrooms.safetynetalerts.repository;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Repository;

import com.openclassrooms.safetynetalerts.model.MedicalRecord;
import com.openclassrooms.safetynetalerts.model.Person;
import com.openclassrooms.safetynetalerts.model.PersonIdentity;

@Repository
public class MedicalRecordRepository extends JsonDataRepository {
	
	MedicalRecordRepository() throws FileNotFoundException, IOException, ParseException {
		super();
	}

	public void deleteById(PersonIdentity personId) {
		this.getAllData().getListOfMedicalRecords()
			.removeIf(medicalRecord -> medicalRecord.getFirstName().equals(personId.getFirstName()) 
				&& medicalRecord.getLastName().equals(personId.getLastName()));
	}

	public MedicalRecord save(MedicalRecord medicalRecord) {
		this.getAllData().getListOfMedicalRecords()
			.add(medicalRecord);
		return medicalRecord;
	}

	public MedicalRecord getMedicalRecord(Person person) {
		return this.getAllData().getListOfMedicalRecords().stream()
			.filter(medicalRecord -> ( medicalRecord.getFirstName().equals(person.getFirstName()) && 
							medicalRecord.getLastName().equals(person.getLastName()) )).findFirst().orElse(null);			
	}

}
