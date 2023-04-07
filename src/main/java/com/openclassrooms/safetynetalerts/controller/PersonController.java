package com.openclassrooms.safetynetalerts.controller;

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
import com.openclassrooms.safetynetalerts.model.Person;
import com.openclassrooms.safetynetalerts.model.PersonIdentity;
import com.openclassrooms.safetynetalerts.service.PersonService;

@RestController
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	// POST
	@PostMapping("/person")
	public Person postPerson(@RequestBody Person person) {
		return personService.savePerson(person);
	}
	
	// DELETE
	@DeleteMapping("/person/{firstName}/{lastName}")
	public void deletePerson(@PathVariable("firstName") final String firstName, @PathVariable("lastName") final String lastName) {
		PersonIdentity personId = new PersonIdentity(firstName, lastName);
		personService.deletePerson(personId);
	}
	
	// PUT
	@PutMapping("/person/{firstName}/{lastName}")
	public Person updatePerson(@PathVariable("firstName") final String firstName, @PathVariable("lastName") final String lastName, @RequestBody Person person) {
		if(!person.getFirstName().equals(firstName) || !person.getLastName().equals(lastName)) {
			// person not found ! (log)
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
		return personService.getAllPeopleForFirestation(stationNumber);
	}

}
