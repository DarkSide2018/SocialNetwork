package com.highload.socialNetwork.controller;

import com.highload.socialNetwork.model.NewsMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class NewsController {

    @MessageMapping("/message")
    @SendTo("/chat/messages")
    public NewsMessage getMessages(NewsMessage message) {
        System.out.println(message);
        return message;
    }
}
