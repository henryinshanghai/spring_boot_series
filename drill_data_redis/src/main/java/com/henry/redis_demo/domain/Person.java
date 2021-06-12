package com.henry.redis_demo.domain;

import java.io.Serializable;

public class Person implements Serializable { // 实现 序列化接口(Serializable) - 以支持Jackson的序列化操作

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private Integer age;
	
	public Person() {
		super();
	} // 注：对一个类型的对象进行序列化操作时，需要该类型有一个空的构造方法
	public Person(String id, String name, Integer age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	
	

}
