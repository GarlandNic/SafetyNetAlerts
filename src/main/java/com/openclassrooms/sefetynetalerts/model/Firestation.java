package com.openclassrooms.sefetynetalerts.model;

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

	public Firestation(String address, String station) {
		this.address = address;
		this.station = station;
	}

}
