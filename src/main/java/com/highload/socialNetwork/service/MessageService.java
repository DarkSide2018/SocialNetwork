package com.highload.socialNetwork.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.highload.socialNetwork.controller.GreetingController;
import com.highload.socialNetwork.model.ChatMessage;
import com.highload.socialNetwork.repos.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    private ChatMessageRepository repository;
    @Autowired
    private GreetingController controller;
    @Autowired
    private ObjectMapper mapper;

    public void save(ChatMessage chatMessage, int shardNumber){
        try {
            String message = mapper.writeValueAsString(chatMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        repository.save(chatMessage,shardNumber);
    }
}
