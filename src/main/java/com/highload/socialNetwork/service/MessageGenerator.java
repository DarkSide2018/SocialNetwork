package com.highload.socialNetwork.service;

import com.github.javafaker.Faker;
import com.highload.socialNetwork.model.ChatMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Random;

@Service
@Slf4j
public class MessageGenerator {
    @Autowired
    private Random random;
    @Autowired
    private MessageService messageService;
    @Autowired
    private Faker faker;
    @Value("${runDialog}")
    private Boolean runDialog = false;

    public ChatMessage createMessage() {
        ChatMessage chatMessage = null;
        if (runDialog) {
            int anInt = random.nextInt(4);
            if (anInt == 3) {
                chatMessage = ChatMessage.builder()
                        .senderId(2L)
                        .senderName("Chuck Norris")
                        .recipientName("Author")
                        .recipientId(1L)
                        .chatId("2_1")
                        .timestamp(new Date(System.currentTimeMillis()))
                        .content("Wow. It is true").build();
                messageService.save(chatMessage, 2);
                System.out.println("Chuck -> " + chatMessage.getContent());
            }
            if (anInt == 2) {
                chatMessage = ChatMessage.builder()
                        .senderId(1L)
                        .senderName("Author")
                        .recipientName("Chuck Norris")
                        .recipientId(2L)
                        .chatId("1_2")
                        .timestamp(new Date(System.currentTimeMillis()))
                        .content(faker.chuckNorris().fact()).build();
                messageService.save(chatMessage, anInt);
                System.out.println("Author -> " + chatMessage.getContent());
            }
            if (anInt == 1) {
                chatMessage = ChatMessage.builder()
                        .senderId(1L)
                        .senderName("Author")
                        .recipientName("Chuck Norris")
                        .recipientId(2L)
                        .chatId("1_2")
                        .timestamp(new Date(System.currentTimeMillis()))
                        .content(faker.chuckNorris().fact()).build();
                messageService.save(chatMessage, anInt);
                System.out.println("Author  -> " + chatMessage.getContent());
            }
        }
        return chatMessage;
    }
}

