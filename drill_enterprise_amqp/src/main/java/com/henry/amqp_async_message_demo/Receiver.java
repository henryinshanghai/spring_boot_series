package com.henry.amqp_async_message_demo;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    // 监听特定的目的地中的消息 - 手段：@RabbitListener(queues = <destination_name>)
	@RabbitListener(queues = "my-queue")
    public void receiveMessage(String message) {
	    // 对接收到的消息进行处理
        System.out.println("Received <" + message + ">");
    }

}
