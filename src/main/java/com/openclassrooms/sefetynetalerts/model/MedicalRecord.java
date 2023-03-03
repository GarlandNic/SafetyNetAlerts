package com.openclassrooms.sefetynetalerts.model;

import java.util.List;

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

}
