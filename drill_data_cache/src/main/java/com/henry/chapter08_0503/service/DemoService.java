package com.henry.chapter08_0503.service;

import com.henry.chapter08_0503.domain.Person;

public interface DemoService {
	public Person save(Person person);
	
	public void remove(Long id);
	
	public Person findOne(Person person);

}
