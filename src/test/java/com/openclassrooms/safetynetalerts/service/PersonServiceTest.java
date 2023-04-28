package com.openclassrooms.safetynetalerts.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.openclassrooms.safetynetalerts.dto.Children;
import com.openclassrooms.safetynetalerts.dto.HouseAndResidents;
import com.openclassrooms.safetynetalerts.dto.PeopleForFirestation;
import com.openclassrooms.safetynetalerts.dto.PeopleInAddress;
import com.openclassrooms.safetynetalerts.dto.PersonDetails;
import com.openclassrooms.safetynetalerts.dto.PersonalInformation;
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
		List<String> mockAdress = Arrays.asList(new String[] {"ici"});
		Mockito.when(fRepo.getAdressesByNumber("1")).thenReturn(mockAdress);
		
		Person mockPersonFather = new Person("Alfred", "Nobel", "ici", null, null, null, null);
		Person mockPersonSon = new Person("Alfred Junior", "Nobel", "ici", null, null, null, null);
		List<Person> mockPeople = Arrays.asList(new Person[] {mockPersonFather, mockPersonSon});
		Mockito.when(pRepo.getPersonsByAdresses(mockAdress)).thenReturn(mockPeople);
		
		MedicalRecord mockMedicalRecordFather = new MedicalRecord("Alfred", "Nobel", "10/21/1833", null, null);
		Mockito.when(mRepo.getMedicalRecord(mockPersonFather)).thenReturn(mockMedicalRecordFather);
		
		MedicalRecord mockMedicalRecordSon = new MedicalRecord("Alfred", "Nobel", "10/21/2013", null, null);
		Mockito.when(mRepo.getMedicalRecord(mockPersonSon)).thenReturn(mockMedicalRecordSon);
		
		PeopleForFirestation result = pS.getAllPeopleForFirestation("1");
		
		assertEquals(1, result.getChildrenNumber());
		assertEquals(1, result.getAdultsNumber());
		assertEquals("Alfred", result.getListOfPeople().get(0).getFirstName());
	}
	
	@Test
	void testGetAllChildForAddress() {
		Person mockPersonFather = new Person("Alfred", "Nobel", "ici", null, null, null, null);
		Person mockPersonSon = new Person("Alfred Junior", "Nobel", "ici", null, null, null, null);
		List<Person> mockPeople = Arrays.asList(new Person[] {mockPersonFather, mockPersonSon});
		Mockito.when(pRepo.getPersonsByAdress("ici")).thenReturn(mockPeople);
		
		MedicalRecord mockMedicalRecordFather = new MedicalRecord("Alfred", "Nobel", "10/21/1833", null, null);
		Mockito.when(mRepo.getMedicalRecord(mockPersonFather)).thenReturn(mockMedicalRecordFather);
		
		MedicalRecord mockMedicalRecordSon = new MedicalRecord("Alfred", "Nobel", "10/21/2013", null, null);
		Mockito.when(mRepo.getMedicalRecord(mockPersonSon)).thenReturn(mockMedicalRecordSon);
		
		Children result = pS.getAllChildrenForAddress("ici");
		
		assertEquals(1, result.getListOfChildren().size());
		assertEquals(1, result.getListOfOtherResidents().size());

		Children result2 = pS.getAllChildrenForAddress("la-bas");
		
		assertNull(result2);
	}
	
	@Test
	void testGetAllPhonesForFirestation() {
		List<String> mockAdress = Arrays.asList(new String[] {"ici"});
		Mockito.when(fRepo.getAdressesByNumber("1")).thenReturn(mockAdress);

		Person mockPersonFather = new Person("Alfred", "Nobel", "ici", null, null, "06-36-66-66-66", null);
		Person mockPersonSon = new Person("Alfred Junior", "Nobel", "ici", null, null, "07 12 34 56 78", null);
		List<Person> mockPeople = Arrays.asList(new Person[] {mockPersonFather, mockPersonSon});
		Mockito.when(pRepo.getPersonsByAdresses(mockAdress)).thenReturn(mockPeople);
		
		List<String> result = pS.getAllPhonesForFirestation("1");
		
		assertEquals(2, result.size());
		assertEquals("06-36-66-66-66", result.get(0));
	}
	
	@Test
	void testGetAllPeopleInAddress() {
		Person mockPerson = new Person("Alfred", "Nobel", "ici", null, null, null, null);
		List<Person> mockPeople = Arrays.asList(new Person[] {mockPerson});
		Mockito.when(pRepo.getPersonsByAdress("ici")).thenReturn(mockPeople);
		
		Mockito.when(fRepo.getNumberByAdress("ici")).thenReturn("1");
		
		List<String> medocs = Arrays.asList(new String[] {"medoc1", "medoc2"});
		List<String> allergs = new ArrayList<String>();
		Mockito.when(mRepo.getMedicalRecord(mockPerson)).thenReturn(new MedicalRecord("Alfred", "Nobel", "10/21/2013", medocs, allergs));
		
		PeopleInAddress result = pS.getAllPeopleInAddress("ici");
		
		assertEquals("1", result.getFirestationNumber());
		assertEquals(1, result.getPeopleDetails().size());
		assertEquals(0, result.getPeopleDetails().get(0).getAllergies().size());
	}
	
	@Test
	void testGetAllHousesAndResidentsForFirestations() {
		List<String> mockAdress = Arrays.asList(new String[] {"ici"});
		Mockito.when(fRepo.getAdressesByNumber("1")).thenReturn(mockAdress);
		
		Person mockPerson = new Person("Alfred", "Nobel", "ici", null, null, null, null);
		List<Person> mockPeople = Arrays.asList(new Person[] {mockPerson});
		Mockito.when(pRepo.getPersonsByAdress("ici")).thenReturn(mockPeople);
		
		List<String> medocs = Arrays.asList(new String[] {"medoc1", "medoc2"});
		List<String> allergs = new ArrayList<String>();
		Mockito.when(mRepo.getMedicalRecord(mockPerson)).thenReturn(new MedicalRecord("Alfred", "Nobel", "10/21/2013", medocs, allergs));
		
		List<String> listOfNumber = Arrays.asList(new String[] {"1"});
		List<HouseAndResidents> result = pS.getAllHousesAndResidentsForFirestations(listOfNumber);
		
		assertEquals("ici", result.get(0).getAddress());
		assertEquals(1, result.get(0).getListOfResidents().size());
	}

	@Test
	void testGetPersonalInformation() {
		Person mockPerson = new Person("Alfred", "Nobel", "ici", null, null, null, null);
		
		Mockito.when(pRepo.getPersonByName("Alfred", "Nobel")).thenReturn(mockPerson);
		
		MedicalRecord mockMedicalRecord = new MedicalRecord("Alfred", "Nobel", "10/21/1833", null, null);
		Mockito.when(mRepo.getMedicalRecord(mockPerson)).thenReturn(mockMedicalRecord);

		PersonalInformation result = pS.getPersonalInformation("Alfred", "Nobel");
		
		assertEquals("Alfred", result.getFirstName());
		assertTrue(result.getAge() > 150);
	}

	@Test
	void testGetAllEmailForCity() {
		Person mockPerson = new Person("Alfred", "Nobel", "ici", "Villeneuve-la-vieille", null, null, "nob.alf@web.com");
		List<Person> mockPeople = Arrays.asList(new Person[] {mockPerson});
		
		Mockito.when(pRepo.getPersonByCity("Villeneuve-la-vieille")).thenReturn(mockPeople);
		
		List<String> result = pS.getAllEmailForCity("Villeneuve-la-vieille");
		
		assertEquals(1, result.size());
		assertEquals("nob.alf@web.com", result.get(0));
	}

}
