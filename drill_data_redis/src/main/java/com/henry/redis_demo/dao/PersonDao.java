package com.henry.redis_demo.dao;

import com.henry.redis_demo.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class PersonDao {
	
	@Autowired
	StringRedisTemplate stringRedisTemplate; //1 Spring Boot已经为开发者配置好了 StringRedisTemplate，因此可以直接注入

	//3 指定操作字符串的简单方法
	@Resource(name="stringRedisTemplate") // 尽管这里看上去有编译报错，但其实项目是跑得通的
	ValueOperations<String,String> valOpsStr;
	
	
	@Autowired
	RedisTemplate<Object, Object> redisTemplate; //2 同1

	//3 指定操作对象的简单方法
	@Resource(name="redisTemplate")
    ValueOperations<Object, Object> valOps;
	
	public void stringRedisTemplateDemo(){ //5 存储字符串类型
		valOpsStr.set("xx", "yy");
	}
	
	
	public void save(Person person){ //6 存储对象
		valOps.set(person.getId(),person);
	}
	
	public String getString(){//7 获取字符串
		return valOpsStr.get("xx");
	}
	
	public Person getPerson(){//8 获取对象
		return (Person) valOps.get("1");
	}

}
