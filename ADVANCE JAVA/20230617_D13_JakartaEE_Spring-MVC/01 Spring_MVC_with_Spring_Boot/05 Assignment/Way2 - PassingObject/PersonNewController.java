package com.example.demo;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
@Controller
public class PersonNewController
{
	@GetMapping("person")
	public ModelAndView before()
	{
 		Person defaultPerson=new Person();
	
		return new ModelAndView("personNew","myperson",defaultPerson);
	}
	
	@PostMapping("person")
	public String afterSubmit(
		@ModelAttribute("mb") Person person,HttpSession session) 
	{
		return "success";
	}
}




















