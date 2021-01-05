package com.highload.socialNetwork.service;

import com.highload.socialNetwork.model.ChatMessage;
import com.highload.socialNetwork.repos.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    private ChatMessageRepository repository;

    public void save(ChatMessage chatMessage, int shardNumber){
        repository.save(chatMessage,shardNumber);
    }
}
