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
	
}
