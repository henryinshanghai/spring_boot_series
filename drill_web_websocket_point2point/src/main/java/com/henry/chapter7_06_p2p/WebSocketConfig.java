package com.henry.chapter7_06_p2p;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker // 开启WebSocket消息代理
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    // 为WebSocket添加 节点(endpoint) - 通信的基础（all start from here）
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/endpointChat").withSockJS();
    }

    // 添加 点对点通信所需要的 /queue消息代理

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/queue");
    }
}
