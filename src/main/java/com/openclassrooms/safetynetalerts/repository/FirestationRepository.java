package com.openclassrooms.safetynetalerts.repository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Repository;

import com.openclassrooms.safetynetalerts.model.Firestation;

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

	public List<String> getAdressesByNumber(String stationNumber) {
		List<String> addresses = new ArrayList<String>();
		this.getAllData().getListOfFirestations().forEach(firestation -> {
			if(firestation.getStation().equals(stationNumber)) {
				addresses.add(firestation.getAddress());
			}
			});
		return addresses;
	}
}
