package com.henry.chapter08_0503.service.impl;

import com.henry.chapter08_0503.dao.PersonRepository;
import com.henry.chapter08_0503.domain.Person;
import com.henry.chapter08_0503.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Service
public class DemoServiceImpl implements DemoService {
	
	@Autowired
	PersonRepository personRepository;

	@Override
	// 把 新增或者更新的数据 添加到缓存中
	@CachePut(value = "people", key = "#person.id") // value定义缓存名称，key定义缓存的获取键
	public Person save(Person person) {
		Person p = personRepository.save(person);
		System.out.println("为id、key为:"+p.getId()+"数据做了缓存");
		return p;
	}

	@Override
	@CacheEvict(value = "people")//2 从 指定名称的缓存中 删除key为id(参数)的数据
	public void remove(Long id) {
		System.out.println("删除了id、key为"+id+"的数据缓存");
		//这里不做实际删除操作
	}

	@Override
	@Cacheable(value = "people", key = "#person.id")//3 把 特定的数据(key-value) 添加到 特定的缓存中
	public Person findOne(Person person) {
		Person p = personRepository.findOne(person.getId()); // 这里为什么会报错？？？ 因为使用了2.5.0版本的parent 改用1.3.0.M的版本就Okay了(这会需要pom相关的改动)
		System.out.println("为id、key为:"+p.getId()+"数据做了缓存"); // 第二次获取相同ID的数据时，此语句预期不会输出
		return p;
	}

}
