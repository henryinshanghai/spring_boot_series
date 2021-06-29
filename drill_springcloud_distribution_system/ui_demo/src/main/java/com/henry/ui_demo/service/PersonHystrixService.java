package com.henry.ui_demo.service;

import com.henry.ui_demo.domain.Person;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// 使用Feign来调用 person微服务
@Service
public class PersonHystrixService { // 断路器的用法：封装方法的原始调用，并提供一个fallback方法

	@Autowired
	PersonService personService; 

	@HystrixCommand(fallbackMethod = "fallbackSave") //1 指定原始方法调用失败时的备用方法 - 手段：@HystrixCommand注解的 fallbackMethod属性
	public List<Person> save(String name) {
		return personService.save(name);
	}
	
	public List<Person> fallbackSave(String name){
		List<Person> list = new ArrayList<>();
		Person p = new Person(name+"没有保存成功，Person Service 故障");
		list.add(p);
		return list;
	}
}
