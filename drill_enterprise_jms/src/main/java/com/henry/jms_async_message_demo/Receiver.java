package com.henry.jms_async_message_demo;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver { // 定义消息接收者

	// 监听指定目的地中的消息 - 手段：@JmsListener(destination = "xxx")
	// 注：这是Spring 4.1提供的特性
	@JmsListener(destination = "my-destination")
	public void receiveMessage(String message) {
		System.out.println("接受到: <" + message + ">");
	}

}
