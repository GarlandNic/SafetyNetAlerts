package com.openclassrooms.sefetynetalerts.repository;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Repository;

import com.openclassrooms.sefetynetalerts.model.Firestation;

@Repository
public class FirestationRepository extends JsonDataRepository {

	FirestationRepository() throws FileNotFoundException, IOException, ParseException {
		super();
	}

	public void deleteById(String address) {
		this.getAllData().getListOfFirestations()
		.removeIf(firestation -> firestation.getAddress().equals(address));
	}

	public Firestation save(Firestation firestation) {
		this.getAllData().getListOfFirestations()
			.add(firestation);
		return firestation;
	}

}
