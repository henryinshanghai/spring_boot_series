package com.henry.discovery_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer // 声明当前服务 是作为一个Eureka Server存在的
public class DiscoveryDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryDemoApplication.class, args);
    }

}
