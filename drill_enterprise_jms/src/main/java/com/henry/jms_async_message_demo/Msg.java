package com.henry.jms_async_message_demo;

import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

public class Msg implements MessageCreator{ // 定义JMS中的消息 需要实现MessageCreator接口

	// 重写 createMessage()抽象方法
	@Override
	public Message createMessage(Session session) throws JMSException {
		return session.createTextMessage("测试消息");
	}
	
}
