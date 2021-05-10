package com.henry.springdatajpademo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity //1 声明：当前类型是 一个与数据库表相映射的 实体类
@NamedQuery(name = "Person.withNameAndAddressNamedQuery", query = "select p from Person p where p.name=?1 and p.address=?2") // 由于数据表还不存在，所以飘红
public class Person {
	@Id //2 声明：此属性为 数据表中的主键
	@GeneratedValue //3 声明：使用逐渐自增的方式来 生成主键的值。 - Hibernate会自动生成一个 序列。
	private Long id;
	
	private String name;
	
	private Integer age;
	
	private String address;
	
	
	
	public Person() {
		super();
	}
	public Person(Long id, String name, Integer age, String address) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.address = address;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}


}
/*
启示：
1 通过实体类来生成 数据表结构；
特征：
	- 使用@Entity、@Id等注解；
	- 没有在属性上添加@Column注解时，hibernate会自动根据属性名 来 生成数据表的字段名。

2 通过数据表 来生成 实体类；
特征：使用@Table、@Column等注解。

 */
