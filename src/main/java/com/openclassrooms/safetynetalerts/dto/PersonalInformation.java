package com.openclassrooms.safetynetalerts.dto;

import java.util.List;

import lombok.Data;

@Data
public class PersonalInformation {
	
	public PersonalInformation(String firstName, String lastName, String address, int age, List<String> medications,
			List<String> allergies) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.age = age;
		this.medications = medications;
		this.allergies = allergies;
	}

	private String firstName;
	
	private String lastName;
	
	private String address;
	
	private int age;
	
	private List<String> medications;
	
	private List<String> allergies;

}
