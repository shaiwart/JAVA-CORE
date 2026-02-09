package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.Person;
import com.example.services.PersonService;

@RestController
public class PersonController 
{
	@Autowired
	private PersonService pservice;
	
	@PostMapping("/addPerson")
	public void addPerson(@RequestBody Person person)
	{
		pservice.addPerson(person);
	}
	
	@GetMapping("/persons")
	public ResponseEntity<?> getAllPersons()
	{
		return ResponseEntity.ok(pservice.getAllPersons());
	}
}
