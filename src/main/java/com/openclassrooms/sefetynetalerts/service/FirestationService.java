package com.openclassrooms.sefetynetalerts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.sefetynetalerts.model.Firestation;
import com.openclassrooms.sefetynetalerts.repository.FirestationRepository;

import lombok.Data;

@Data
@Service
public class FirestationService {

	@Autowired
	private FirestationRepository firestationRepository;
	
	//post
	public Firestation saveFirestation(Firestation firestation) {
		Firestation savedFirestation = firestationRepository.save(firestation);
		return savedFirestation;
	}
	
	//delete
	public void deleteFirestation(String address) {
		firestationRepository.deleteById(address);
	}
	
	//put
	public Firestation replaceFirestation(String address, String newStation) {
		deleteFirestation(address);
		Firestation savedFirestation = new Firestation(address, newStation);
		return saveFirestation(savedFirestation);
	}
	
	
}
