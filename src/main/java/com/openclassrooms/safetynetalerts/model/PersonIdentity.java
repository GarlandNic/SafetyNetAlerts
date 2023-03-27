package com.openclassrooms.safetynetalerts.model;

public class PersonIdentity {
	
	private String firstName;
	
	private String lastName;
	
	public PersonIdentity(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

}
