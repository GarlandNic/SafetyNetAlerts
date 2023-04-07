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
//		Cette url doit retourner une liste de tous les foyers desservis par la caserne. Cette liste doit regrouper les
//		personnes par adresse. Elle doit aussi inclure le nom, le numéro de téléphone et l'âge des habitants, et
//		faire figurer leurs antécédents médicaux (médicaments, posologie et allergies) à côté de chaque nom.
