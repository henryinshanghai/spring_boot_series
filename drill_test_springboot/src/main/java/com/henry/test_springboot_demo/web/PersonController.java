package com.henry.test_springboot_demo.web;

import com.henry.test_springboot_demo.dao.PersonRepository;
import com.henry.test_springboot_demo.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	PersonRepository personRepository;
	
	@RequestMapping(method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE} )
	public List<Person> findAll(){
		return personRepository.findAll();
	}

}
