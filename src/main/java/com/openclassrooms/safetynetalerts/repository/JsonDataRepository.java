package com.openclassrooms.safetynetalerts.repository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Repository;

import com.openclassrooms.safetynetalerts.model.AllData;

@Repository
public class JsonDataRepository {
	
	private AllData allData;
	
	private static final String DATA_PATH = "data.json";
	
	JsonDataRepository() throws FileNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser();
		ClassLoader classLoader = getClass().getClassLoader(); 
		JSONObject jsonObject = (JSONObject) parser.parse(new InputStreamReader(classLoader.getResourceAsStream(DATA_PATH), "UTF-8"));
//		JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(DATA_PATH));
		JSONArray jsonPersons = (JSONArray) jsonObject.get("persons");
		JSONArray jsonFirestations = (JSONArray) jsonObject.get("firestations");
		JSONArray jsonMedicalRecords = (JSONArray) jsonObject.get("medicalrecords");
		this.allData = new AllData(jsonPersons, jsonMedicalRecords, jsonFirestations);
	}

	public AllData getAllData() {
		return allData;
	}

	public void setAllData(AllData allData) {
		this.allData = allData;
	}

/*
public void saveAllData() throws IOException {
		this.allData.asJson().writeJSONString(new FileWriter(DATA_PATH));
	}
*/
}
