package com.henry.spring_batch_demo.domain;

import javax.validation.constraints.Size;

public class Person {
	
	@Size(max=4,min=2) //1 使用JSR-303规定的注解 来 校验数据 - 长度范围在2-4之间为合法name
	private String name;
	
	private int age;
	
	private String nation;
	
	private String address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	




	

}
