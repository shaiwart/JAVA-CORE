package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.entities.Person;
import com.example.repositories.PersonRepository;

@SpringBootTest
class PersonRepositoryTest {

	@Autowired
	private PersonRepository repository;
	
	@Test
	void testIsPersonExistsById() {
		Person p=new Person();
	//	p.setId(100);
		p.setName("Abc");
		p.setCity("Mumbai");
		repository.save(p);
		
		boolean result=repository.isPersonExistsById(p.getId());
		assertThat(result).isTrue();
	}
	
	@BeforeEach
	void setUp()
	{
		System.out.println("Setting up");
	}


	@AfterEach
	void tearDown()
	{
		System.out.println("tearing down");
		repository.deleteAll();
	}

}
