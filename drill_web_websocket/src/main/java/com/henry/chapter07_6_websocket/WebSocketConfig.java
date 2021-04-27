package com.henry.chapter07_6_websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
// 开启 Spring Boot对WebSocket的支持 - 使用STOMP协议 来 传输消息(基于代理broker的消息message)
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    // 注册STOMP协议中的 服务端节点endpoint
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/endpointWisely").withSockJS(); // 指定使用SockJS协议
    }

    // 配置 消息代理(message broker)
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 广播式的通信方式 需要配置一个 /topic消息代理
        registry.enableSimpleBroker("/topic");
    }
}
