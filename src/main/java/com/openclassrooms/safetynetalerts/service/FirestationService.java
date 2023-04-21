package com.openclassrooms.safetynetalerts.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.safetynetalerts.model.Firestation;
import com.openclassrooms.safetynetalerts.repository.FirestationRepository;

import lombok.Data;

@Data
@Service
public class FirestationService {
	
	private final static Logger logger = LogManager.getLogger("FirestationService");

	@Autowired
	private FirestationRepository firestationRepository;
	
	//post
	public Firestation saveFirestation(Firestation firestation) {
		logger.info("service - saveFirestation");
		Firestation savedFirestation = firestationRepository.save(firestation);
		return savedFirestation;
	}
	
	//delete
	public void deleteFirestation(String address) {
		logger.info("service - deleteFirestation");
		firestationRepository.deleteById(address);
	}
	
	//put
	public Firestation replaceFirestation(String address, String newStation) {
		logger.info("service - replaceFirestation");
		deleteFirestation(address);
		Firestation savedFirestation = new Firestation(address, newStation);
		return saveFirestation(savedFirestation);
	}
}
