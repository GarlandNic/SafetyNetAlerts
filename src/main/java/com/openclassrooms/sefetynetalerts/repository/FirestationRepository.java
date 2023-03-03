package com.openclassrooms.sefetynetalerts.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.sefetynetalerts.model.Firestation;

@Repository
public interface FirestationRepository extends CrudRepository<Firestation, String> {

}
