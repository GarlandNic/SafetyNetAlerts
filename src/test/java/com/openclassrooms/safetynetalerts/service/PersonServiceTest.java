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

import com.openclassrooms.safetynetalerts.dto.Children;
import com.openclassrooms.safetynetalerts.dto.PeopleForFirestation;
import com.openclassrooms.safetynetalerts.dto.PeopleInAddress;
import com.openclassrooms.safetynetalerts.dto.PersonDetails;
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
	
	@Test
	void testgetAllChildForAddress() {
		Person mockPersonFather = new Person("Alfred", "Nobel", "ici", null, null, null, null);
		Person mockPersonSon = new Person("Alfred Junior", "Nobel", "ici", null, null, null, null);
		List<Person> mockPeople = new ArrayList<Person>();
		mockPeople.add(mockPersonFather);
		mockPeople.add(mockPersonSon);
		Mockito.when(pRepo.getPersonsByAdress("ici")).thenReturn(mockPeople);
		
		MedicalRecord mockMedicalRecordFather = new MedicalRecord("Alfred", "Nobel", LocalDate.of(1833, 10, 21), null, null);
		Mockito.when(mRepo.getMedicalRecord(mockPersonFather)).thenReturn(mockMedicalRecordFather);
		
		MedicalRecord mockMedicalRecordSon = new MedicalRecord("Alfred", "Nobel", LocalDate.of(2013, 10, 21), null, null);
		Mockito.when(mRepo.getMedicalRecord(mockPersonSon)).thenReturn(mockMedicalRecordSon);
		
		Children result = pS.getAllChildrenForAddress("ici");
		
		assertEquals(1, result.getListOfChildren().size());
		assertEquals(1, result.getListOfOtherResidents().size());
	}
	
	@Test
	void testgetAllPhonesForFirestation() {
		List<String> mockAdress = new ArrayList<String>();
		mockAdress.add("ici");
		Mockito.when(fRepo.getAdressesByNumber("1")).thenReturn(mockAdress);

		Person mockPersonFather = new Person("Alfred", "Nobel", "ici", null, null, "06-36-66-66-66", null);
		Person mockPersonSon = new Person("Alfred Junior", "Nobel", "ici", null, null, "07 12 34 56 78", null);
		List<Person> mockPeople = new ArrayList<Person>();
		mockPeople.add(mockPersonFather);
		mockPeople.add(mockPersonSon);
		Mockito.when(pRepo.getPersonsByAdresses(mockAdress)).thenReturn(mockPeople);
		
		List<String> result = pS.getAllPhonesForFirestation("1");
		
		assertEquals(2, result.size());
		assertEquals("06-36-66-66-66", result.get(0));
	}
	
	@Test
	void testgetAllPeopleInAddress() {
		Person mockPersonFather = new Person("Alfred", "Nobel", "ici", null, null, "06-36-66-66-66", null);
		Person mockPersonSon = new Person("Alfred Junior", "Nobel", "ici", null, null, "07 12 34 56 78", null);
		List<Person> mockPeople = new ArrayList<Person>();
		mockPeople.add(mockPersonFather);
		mockPeople.add(mockPersonSon);		
		Mockito.when(pRepo.getPersonsByAdress("ici")).thenReturn(null);
		
		Mockito.when(fRepo.getNumberByAdress(null)).thenReturn(null);
		
		Mockito.when(mRepo.getMedicalRecord(null)).thenReturn(null);
		
		
//		List<Person> listOfPersons = personRepository.getPersonsByAdress(address);
//
//		String number = firestationRepository.getNumberByAdress(address);
//
//		List<PersonDetails> people = new ArrayList<PersonDetails>();
//		
//		listOfPersons.forEach(person -> {
//			people.add(new PersonDetails(person.getFirstName(), person.getLastName(), person.getPhone(), 
//					getAge(person), medicalRecordRepository.getMedicalRecord(person).getMedications(), 
//					medicalRecordRepository.getMedicalRecord(person).getAllergies()));
//		});
//
//		return (new PeopleInAddress(people, number));
		PeopleInAddress result = pS.getAllPeopleInAddress("ici");
	}
}
