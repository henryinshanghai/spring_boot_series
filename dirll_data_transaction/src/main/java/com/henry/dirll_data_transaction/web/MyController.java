package com.henry.dirll_data_transaction.web;

import com.henry.dirll_data_transaction.domain.Person;
import com.henry.dirll_data_transaction.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

	@Autowired
	DemoService demoService;
	
	@RequestMapping("/rollback")
	public Person rollback(Person person){ //1
		
		return demoService.savePersonWithRollBack(person);
	}
	
	@RequestMapping("/norollback")
	public Person noRollback(Person person){//2
		
		return demoService.savePersonWithoutRollBack(person);
		
		
	}

}
