package com.highload.socialNetwork.controller;

import com.highload.socialNetwork.model.NewsMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Controller
public class NewsController {

    @MessageMapping("/message")
    @SendTo("/chat/messages")
    public String getMessages(NewsMessage message) {
        System.out.println(message);
        return "helloe";
    }
    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public String send() throws Exception {
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        return UUID.randomUUID() + "time -> " + time;
    }
}
