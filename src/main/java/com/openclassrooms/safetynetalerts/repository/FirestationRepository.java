package com.openclassrooms.safetynetalerts.repository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Repository;

import com.openclassrooms.safetynetalerts.model.Firestation;

@Repository
public class FirestationRepository extends JsonDataRepository {

	private final static Logger logger = LogManager.getLogger("FirestationRepository");
	
	FirestationRepository() throws FileNotFoundException, IOException, ParseException {
		super();
	}

	public void deleteById(String address) {
		logger.debug("FirestationRepository - deleteById: address="+address);
		this.getAllData().getListOfFirestations()
			.removeIf(firestation -> firestation.getAddress().equals(address));
	}

	public Firestation save(Firestation firestation) {
		logger.debug("FirestationRepository - save: firestation="+firestation.toString());
		this.getAllData().getListOfFirestations()
			.add(firestation);
		return firestation;
	}

	public List<String> getAdressesByNumber(String stationNumber) {
		logger.debug("FirestationRepository - save: stationNumber="+stationNumber);
		List<String> addresses = new ArrayList<String>();
		this.getAllData().getListOfFirestations().forEach(firestation -> {
			if(firestation.getStation().equals(stationNumber)) {
				addresses.add(firestation.getAddress());
			}
			});
		return addresses;
	}

	public String getNumberByAdress(String address) {
		logger.debug("FirestationRepository - save: getNumberByAdress="+address);
		Firestation searched = this.getAllData().getListOfFirestations().stream()
				.filter(firestation -> firestation.getAddress().equals(address)).findFirst().orElse(null);
		if(searched!=null) return searched.getStation();
		return null;
	}
}
