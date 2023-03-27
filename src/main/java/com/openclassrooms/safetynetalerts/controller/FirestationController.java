package com.openclassrooms.safetynetalerts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.safetynetalerts.model.Firestation;
import com.openclassrooms.safetynetalerts.service.FirestationService;

@RestController
public class FirestationController {
	
	@Autowired
	private FirestationService firestationService;
	
	// POST
	@PostMapping("/firestation")
	public Firestation postFirestation(@RequestBody Firestation firestation) {
		return firestationService.saveFirestation(firestation);
	}
	
	// DELETE
	@DeleteMapping("/firestation/{address}")
	public void deleteFirestation(@PathVariable("address") final String address) {
		firestationService.deleteFirestation(address);
	}
	
	// PUT
	@PutMapping("/firestation/{address}")
	public Firestation updateFirestation(@PathVariable("address") final String address, @RequestBody Firestation firestation) {
		if(!firestation.getAddress().equals(address)) {
			// firestation not found ! (log)
			return null;
		} else {
			return firestationService.replaceFirestation(address, firestation.getStation());
		}
	}
	
}
