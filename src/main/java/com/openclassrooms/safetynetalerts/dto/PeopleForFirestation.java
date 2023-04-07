package com.openclassrooms.safetynetalerts.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.openclassrooms.safetynetalerts.model.Person;
import com.openclassrooms.safetynetalerts.service.MedicalRecordService;

import lombok.Data;

@Data
public class PeopleForFirestation {
	
	List<PersonForFirestation> listOfPeople;
	
	int adultsNumber;
	
	int childrenNumber;
	
	@Autowired
	private MedicalRecordService medicalRecordService;
	
	PeopleForFirestation() {
		this.listOfPeople = new ArrayList<PersonForFirestation>();
		this.adultsNumber = 0;
		this.childrenNumber = 0;
	}
	
	public PeopleForFirestation(List<Person> listOfPerson) {
		this();
		listOfPerson.forEach(person -> {
			this.listOfPeople.add(new PersonForFirestation(person));
			if(medicalRecordService.isChildren(person)) this.childrenNumber++;
			else this.adultsNumber++;
		});
	}

}
