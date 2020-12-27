package com.highload.socialNetwork.controller;

import com.highload.socialNetwork.service.MessageGenerator;
import com.highload.socialNetwork.springSocket.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingController {
    @Autowired
    private MessageGenerator generator;
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(String message) {
        return new Greeting(message);
    }

    @MessageMapping("/chuck")
    @SendTo("/topic/answer")
    public Greeting answer() {
        return new Greeting(generator.createMessage().getContent());
    }
}
