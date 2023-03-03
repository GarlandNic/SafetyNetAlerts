package com.openclassrooms.sefetynetalerts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.sefetynetalerts.model.Person;
import com.openclassrooms.sefetynetalerts.model.PersonIdentity;
import com.openclassrooms.sefetynetalerts.repository.PersonRepository;

import lombok.Data;

@Data
@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;
	
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
	
	
}
