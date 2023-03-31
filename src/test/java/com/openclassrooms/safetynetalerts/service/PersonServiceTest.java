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

import com.openclassrooms.safetynetalerts.model.Person;
import com.openclassrooms.safetynetalerts.repository.PersonRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class PersonServiceTest {
	
	@Autowired
	PersonService ps;
	
	// mocker le personrepository.save()
	@MockBean
	PersonRepository pr;

	@Test
	void testSavePerson() {
		Mockito.when(pr.save(any(Person.class))).thenReturn(new Person("Alfred", "Nobel", null, null, null, null, null));
		Person p1 = new Person("Alfred", "Nobel", null, null, null, null, null);
		Person p2 = ps.savePerson(p1);
		
		assertThat(p2).usingRecursiveComparison().isEqualTo(p1);
	}

}
