package com.openclassrooms.safetynetalerts.model;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import lombok.Data;

@Data
public class AllData {
	
	private List<Person> listOfPersons;
	
	private List<MedicalRecord> listOfMedicalRecords;
	
	private List<Firestation> listOfFirestations;
	
	public AllData(List<Person> listOfPersons, List<MedicalRecord> listOfMedicalRecords, List<Firestation> listOfFirestations) {
		this.listOfPersons = listOfPersons;
		this.listOfMedicalRecords = listOfMedicalRecords;
		this.listOfFirestations = listOfFirestations;
	}
	
	public AllData(JSONArray listOfPersons, JSONArray listOfMedicalRecords, JSONArray listOfFirestations) {
		this.listOfPersons = new ArrayList<Person>();
		listOfPersons.forEach(person -> this.listOfPersons.add(new Person((JSONObject) person)));

		this.listOfMedicalRecords = new ArrayList<MedicalRecord>();
		listOfMedicalRecords.forEach(medicalRecord -> this.listOfMedicalRecords.add(new MedicalRecord((JSONObject) medicalRecord)));

		this.listOfFirestations = new ArrayList<Firestation>();
		listOfFirestations.forEach(firestation -> this.listOfFirestations.add(new Firestation((JSONObject) firestation)));
	}
	
	public JSONObject asJson() {
		JSONObject result = new JSONObject();

		JSONArray listOfPersons = new JSONArray();
		this.listOfPersons.forEach(person -> listOfPersons.add(person.asJson()));
		result.put("persons", listOfPersons);
		
		JSONArray listOfFirestations = new JSONArray();
		this.listOfFirestations.forEach(firestation -> listOfFirestations.add(firestation.asJson()));
		result.put("firestations", listOfFirestations);
		
		JSONArray listOfMedicalRecords = new JSONArray();
		this.listOfMedicalRecords.forEach(medicalRecord -> listOfMedicalRecords.add(medicalRecord.asJson()));
		result.put("medicalrecords", listOfMedicalRecords);
		
		return result;
	}

}
