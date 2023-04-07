package com.openclassrooms.safetynetalerts.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.openclassrooms.safetynetalerts.model.MedicalRecord;
import com.openclassrooms.safetynetalerts.model.Person;
import com.openclassrooms.safetynetalerts.model.PersonIdentity;
import com.openclassrooms.safetynetalerts.repository.MedicalRecordRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class MedicalRecordServiceTest {
	
	@Autowired
	MedicalRecordService mrs;
	
	@MockBean
	MedicalRecordRepository mrr;	

	@Test
	void testSaveMedicalRecord() {
		Mockito.when(mrr.save(any(MedicalRecord.class))).thenReturn(new MedicalRecord("Alfred", "Nobel", null, null, null));
		
		MedicalRecord mr1 = new MedicalRecord("Alfred", "Nobel", null, null, null);
		MedicalRecord mr2 = mrs.saveMedicalRecord(mr1);
		
		assertThat(mr2).usingRecursiveComparison().isEqualTo(mr1);
	}

	@Test
	void testReplaceMedicalRecord() {
		Mockito.when(mrr.save(any(MedicalRecord.class))).thenReturn(new MedicalRecord("Alfred", "Nobel", null, null, null));
		
		MedicalRecord mr1 = new MedicalRecord("Alfred", "Nobel", null, null, null);
		MedicalRecord mr2 = mrs.replaceMedicalRecord((new PersonIdentity("Alfred","Nobel")), mr1);
		
		assertThat(mr2).usingRecursiveComparison().isEqualTo(mr1);
	}
	
	@Test
	void testIsChildrenFalse() {
		Mockito.when(mrr.getMedicalRecord(any(Person.class))).thenReturn(new MedicalRecord("Alfred", "Nobel", LocalDate.of(1833, 10, 21), null, null));
		
		Person p1 = new Person("Alfred", "Nobel", null, null, null, null, null);
		boolean child1 = mrs.isChildren(p1);
		
		assertFalse(child1);
	}
	
	@Test
	void testIsChildrenTrue() {
		Mockito.when(mrr.getMedicalRecord(any(Person.class))).thenReturn(new MedicalRecord("Alfred190", "Nobel190", LocalDate.of(2013, 10, 21), null, null));
		
		Person p1 = new Person("Alfred190", "Nobel190", null, null, null, null, null);
		boolean child1 = mrs.isChildren(p1);
		
		assertTrue(child1);
	}
}
