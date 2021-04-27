package com.henry.chapter7_06_p2p.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class WsController {

    @Autowired
    SimpMessagingTemplate messagingTemplate; // SimpMessagingTemplate 是 用于向浏览器发送消息的组件

    @MessageMapping("/chat")
    public void handleChat(Principal principal, String msg) { // Principal 是 包含当前用户信息的组件
        if (principal.getName().equals("henry")) {
            // 向指定的用户发送消息   手段：xxx对象的convertAndSendToUser()方法
            messagingTemplate.convertAndSendToUser("jennifer", // 接收消息的用户
                    "/queue/notifications",  // 用来给浏览器所订阅的地址
                    principal.getName() + "-send: " + msg); // 发送的消息本身
        } else { // 缺少这个else，会导致 jennifer无法发送消息
            messagingTemplate.convertAndSendToUser("henry",
                    "/queue/notifications",
                    principal.getName() + "-send: " + msg);
        }
    }

}
