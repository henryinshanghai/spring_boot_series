package com.henry.dirll_data_transaction.service;

import com.henry.dirll_data_transaction.domain.Person;

public interface DemoService {
	public Person savePersonWithRollBack(Person person);
	public Person savePersonWithoutRollBack(Person person);

}
