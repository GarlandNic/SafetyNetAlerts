package com.openclassrooms.safetynetalerts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.safetynetalerts.model.Person;
import com.openclassrooms.safetynetalerts.model.PersonIdentity;
import com.openclassrooms.safetynetalerts.repository.PersonRepository;

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
