package com.openclassrooms.sefetynetalerts.repository;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Repository;

import com.openclassrooms.sefetynetalerts.model.Person;
import com.openclassrooms.sefetynetalerts.model.PersonIdentity;

@Repository
public class PersonRepository extends JsonDataRepository {

	PersonRepository() throws FileNotFoundException, IOException, ParseException {
		super();
	}

	public void deleteById(PersonIdentity personId) {
		this.getAllData().getListOfPersons()
		.removeIf(person -> person.getFirstName() == personId.getFirstName() 
			&& person.getLastName() == personId.getLastName());
	}

	public Person save(Person person) {
		this.getAllData().getListOfPersons()
			.add(person);
		return person;
	}

}
