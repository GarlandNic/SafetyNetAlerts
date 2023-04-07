package com.openclassrooms.safetynetalerts.dto;

import java.util.List;

import com.openclassrooms.safetynetalerts.model.PersonIdentity;

import lombok.Data;

@Data
public class Children {
	
	List<Child> listOfChildren;
	
	List<PersonIdentity> listOfOtherResidents;
	
	public Children(List<Child> listOfChildren, List<PersonIdentity> listOfOtherResidents) {
		this.listOfChildren = listOfChildren;
		this.listOfOtherResidents = listOfOtherResidents;
	}

}
