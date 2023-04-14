package com.openclassrooms.safetynetalerts.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.safetynetalerts.dto.Child;
import com.openclassrooms.safetynetalerts.dto.Children;
import com.openclassrooms.safetynetalerts.dto.HouseAndResidents;
import com.openclassrooms.safetynetalerts.dto.PeopleForFirestation;
import com.openclassrooms.safetynetalerts.dto.PeopleInAddress;
import com.openclassrooms.safetynetalerts.dto.PersonDetails;
import com.openclassrooms.safetynetalerts.dto.PersonForFirestation;
import com.openclassrooms.safetynetalerts.dto.PersonalInformation;
import com.openclassrooms.safetynetalerts.model.MedicalRecord;
import com.openclassrooms.safetynetalerts.model.Person;
import com.openclassrooms.safetynetalerts.model.PersonIdentity;
import com.openclassrooms.safetynetalerts.repository.FirestationRepository;
import com.openclassrooms.safetynetalerts.repository.MedicalRecordRepository;
import com.openclassrooms.safetynetalerts.repository.PersonRepository;

import lombok.Data;

@Data
@Service
public class PersonService {

	private final int MAJORITE = 18;
	
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
	// 		cette url doit retourner une liste des personnes couverts par la caserne de pompiers correspondante. 
	// 		Donc si le num de la station est =1 elle doit renvoyer les hab couverts par la station 1. 
	// 		la liste doit inclure les informations spécifiques suivantes : prénom, nom, address, numéro de tel. 
	// 		de plus elle doit fournir un décompte du nombre d'adultes et du nombre d'enfants (18 ans ou moins)
	public PeopleForFirestation getAllPeopleForFirestation(String stationNumber) {
		
		List<String> addresses = firestationRepository.getAdressesByNumber(stationNumber);

		List<Person> listOfPersons = personRepository.getPersonsByAdresses(addresses);
		
		PeopleForFirestation peopleFF = new PeopleForFirestation();
		listOfPersons.forEach(person -> {
			peopleFF.getListOfPeople().add(new PersonForFirestation(person));
			if(isChild(person)) peopleFF.incrementChildrenNumber();
			else peopleFF.incrementAdultsNumber();
		});
		
		return peopleFF;
	}
	
	//	http://localhost:8080/childAlert?address=<address>
	//		Cette url doit retourner une liste d'enfants (tout individu âgé de 18 ans ou moins) habitant à cette adresse.
	//		La liste doit comprendre le prénom et le nom de famille de chaque enfant, son âge et une liste des autres
	//		membres du foyer. S'il n'y a pas d'enfant, cette url peut renvoyer une chaîne vide.
	public Children getAllChildrenForAddress(String address) {
		List<Person> listOfResidents = personRepository.getPersonsByAdress(address);
		List<Child> listOfChildren = new ArrayList<Child>();
		List<PersonIdentity> listOfOtherResident = new ArrayList<PersonIdentity>();
		
		listOfResidents.forEach(person -> {
			if(isChild(person)) {
				listOfChildren.add(new Child(person.getFirstName(), person.getLastName(), getAge(person)));
			} else {
				listOfOtherResident.add(new PersonIdentity(person.getFirstName(), person.getLastName()));
			}
		});
		
		if(listOfChildren.size()==0) return null;
		
		return (new Children(listOfChildren, listOfOtherResident));
	}
	
	private int getAge(Person person) {
		return medicalRecordRepository.getMedicalRecord(person).getAge();
	}

	private boolean isChild(Person person) {
		return (getAge(person) <= MAJORITE);
	}

	//	http://localhost:8080/phoneAlert?firestation=<firestation_number>
	//		Cette url doit retourner une liste des numéros de téléphone des résidents desservis par la caserne de
	//		pompiers. Nous l'utiliserons pour envoyer des messages texte d'urgence à des foyers spécifiques.
	public List<String> getAllPhonesForFirestation(String stationNumber) {
		
		List<String> addresses = firestationRepository.getAdressesByNumber(stationNumber);

		List<Person> listOfPersons = personRepository.getPersonsByAdresses(addresses);
		
		List<String> listOfPhones = new ArrayList<String>();
		
		listOfPersons.forEach(person -> listOfPhones.add(person.getPhone()));

		return listOfPhones;
	}

	//	http://localhost:8080/fire?address=<address>
	//		Cette url doit retourner la liste des habitants vivant à l’adresse donnée ainsi que le numéro de la caserne
	//		de pompiers la desservant. La liste doit inclure le nom, le numéro de téléphone, l'âge et les antécédents
	//		médicaux (médicaments, posologie et allergies) de chaque personne.
	public PeopleInAddress getAllPeopleInAddress(String address) {

		List<Person> listOfPersons = personRepository.getPersonsByAdress(address);

		String number = firestationRepository.getNumberByAdress(address);

		List<PersonDetails> people = new ArrayList<PersonDetails>();
		
		listOfPersons.forEach(person -> {
			people.add(new PersonDetails(person.getFirstName(), person.getLastName(), person.getPhone(), 
					getAge(person), medicalRecordRepository.getMedicalRecord(person).getMedications(), 
					medicalRecordRepository.getMedicalRecord(person).getAllergies()));
		});

		return (new PeopleInAddress(people, number));
	}

	//	http://localhost:8080/flood/stations?stations=<a list of station_numbers>
	//		Cette url doit retourner une liste de tous les foyers desservis par la caserne. Cette liste doit regrouper les
	//		personnes par adresse. Elle doit aussi inclure le nom, le numéro de téléphone et l'âge des habitants, et
	//		faire figurer leurs antécédents médicaux (médicaments, posologie et allergies) à côté de chaque nom.
	public List<HouseAndResidents> getAllHousesAndResidentsForFirestations(List<String> listOfStationNumbers) {
		
		List<HouseAndResidents> listOfHouses = new ArrayList<HouseAndResidents>();
		
		listOfStationNumbers.forEach(number -> {
			List<String> addresses = firestationRepository.getAdressesByNumber(number);
			addresses.forEach(address -> {
				List<PersonDetails> peopleDetails = new ArrayList<PersonDetails>();
				List<Person> listOfPersons = personRepository.getPersonsByAdress(address);
				listOfPersons.forEach(person -> {
					peopleDetails.add(new PersonDetails(person.getFirstName(), person.getLastName(), person.getPhone(), 
							getAge(person), medicalRecordRepository.getMedicalRecord(person).getMedications(), 
							medicalRecordRepository.getMedicalRecord(person).getAllergies()));
				});
				
				listOfHouses.add(new HouseAndResidents(address, peopleDetails));
			});
		});
		
		return listOfHouses;
	}

	//	http://localhost:8080/personInfo?firstName=<firstName>&lastName=<lastName>
	//		Cette url doit retourner le nom, l'adresse, l'âge, l'adresse mail et les antécédents médicaux (médicaments,
	//		posologie, allergies) de chaque habitant. Si plusieurs personnes portent le même nom, elles doivent
	//		toutes apparaître.
	public PersonalInformation getPersonalInformation(String firstName, String lastName) {
		
		Person person = personRepository.getPersonByName(firstName, lastName);
		MedicalRecord medic = medicalRecordRepository.getMedicalRecord(person);

		return new PersonalInformation(firstName, lastName, person.getAddress(), getAge(person), medic.getMedications(), medic.getAllergies());
	}

	//	http://localhost:8080/communityEmail?city=<city>
	//		Cette url doit retourner les adresses mail de tous les habitants de la ville
	public List<String> getAllEmailForCity(String city) {
		
		List<String> listOfEmail = new ArrayList<String>();
		
		List<Person> people = personRepository.getPersonByCity(city);
		
		people.forEach(person -> listOfEmail.add(person.getEmail()));

		return listOfEmail;
	}

}
