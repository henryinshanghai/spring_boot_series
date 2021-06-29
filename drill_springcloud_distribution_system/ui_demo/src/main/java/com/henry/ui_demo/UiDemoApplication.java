package com.henry.ui_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients // 开启 Spring Boot对Feign客户端的支持 - 远端API调用
@EnableCircuitBreaker // 开启 对断路器特性的支持
@EnableZuulProxy // 开启 对网管代理的支持
public class UiDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(UiDemoApplication.class, args);
    }

}
