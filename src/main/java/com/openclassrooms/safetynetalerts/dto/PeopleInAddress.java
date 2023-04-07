package com.openclassrooms.safetynetalerts.dto;

import java.util.List;

import lombok.Data;

@Data
public class PeopleInAddress {
	
	private List<PersonDetails> peopleDetails;
	
	private String firestationNumber;
	
	public PeopleInAddress(List<PersonDetails> peopleDetails, String firestationNumber) {
		this.peopleDetails = peopleDetails;
		this.firestationNumber = firestationNumber;
	}

}
