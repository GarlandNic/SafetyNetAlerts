package com.openclassrooms.sefetynetalerts.model;

import org.json.simple.JSONObject;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "persons")
@IdClass(PersonIdentity.class)
public class Person {
	
	@Id
	private String firstName;
	
	@Id
	private String lastName;
	
	private String address;
	
	private String city;
	
	private String zip;
	
	private String phone;
	
	private String email;
	
	@OneToOne @MapsId
	private MedicalRecord medicalRecord;
	
	@ManyToOne @JoinColumn(name="address")
	private Firestation firestation;
	
	public Person(String firstName, String lastName, String address, String city, String zip, String phone, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.zip = zip;
		this.phone = phone;
		this.email = email;
	}
	
	public Person(JSONObject person) {
		this.firstName = (String) person.get("firstName");
		this.lastName = (String) person.get("lastName");
		this.address = (String) person.get("address");
		this.city = (String) person.get("city");
		this.zip = (String) person.get("zip");
		this.phone = (String) person.get("phone");
		this.email = (String) person.get("email");
	}
	
}
