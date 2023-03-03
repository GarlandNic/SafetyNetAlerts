package com.openclassrooms.sefetynetalerts.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "firestations")
public class Firestation {

	private String adress;
	
	@Id
	private String station;
}
