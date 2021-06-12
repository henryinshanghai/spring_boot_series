package com.henry.chapter08_06.web;


import com.henry.chapter08_06.dao.PersonRepository;
import com.henry.chapter08_06.domain.Location;
import com.henry.chapter08_06.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

@RestController
public class DataController {
	
	@Autowired
	PersonRepository personRepository;

	// 用于保存数据的接口
	@RequestMapping("/save")
	public Person save(){
		Person  p = new Person("wyf",32);
		Collection<Location> locations =  new LinkedHashSet<Location>();
		Location loc1 = new Location("上海","2009");
		Location loc2 = new Location("合肥","2010");
		Location loc3 = new Location("广州","2011");
		Location loc4 = new Location("马鞍山","2012");
		locations.add(loc1);
		locations.add(loc2);
		locations.add(loc3);
		locations.add(loc4);
		p.setLocations(locations);
		
		return personRepository.save(p);
	} // 没有在控制台看到日志，有点心慌 - 去查看数据库； 发现compass的test数据库下 多出了一个person文档

	// 测试方法名查询
	@RequestMapping("/q1")
	public Person q1(String name){
		return personRepository.findByName(name);
	}

	// 测试@Query查询方式
	@RequestMapping("/q2")
	public List<Person> q2(Integer age){
		return personRepository.withQueryFindByAge(age);
	}

}
