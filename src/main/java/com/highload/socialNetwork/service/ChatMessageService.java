package com.highload.socialNetwork.service;

import com.highload.socialNetwork.model.ChatMessage;
import org.springframework.stereotype.Service;

@Service
public class ChatMessageService {
    public ChatMessage save(ChatMessage chatMessage) {
        return new ChatMessage();
    }
}
