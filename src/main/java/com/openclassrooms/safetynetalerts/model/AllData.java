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
		
	public AllData(JSONArray listOfPersons, JSONArray listOfMedicalRecords, JSONArray listOfFirestations) {
		this.listOfPersons = new ArrayList<Person>();
		listOfPersons.forEach(person -> this.listOfPersons.add(new Person((JSONObject) person)));

		this.listOfMedicalRecords = new ArrayList<MedicalRecord>();
		listOfMedicalRecords.forEach(medicalRecord -> this.listOfMedicalRecords.add(new MedicalRecord((JSONObject) medicalRecord)));

		this.listOfFirestations = new ArrayList<Firestation>();
		listOfFirestations.forEach(firestation -> this.listOfFirestations.add(new Firestation((JSONObject) firestation)));
	}

}
