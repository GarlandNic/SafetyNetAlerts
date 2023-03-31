package com.openclassrooms.safetynetalerts.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.openclassrooms.safetynetalerts.model.Firestation;
import com.openclassrooms.safetynetalerts.repository.FirestationRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class FirestationServiceTest {
	
	@Autowired
	FirestationService fs;
	
	@MockBean
	FirestationRepository fr;

	@Test
	void testSaveFirestation() {
		Mockito.when(fr.save(any(Firestation.class))).thenReturn(new Firestation("Here", "42"));
		
		Firestation f1 = new Firestation("Here", "42");
		Firestation f2 = fs.saveFirestation(f1);
		
		assertThat(f2).usingRecursiveComparison().isEqualTo(f1);
	}

}
