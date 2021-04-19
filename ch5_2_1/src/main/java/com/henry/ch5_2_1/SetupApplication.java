package com.henry.ch5_2_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication // 1 Spring Boot中的核心注解。 作用：开启项目的自动配置
public class SetupApplication {

    @RequestMapping(value = "/")
    String index() {
        return "Hello Spring Boot";
    }

	// 2 main方法	作用：作为项目启动的入口。
	public static void main(String[] args) {
		SpringApplication.run(SetupApplication.class, args);
	}



}
