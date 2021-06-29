package com.henry.ui_demo.service;

import com.henry.ui_demo.domain.Person;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@FeignClient("person")
public interface PersonService {

	// 使用Feign进行远程服务调用的方式 - 在自定义的接口中声明抽象方法，并在方法上指定具体的API路径（不需要提供具体实现）
	@RequestMapping(method = RequestMethod.POST,
					value = "/save",
					produces = MediaType.APPLICATION_JSON_VALUE,
					consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	List<Person> save(@RequestBody String name);
}
