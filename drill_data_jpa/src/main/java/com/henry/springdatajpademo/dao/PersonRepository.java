package com.henry.springdatajpademo.dao;

import com.henry.springdatajpademo.domain.Person;
import com.henry.springdatajpademo.support.CustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// 如何使用自定义的Repo？ 答：让实体类Repo 继承自 自定义的Repo； - 这样就能够使用自定义Repo中的功能
public interface PersonRepository extends CustomRepository<Person, Long> { // 自定义的CustomRepo VS. JPA提供的JpaRepository
	// approach01 - 使用方法名(映射到字段) 来 查询
	List<Person> findByAddress(String address);

	// 使用方法名查询时，接收多个参数（字段）
	Person findByNameAndAddress(String name, String address);

	// approach02 - 使用@Query查询； 特征：方法参数能够传递到SQL语句中(按照名称进行绑定)
	@Query("select p from Person p where p.name= :name and p.address= :address")
	Person withNameAndAddressQuery(@Param("name") String name, @Param("address") String address);

	// approach03 - 结合实体类中的@NamedQuery注解 进行查询； 用法：1 在实体类中使用位置参数定义SQL； 2 在DAO接口中定义对应的方法。
	Person withNameAndAddressNamedQuery(String name, String address);

}
