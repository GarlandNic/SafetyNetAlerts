package com.openclassrooms.sefetynetalerts.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.sefetynetalerts.model.MedicalRecord;
import com.openclassrooms.sefetynetalerts.model.PersonIdentity;

@Repository
public class MedicalRecordRepository extends JsonDataRepository {
	
	private MedicalRecord medicalRecord;

	public void deleteById(PersonIdentity personId) {
		// TODO Auto-generated method stub
		
	}

	public MedicalRecord save(MedicalRecord medicalRecord2) {
		// TODO Auto-generated method stub
		return null;
	}

}
