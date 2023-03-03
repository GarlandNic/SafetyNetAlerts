package com.openclassrooms.sefetynetalerts.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.sefetynetalerts.model.MedicalRecord;
import com.openclassrooms.sefetynetalerts.model.PersonIdentity;

@Repository
public interface MedicalRecordRepository extends CrudRepository<MedicalRecord, PersonIdentity> {

}
