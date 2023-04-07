package com.openclassrooms.safetynetalerts.dto;

import com.openclassrooms.safetynetalerts.model.Person;

import lombok.Data;

@Data
public class PersonForFirestation {
	
	private String firstName;
	
	private String lastName;
	
	private String address;
	
	private String phone;
	
	public PersonForFirestation(Person person) {
		this.firstName = person.getFirstName();
		this.lastName = person.getLastName();
		this.address = person.getAddress();
		this.phone = person.getPhone();
	}

}
