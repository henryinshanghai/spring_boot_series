package com.henry.chapter08_06.dao;


import com.henry.chapter08_06.domain.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

// 定义数据访问接口
public interface PersonRepository extends MongoRepository<Person, String> {

	 // 使用方法名来查询
	 Person findByName(String name);

	 // 使用@Query注解 来 进行查询 - 手段：在参数中 构造JSON字符串
	 @Query("{'age': ?0}") // 这个语法待进一步学习
     List<Person> withQueryFindByAge(Integer age);

}
