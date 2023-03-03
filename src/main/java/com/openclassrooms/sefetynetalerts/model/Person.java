package com.openclassrooms.sefetynetalerts.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
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
	
}
