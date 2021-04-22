package com.henry.drill_starter_using;

import com.henry.HelloService_03;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class DrillStarterUsingApplication {


    @Autowired
    HelloService_03 helloService03; // 这里的飘红并不影响项目运行，有点意思呀

    @RequestMapping("/")
    public String index() {
        return helloService03.sayHello();
    }

    public static void main(String[] args) {
        SpringApplication.run(DrillStarterUsingApplication.class, args);
    }

}
