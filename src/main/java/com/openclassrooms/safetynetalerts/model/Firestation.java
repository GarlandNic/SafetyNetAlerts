package com.openclassrooms.safetynetalerts.model;

import org.json.simple.JSONObject;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "firestations")
public class Firestation {

	@Id
	private String address;
	
	private String station;
	
//	@OneToMany @JoinColumn(name="address")
//	private Person person;

	public Firestation(String address, String station) {
		this.address = address;
		this.station = station;
	}
	
	public Firestation(JSONObject jsonFirestation) {
		this.address = (String) jsonFirestation.get("address");
		this.station = (String) jsonFirestation.get("station");
	}

}
