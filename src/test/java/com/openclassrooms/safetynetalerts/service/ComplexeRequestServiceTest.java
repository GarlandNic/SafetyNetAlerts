package com.openclassrooms.safetynetalerts.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ComplexeRequestServiceTest {

	@Test
	void testGetAllPeopleForFirestation() {
		// firestation?stationNumber=<**>
		// cette url doit retourner une liste des personnes couverts par la caserne de pompiers correspondante. 
		// Donc si le num de la station est =1 elle doit renvoyer les hab couverts par la station 1. 
		// la liste doit inclure les informations spécifiques suivantes : prénom, nom, address, numéro de tel. 
		// de plus elle doit fournir un décompte du nombre d'adultes et du nombre d'enfants (18 ans ou moins)

		fail("Not yet implemented");
	}

}
