package com.openclassrooms.safetynetalerts.repository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Repository;

import com.openclassrooms.safetynetalerts.model.Person;
import com.openclassrooms.safetynetalerts.model.PersonIdentity;

@Repository
public class PersonRepository extends JsonDataRepository {
	
	private final static Logger logger = LogManager.getLogger("PersonRepository");

	PersonRepository() throws FileNotFoundException, IOException, ParseException {
		super();
	}

	public void deleteById(PersonIdentity personId) {
		logger.debug("PersonRepository - deleteById: personId="+personId);
		this.getAllData().getListOfPersons()
		.removeIf(person -> person.getFirstName().equals(personId.getFirstName()) 
			&& person.getLastName().equals(personId.getLastName()));
	}

	public Person save(Person person) {
		logger.debug("PersonRepository - save: person="+person);
		this.getAllData().getListOfPersons()
			.add(person);
		return person;
	}

	public List<Person> getPersonsByAdresses(List<String> addresses) {
		logger.debug("PersonRepository - getPersonsByAdresses: addresses="+addresses);
		List<Person> listOfPersons = new ArrayList<Person>();
		this.getAllData().getListOfPersons().forEach(person -> {
			if( addresses.contains(person.getAddress()) ) {
				listOfPersons.add(person);
			}
			});
		return listOfPersons;
	}

	public List<Person> getPersonsByAdress(String address) {
		logger.debug("PersonRepository - getPersonsByAdress: address="+address);
		List<Person> listOfPersons = new ArrayList<Person>();
		this.getAllData().getListOfPersons().forEach(person -> {
			if( address.equals(person.getAddress()) ) {
				listOfPersons.add(person);
			}
			});
		return listOfPersons;
	}

	public Person getPersonByName(String firstName, String lastName) {
		logger.debug("PersonRepository - getPersonByName: "+firstName+", "+lastName);
		return this.getAllData().getListOfPersons().stream()
				.filter(person -> (person.getFirstName().equals(firstName) && person.getLastName().equals(lastName)))
				.findFirst().orElse(null);
	}

	public List<Person> getPersonByCity(String city) {
		logger.debug("PersonRepository - getPersonByCity: city="+city);
		List<Person> listOfPersons = new ArrayList<Person>();
		this.getAllData().getListOfPersons().forEach(person -> {
			if( city.equals(person.getCity()) ) {
				listOfPersons.add(person);
			}
			});
		return listOfPersons;
	}

}
