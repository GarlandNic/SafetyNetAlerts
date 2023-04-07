package com.openclassrooms.safetynetalerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.safetynetalerts.dto.PeopleForFirestation;
import com.openclassrooms.safetynetalerts.dto.PersonForFirestation;
import com.openclassrooms.safetynetalerts.model.Person;
import com.openclassrooms.safetynetalerts.model.PersonIdentity;
import com.openclassrooms.safetynetalerts.repository.FirestationRepository;
import com.openclassrooms.safetynetalerts.repository.MedicalRecordRepository;
import com.openclassrooms.safetynetalerts.repository.PersonRepository;

import lombok.Data;

@Data
@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private FirestationRepository firestationRepository;
	
	@Autowired
	private MedicalRecordRepository medicalRecordRepository;
	
	//post
	public Person savePerson(Person person) {
		Person savedPerson = personRepository.save(person);
		return savedPerson;
	}
	
	//delete
	public void deletePerson(PersonIdentity personId) {
		personRepository.deleteById(personId);
	}
	
	//put
	public Person replacePerson(PersonIdentity personId, Person person) {
		deletePerson(personId);
		return savePerson(person);
	}
	
	// firestation?stationNumber=<**>
	// cette url doit retourner une liste des personnes couverts par la caserne de pompiers correspondante. 
	// Donc si le num de la station est =1 elle doit renvoyer les hab couverts par la station 1. 
	// la liste doit inclure les informations spécifiques suivantes : prénom, nom, address, numéro de tel. 
	// de plus elle doit fournir un décompte du nombre d'adultes et du nombre d'enfants (18 ans ou moins)
	public PeopleForFirestation getAllPeopleForFirestation(String stationNumber) {
		
		List<String> addresses = firestationRepository.getAdressesByNumber(stationNumber);

		List<Person> listOfPersons = personRepository.getPersonsByAdress(addresses);
		
		PeopleForFirestation peopleFF = new PeopleForFirestation();
		listOfPersons.forEach(person -> {
			peopleFF.getListOfPeople().add(new PersonForFirestation(person));
			if(isChild(person)) peopleFF.incrementChildrenNumber();
			else peopleFF.incrementAdultsNumber();
		});
		
		return peopleFF;
	}

	private boolean isChild(Person person) {
		int age = medicalRecordRepository.getMedicalRecord(person).getAge();
		return (age <= 18);
	}

}
