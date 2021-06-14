package com.henry.jms_async_message_demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.core.JmsTemplate;

@SpringBootApplication
public class JmsAsyncMessageDemoApplication implements CommandLineRunner { // CommandLineRunner接口 作用：定义程序启动后会执行的代码  手段：重写run()方法

    public static void main(String[] args) {
        SpringApplication.run(JmsAsyncMessageDemoApplication.class, args);
    }

    // 注入 Spring Boot已经配置好的 JmsTemplate的Bean实例
    @Autowired
    JmsTemplate jmsTemplate;

    @Override
    public void run(String... strings) throws Exception {
        // 发送消息 手段：send(destination, message)
        // 特征：相当于同时 在消息代理中定义了一个 叫做my-destination的目的地
        jmsTemplate.send("my-destination", new Msg());
    }
}
