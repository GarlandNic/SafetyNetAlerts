package com.openclassrooms.safetynetalerts.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.openclassrooms.safetynetalerts.dto.PeopleForFirestation;
import com.openclassrooms.safetynetalerts.model.MedicalRecord;
import com.openclassrooms.safetynetalerts.model.Person;
import com.openclassrooms.safetynetalerts.model.PersonIdentity;
import com.openclassrooms.safetynetalerts.repository.FirestationRepository;
import com.openclassrooms.safetynetalerts.repository.MedicalRecordRepository;
import com.openclassrooms.safetynetalerts.repository.PersonRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class PersonServiceTest {
	
	@Autowired
	PersonService pS;
	
	@MockBean
	PersonRepository pRepo;

	@MockBean
	FirestationRepository fRepo;

	@MockBean
	MedicalRecordRepository mRepo;

	@Test
	void testSavePerson() {
		Mockito.when(pRepo.save(any(Person.class))).thenReturn(new Person("Alfred", "Nobel", null, null, null, null, null));
		
		Person p1 = new Person("Alfred", "Nobel", null, null, null, null, null);
		Person p2 = pS.savePerson(p1);
		
		assertThat(p2).usingRecursiveComparison().isEqualTo(p1);
	}
	
	@Test
	void testReplacePerson() {
		Mockito.when(pRepo.save(any(Person.class))).thenReturn(new Person("Alfred", "Nobel", null, null, null, null, null));
		
		Person p1 = new Person("Alfred", "Nobel", null, null, null, null, null);
		Person p2 = pS.replacePerson((new PersonIdentity("Alfred","Nobel")), p1);
		
		assertThat(p2).usingRecursiveComparison().isEqualTo(p1);
	}
	
	@Test
	void testGetAllPeopleForFirestation() {
		List<String> mockAdress = new ArrayList<String>();
		mockAdress.add("ici");
		Mockito.when(fRepo.getAdressesByNumber("1")).thenReturn(mockAdress);
		Person mockPerson = new Person("Alfred", "Nobel", "ici", null, null, null, null);
		List<Person> mockPeople = new ArrayList<Person>();
		mockPeople.add(mockPerson);
		Mockito.when(pRepo.getPersonsByAdresses(mockAdress)).thenReturn(mockPeople);
		MedicalRecord mockMedicalRecord = new MedicalRecord("Alfred", "Nobel", LocalDate.of(1833, 10, 21), null, null);
		Mockito.when(mRepo.getMedicalRecord(mockPerson)).thenReturn(mockMedicalRecord);
		
		PeopleForFirestation result = pS.getAllPeopleForFirestation("1");
		
		assertEquals(0, result.getChildrenNumber());
		assertEquals(1, result.getAdultsNumber());
		assertEquals("Alfred", result.getListOfPeople().get(0).getFirstName());
	}
}
