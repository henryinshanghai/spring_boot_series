package com.henry.redis_demo.web;

import com.henry.redis_demo.dao.PersonDao;
import com.henry.redis_demo.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {
	
	@Autowired
	PersonDao personDao;
	
	@RequestMapping("/set") //1 演示存储/设置 字符串以及对象
	public void set(){
		Person person = new Person("1","wyf", 32);
		personDao.save(person); // 存储 id -> 对象的映射关系
		personDao.stringRedisTemplateDemo(); // 存储字符串的映射关系
	}
	
	@RequestMapping("/getStr") //2 演示 获取到字符串
	public String getStr(){
		return personDao.getString();
	}
	
	@RequestMapping("/getPerson") //3 演示 获取到对象
	public Person getPerson(){
		return personDao.getPerson();
	}
}
