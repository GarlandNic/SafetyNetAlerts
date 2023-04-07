package com.openclassrooms.safetynetalerts.repository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Repository;

import com.openclassrooms.safetynetalerts.model.Person;
import com.openclassrooms.safetynetalerts.model.PersonIdentity;

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

	public List<Person> getPersonsByAdress(String adress) {
		return this.getAllData().getListOfPersons().stream().filter(person -> person.getAddress().equals(adress)).toList();
	}

}
