package com.openclassrooms.safetynetalerts.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
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
	
	private String birthdate;
	
	private List<String> medications;
	
	private List<String> allergies;
	
//	@OneToOne @MapsId
//	private Person person;
	
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	
	public MedicalRecord(String firstName, String lastName, String birthdate, List<String> medications, List<String> allergies) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.medications = medications;
		this.allergies = allergies;
	}

	public MedicalRecord(JSONObject medicalRecord) {
		this.firstName = (String) medicalRecord.get("firstName");
		this.lastName = (String) medicalRecord.get("lastName");
		this.birthdate = (String) medicalRecord.get("birthdate");
		
		this.medications = new ArrayList<String>();
		JSONArray listOfMedications = (JSONArray) medicalRecord.get("medications");
		listOfMedications.forEach(medication -> this.medications.add((String) medication));

		this.allergies = new ArrayList<String>();
		JSONArray listOfAllergies = (JSONArray) medicalRecord.get("allergies");
		listOfAllergies.forEach(allergie -> this.allergies.add((String) allergie));
	}

	public int getAge() {
		LocalDate today = LocalDate.now();
		return Period.between(this.getBirthdateDate(), today).getYears();
	}
	
	public LocalDate getBirthdateDate() {
		return LocalDate.parse((String) this.birthdate, formatter);
	}

}
