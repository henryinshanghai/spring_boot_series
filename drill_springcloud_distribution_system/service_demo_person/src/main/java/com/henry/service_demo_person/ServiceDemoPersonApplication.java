package com.henry.service_demo_person;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient // 声明当前服务作为 Eureka Client
public class ServiceDemoPersonApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceDemoPersonApplication.class, args);
    }

}
