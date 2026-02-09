package com.example.demo;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class PersonNewController
{
	@GetMapping("person")
	public String before()
	{
 		return "personNew";
	}
		
	@PostMapping("person")
	public String afterSubmit(@RequestParam("personname") String name,@RequestParam("age") int personage, @RequestParam("address") String personaddress,Model model) 
	{
	
		Person person=new Person();
		person.setPersonName(personname);
		person.setPersonAge(personage);
		person.setPersonAddress(personaddress);
		model.addAttribute("myperson", person);
		return "success";
	}
}
