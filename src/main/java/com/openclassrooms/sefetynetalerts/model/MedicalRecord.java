package com.openclassrooms.sefetynetalerts.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "medicalrecords")
@IdClass(PersonIdentity.class)
public class MedicalRecord {

	@Id
	private String firstName;
	
	@Id
	private String lastName;
	
	private Date birthdate;
	
	private List<String> medications;
	
	private List<String> allergies;
	
	@OneToOne @MapsId
	private Person person;
	
	public MedicalRecord(String firstName, String lastName, Date birthdate, List<String> medications, List<String> allergies) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.medications = medications;
		this.allergies = allergies;
	}

	public MedicalRecord(JSONObject medicalRecord) {
		this.firstName = (String) medicalRecord.get("firstName");
		this.lastName = (String) medicalRecord.get("lastName");
		this.birthdate = (Date) medicalRecord.get("birthdate");
		
		this.medications = new ArrayList<String>();
		JSONArray listOfMedications = (JSONArray) medicalRecord.get("medications");
		listOfMedications.forEach(medication -> this.medications.add(medication.toString()));

		this.allergies = new ArrayList<String>();
		JSONArray listOfAllergies = (JSONArray) medicalRecord.get("allergies");
		listOfAllergies.forEach(allergie -> this.allergies.add(allergie.toString()));
	}

}
