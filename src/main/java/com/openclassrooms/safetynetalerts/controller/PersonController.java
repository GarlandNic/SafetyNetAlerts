package com.openclassrooms.safetynetalerts.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.safetynetalerts.dto.PeopleForFirestation;
import com.openclassrooms.safetynetalerts.dto.PeopleInAddress;
import com.openclassrooms.safetynetalerts.dto.PersonalInformation;
import com.openclassrooms.safetynetalerts.model.Person;
import com.openclassrooms.safetynetalerts.model.PersonIdentity;
import com.openclassrooms.safetynetalerts.dto.Child;
import com.openclassrooms.safetynetalerts.dto.Children;
import com.openclassrooms.safetynetalerts.dto.HouseAndResidents;
import com.openclassrooms.safetynetalerts.service.PersonService;

@RestController
public class PersonController {
	
	private final static Logger logger = LogManager.getLogger("PersonController");
	
	@Autowired
	private PersonService personService;
	
	// POST
	@PostMapping("/person")
	public Person postPerson(@RequestBody Person person) {
		logger.info("controller - postPerson");
		return personService.savePerson(person);
	}
	
	// DELETE
	@DeleteMapping("/person/{firstName}/{lastName}")
	public void deletePerson(@PathVariable("firstName") final String firstName, @PathVariable("lastName") final String lastName) {
		logger.info("controller - deletePerson");
		PersonIdentity personId = new PersonIdentity(firstName, lastName);
		personService.deletePerson(personId);
	}
	
	// PUT
	@PutMapping("/person/{firstName}/{lastName}")
	public Person updatePerson(@PathVariable("firstName") final String firstName, @PathVariable("lastName") final String lastName, @RequestBody Person person) {
		logger.info("controller - updatePerson");
		if(!person.getFirstName().equals(firstName) || !person.getLastName().equals(lastName)) {
			logger.warn("controller - updatePerson : not the same person !");
			return null;
		} else {
			PersonIdentity personId = new PersonIdentity(firstName, lastName);
			return personService.replacePerson(personId, person);
		}
	}
	
	// firestation?stationNumber=<**>
	// cette url doit retourner une liste des personnes couverts par la caserne de pompiers correspondante. 
	// Donc si le num de la station est =1 elle doit renvoyer les hab couverts par la station 1. 
	// la liste doit inclure les informations spécifiques suivantes : prénom, nom, address, numéro de tel. 
	// de plus elle doit fournir un décompte du nombre d'adultes et du nombre d'enfants (18 ans ou mois)
	@GetMapping("/firestation")
	public PeopleForFirestation getAllPeopleForFirestation(@RequestParam("stationNumber") final String stationNumber) {
		logger.info("controller - getAllPeopleForFirestation");
		return personService.getAllPeopleForFirestation(stationNumber);
	}
	
	//	http://localhost:8080/childAlert?address=<address>
	//		Cette url doit retourner une liste d'enfants (tout individu âgé de 18 ans ou moins) habitant à cette adresse.
	//		La liste doit comprendre le prénom et le nom de famille de chaque enfant, son âge et une liste des autres
	//		membres du foyer. S'il n'y a pas d'enfant, cette url peut renvoyer une chaîne vide.
	@GetMapping("/childAlert")
	public Children getAllChildForAddress(@RequestParam("address") final String address) {
		logger.info("controller - getAllChildForAddress");
		return personService.getAllChildrenForAddress(address);
	}
	
	//	http://localhost:8080/phoneAlert?firestation=<firestation_number>
	//		Cette url doit retourner une liste des numéros de téléphone des résidents desservis par la caserne de
	//		pompiers. Nous l'utiliserons pour envoyer des messages texte d'urgence à des foyers spécifiques.
	@GetMapping("/phoneAlert")
	public List<String> getAllPhonesForFirestation(@RequestParam("firestation") final String firestation) {
		logger.info("controller - getAllPhonesForFirestation");
		return personService.getAllPhonesForFirestation(firestation);
	}
	
	//	http://localhost:8080/fire?address=<address>
	//		Cette url doit retourner la liste des habitants vivant à l’adresse donnée ainsi que le numéro de la caserne
	//		de pompiers la desservant. La liste doit inclure le nom, le numéro de téléphone, l'âge et les antécédents
	//		médicaux (médicaments, posologie et allergies) de chaque personne.
	@GetMapping("/fire")
	public PeopleInAddress getAllPeopleInAddress(@RequestParam("address") final String address) {
		logger.info("controller - getAllPeopleInAddress");
		return personService.getAllPeopleInAddress(address);
	}

	//	http://localhost:8080/flood/stations?stations=<a list of station_numbers>
	//		Cette url doit retourner une liste de tous les foyers desservis par la caserne. Cette liste doit regrouper les
	//		personnes par adresse. Elle doit aussi inclure le nom, le numéro de téléphone et l'âge des habitants, et
	//		faire figurer leurs antécédents médicaux (médicaments, posologie et allergies) à côté de chaque nom.
	@GetMapping("/flood/stations")
	public List<HouseAndResidents> getAllHousesAndResidentsForFirestations(@RequestParam("stations") final List<String> listOfStationNumbers) {
		logger.info("controller - getAllHousesAndResidentsForFirestations");
		return personService.getAllHousesAndResidentsForFirestations(listOfStationNumbers);
	}

	//	http://localhost:8080/personInfo?firstName=<firstName>&lastName=<lastName>
	//		Cette url doit retourner le nom, l'adresse, l'âge, l'adresse mail et les antécédents médicaux (médicaments,
	//		posologie, allergies) de chaque habitant. Si plusieurs personnes portent le même nom, elles doivent
	//		toutes apparaître.
	@GetMapping("/personInfo")
	public PersonalInformation getPersonalInformation(@RequestParam("firstName") final String firstName, @RequestParam("lastName") final String lastName) {
		logger.info("controller - getPersonalInformation");
		return personService.getPersonalInformation(firstName, lastName);
	}

	//	http://localhost:8080/communityEmail?city=<city>
	//		Cette url doit retourner les adresses mail de tous les habitants de la ville
	@GetMapping("/communityEmail")
	public List<String> getAllEmailForCity(@RequestParam("city") final String city) {
		logger.info("controller - getAllEmailForCity");
		return personService.getAllEmailForCity(city);
	}

}
