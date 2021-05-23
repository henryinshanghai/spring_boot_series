package com.henry.dirll_data_transaction.service.impl;


import com.henry.dirll_data_transaction.dao.PersonRepository;
import com.henry.dirll_data_transaction.domain.Person;
import com.henry.dirll_data_transaction.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DemoServiceImpl implements DemoService {

	@Autowired
	PersonRepository personRepository; //1 注入Repo对象 - 用于操作数据库
	
	@Transactional(rollbackFor={IllegalArgumentException.class}) //2 定制：当方法中出现xxx异常时，把数据回滚
	public Person savePersonWithRollBack(Person person){
		Person p =personRepository.save(person);

		if(person.getName().equals("汪云飞")){
			throw new IllegalArgumentException("汪云飞已存在，数据将回滚"); //3 硬编码的方式，手动触发异常
		}
		return p;
	}

	@Transactional(noRollbackFor={IllegalArgumentException.class}) //4 定制：当方法中出现ooo异常时，不导致数据回滚
	public Person savePersonWithoutRollBack(Person person){
		Person p =personRepository.save(person);
		
		if(person.getName().equals("汪云飞")){
			throw new IllegalArgumentException("汪云飞虽已存在，数据将不会回滚");
		}
		return p;
	}
}
