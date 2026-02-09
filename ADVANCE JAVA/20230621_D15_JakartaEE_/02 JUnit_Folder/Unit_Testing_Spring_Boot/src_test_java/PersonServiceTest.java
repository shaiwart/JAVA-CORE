package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.repositories.PersonRepository;
import com.example.services.PersonService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class PersonServiceTest 
{

	@Mock
	private PersonRepository prepository;
	
	private PersonService pservice;
	
	@BeforeEach
	void setUp()
	{
		pservice=new PersonService(this.prepository);
	}
	
	@Test
	void testGetAllPersons() {
		
		pservice.getAllPersons();
		verify(prepository).findAll();
	}
}
