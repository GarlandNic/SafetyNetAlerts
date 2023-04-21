package com.openclassrooms.safetynetalerts.dto;

import java.util.List;

import lombok.Data;

@Data
public class HouseAndResidents {
	
	public HouseAndResidents(String address, List<PersonDetails> listOfResidents) {
		super();
		this.address = address;
		this.listOfResidents = listOfResidents;
	}

	private String address;
	
	private List<PersonDetails> listOfResidents;
	
}
