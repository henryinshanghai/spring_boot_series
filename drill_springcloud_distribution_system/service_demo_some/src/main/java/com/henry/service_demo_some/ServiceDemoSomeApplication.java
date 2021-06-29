package com.henry.service_demo_some;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient // 为啥不是 @EnableEurekaClient注解呢？
@RestController
public class ServiceDemoSomeApplication {

    @Value("${my.message}") //1 从config server中的某个配置文件中取值，并注入到message属性中
    private String message;


    @RequestMapping(value = "/getsome")
    public String getsome(){
        return message;
    }

    public static void main(String[] args) {
        SpringApplication.run(ServiceDemoSomeApplication.class, args);
    }

}
