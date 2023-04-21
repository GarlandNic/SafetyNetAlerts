package com.openclassrooms.safetynetalerts.model;

import lombok.Data;

@Data
public class PersonIdentity {
	
	private String firstName;
	
	private String lastName;
	
	public PersonIdentity(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

}
