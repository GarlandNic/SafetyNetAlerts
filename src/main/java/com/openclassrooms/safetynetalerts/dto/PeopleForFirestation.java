package com.openclassrooms.safetynetalerts.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.openclassrooms.safetynetalerts.model.Person;
import com.openclassrooms.safetynetalerts.service.MedicalRecordService;

import lombok.Data;

@Data
public class PeopleForFirestation {
	
	List<PersonForFirestation> listOfPeople;
	
	int adultsNumber;
	
	int childrenNumber;
	
	public PeopleForFirestation() {
		this.listOfPeople = new ArrayList<PersonForFirestation>();
		this.adultsNumber = 0;
		this.childrenNumber = 0;
	}
	
	public int incrementAdultsNumber() {
		return (this.adultsNumber++);
	}
	
	public int incrementChildrenNumber() {
		return (this.childrenNumber++);
	}
	
}
