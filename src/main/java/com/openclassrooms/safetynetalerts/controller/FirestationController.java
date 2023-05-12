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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
public class FirestationController {
	
	private final static Logger logger = LogManager.getLogger("FirestationController");
	
	@Autowired
	private FirestationService firestationService;
	
	// POST
	@PostMapping("/firestation")
	public Firestation postFirestation(@RequestBody Firestation firestation) {
		logger.info("controller - postFirestation");
		return firestationService.saveFirestation(firestation);
	}
	
	// DELETE
	@DeleteMapping("/firestation/{address}")
	public void deleteFirestation(@PathVariable("address") final String address) {
		logger.info("controller - deleteFirestation");
		firestationService.deleteFirestation(address);
	}
	
	// PUT
	@PutMapping("/firestation/{address}")
	public Firestation updateFirestation(@PathVariable("address") final String address, @RequestBody Firestation firestation) {
		logger.info("controller - updateFirestation");
		if(!firestation.getAddress().equals(address)) {
			logger.warn("controller - updateFirestation : not the same firestation !");
			return null;
		} else {
			return firestationService.replaceFirestation(address, firestation.getStation());
		}
	}
	
}
