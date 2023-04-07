package com.openclassrooms.safetynetalerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.openclassrooms.safetynetalerts.dto.PeopleForFirestation;
import com.openclassrooms.safetynetalerts.model.Person;
import com.openclassrooms.safetynetalerts.repository.FirestationRepository;
import com.openclassrooms.safetynetalerts.repository.MedicalRecordRepository;
import com.openclassrooms.safetynetalerts.repository.PersonRepository;

@Service
public class ComplexeRequestService {
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private FirestationRepository firestationRepository;
	
	@Autowired
	private MedicalRecordRepository medicalRecordRepository;

	// firestation?stationNumber=<**>
	// cette url doit retourner une liste des personnes couverts par la caserne de pompiers correspondante. 
	// Donc si le num de la station est =1 elle doit renvoyer les hab couverts par la station 1. 
	// la liste doit inclure les informations spécifiques suivantes : prénom, nom, address, numéro de tel. 
	// de plus elle doit fournir un décompte du nombre d'adultes et du nombre d'enfants (18 ans ou moins)
	public PeopleForFirestation getAllPeopleForFirestation(String stationNumber) {
		
		String adress = firestationRepository.getAdressByNumber(stationNumber);

		List<Person> listOfPersons = personRepository.getPersonsByAdress(adress);
		
		return new PeopleForFirestation(listOfPersons);
	}

}
