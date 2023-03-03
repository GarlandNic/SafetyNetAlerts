package com.openclassrooms.sefetynetalerts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.sefetynetalerts.service.ComplexeRequestService;

@RestController
public class ComplexeRequestController {
	
	@Autowired 
	private ComplexeRequestService complexeRequestService;

	// firestation?stationNumber=<**>
	// cette url doit retourner une liste des personnes couverts par la caserne de pompiers correspondante. 
	// Donc si le num de la station est =1 elle doit renvoyer les hab couverts par la station 1. 
	// la liste doit inclure les informations spécifiques suivantes : prénom, nom, address, numéro de tel. 
	// de plus elle doit fournir un décompte du nombre d'adultes et du nombre d'enfants (18 ans ou mois)
	@GetMapping("/firestation")
	public String getAllPeopleForFirestation(@RequestParam("stationNumber") final String stationNumber) {
		return complexeRequestService.getAllPeopleForFirestation(stationNumber);
	}


}
