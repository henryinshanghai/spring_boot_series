package com.henry.chapter07_07;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Chapter0707Application {

    // 模拟的控制器方法 - 直接在方法体中硬编码返回值
    // produces属性 - 它的作用是指定返回值类型，不但可以设置返回值类型还可以设定返回值的字符编码；  参考：https://blog.csdn.net/jaryle/article/details/72965885
    @RequestMapping(value = "/search", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Person search(String personName) {
        // 这里返回的JavaBean 会被自动转化为JSON对象返给前端 - 因为添加了 @RestController注解
        return new Person(personName, 32, "hefei");

    }

    public static void main(String[] args) {
        SpringApplication.run(Chapter0707Application.class, args);
    }

}
