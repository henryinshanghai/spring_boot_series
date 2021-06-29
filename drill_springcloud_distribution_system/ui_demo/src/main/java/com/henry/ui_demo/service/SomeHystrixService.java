package com.henry.ui_demo.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

// 使用 Ribbon 来 调用some微服务
@Service
public class SomeHystrixService {

	@Autowired
	RestTemplate restTemplate; // 用法： 1 首先需要注入一个 RestTemplate对象； 特征：可以直接注入，因为Spring Boot已经配置好了这个类型

	@HystrixCommand(fallbackMethod = "fallbackSome") //2 为原始方法的调用 添加一个失败时的备用方法 - 手段：@HystrixCommand注解的fallbackMethod属性
	public String getSome() {
		return restTemplate.getForObject("http://some/getsome", String.class);
	}
	
	public String fallbackSome(){
		return "some service模块故障";
	}
}
