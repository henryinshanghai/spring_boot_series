package com.henry.chapter07_6_websocket.web;

import com.henry.chapter07_6_websocket.domain.WiselyMessage;
import com.henry.chapter07_6_websocket.domain.WiselyResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WsController {

    // 浏览器向服务器端发送请求时， 使用 @MessageMapping 来 映射/welcome这个请求地址
    @MessageMapping("/welcome")
    // 在服务端有新消息时，会对订阅了特定的端点的浏览器发送消息
    @SendTo("/topic/getResponse") // 这里给浏览器起订阅的端点 就在 @SendTo 里定义
    public WiselyResponse say(WiselyMessage message) throws InterruptedException {
        Thread.sleep(3000);
        return new WiselyResponse("welcome" + message.getName() + "!");
    }

}
