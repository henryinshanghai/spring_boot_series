package com.henry.amqp_async_message_demo;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AmqpAsyncMessageDemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(AmqpAsyncMessageDemoApplication.class, args);
    }

    // 注入 发送消息的工具rabbitTemplate
    @Autowired
    RabbitTemplate rabbitTemplate;

    // 声明目的地队列的名称
    @Bean
    public Queue wiselyQueue() { // org.springframework.amqp.core.Queue
        return new Queue("my-queue");
    }

    // 特征：run()方法 会在应用启动时执行
    @Override
    public void run(String... args) throws Exception {
        // 向目的地发送消息 - 手段：convertAndSend(xxx, ooo)
        rabbitTemplate.convertAndSend("my-queue", "来自于 RabbitMQ 的问候");
    }
}
