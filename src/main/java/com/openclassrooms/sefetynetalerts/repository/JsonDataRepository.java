package com.openclassrooms.sefetynetalerts.repository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Repository;

import com.openclassrooms.sefetynetalerts.model.AllData;

@Repository
public class JsonDataRepository {
	
	private AllData allData;
	
	private static final String DATA_PATH = "../../../resources/data.jon";
	
	JsonDataRepository() throws FileNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(DATA_PATH));
		JSONArray jsonPersons = (JSONArray) jsonObject.get("persons");
		JSONArray jsonFirestations = (JSONArray) jsonObject.get("firestations");
		JSONArray jsonMedicalRecords = (JSONArray) jsonObject.get("medicalrecords");
		this.allData = new AllData(jsonMedicalRecords, jsonMedicalRecords, jsonMedicalRecords);
	}

	public AllData getAllData() {
		return allData;
	}

	public void setAllData(AllData allData) {
		this.allData = allData;
	}



	
}
