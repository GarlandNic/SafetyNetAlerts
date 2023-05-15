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
		logger.info("post new Firestation");
		logger.debug("controller - postFirestation: address="+firestation.getAddress());
		return firestationService.saveFirestation(firestation);
	}
	
	// DELETE
	@DeleteMapping("/firestation/{address}")
	public void deleteFirestation(@PathVariable("address") final String address) {
		logger.info("delete Firestation: "+address);
		logger.debug("controller - deleteFirestation: address="+address);
		firestationService.deleteFirestation(address);
	}
	
	// PUT
	@PutMapping("/firestation/{address}")
	public Firestation updateFirestation(@PathVariable("address") final String address, @RequestBody Firestation firestation) {
		logger.info("update Firestation");
		if(!firestation.getAddress().equals(address)) {
			logger.error("controller - updateFirestation : not the same firestation ! Operation aborting.");
			return null;
		} else {
			logger.debug("controller - updateFirestation: address="+address);
			return firestationService.replaceFirestation(address, firestation.getStation());
		}
	}
	
}
