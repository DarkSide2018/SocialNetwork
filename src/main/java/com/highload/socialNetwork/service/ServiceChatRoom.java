package com.highload.socialNetwork.service;

import com.github.javafaker.Faker;
import com.highload.socialNetwork.model.ChatMessage;
import com.highload.socialNetwork.repos.ChatMessageRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Random;

@Service
@AllArgsConstructor
@Slf4j
public class ServiceChatRoom {
    @Autowired
    private Random random;
    @Autowired
    private final ChatMessageRepository chatMessageRepository;
    @Autowired
    private final Faker faker;


    @PostConstruct
    public void init() throws InterruptedException {
        while (true) {
            int anInt = random.nextInt(4);
            ChatMessage chatMessage;
            if (anInt == 3) {
                chatMessage = ChatMessage.builder()
                        .senderId(2L)
                        .senderName("Chuck Norris")
                        .recipientName("Author")
                        .recipientId(1L)
                        .chatId("2_1")
                        .content("Wow. It is true").build();
                chatMessageRepository.save(chatMessage,2);
                System.out.println("Chuck -> " + chatMessage.getContent());
            }
            if (anInt == 2) {
                chatMessage = ChatMessage.builder()
                        .senderId(1L)
                        .senderName("Author")
                        .recipientName("Chuck Norris")
                        .recipientId(2L)
                        .chatId("1_2")
                        .content(faker.chuckNorris().fact()).build();
                chatMessageRepository.save(chatMessage,anInt);
                System.out.println("Author -> " + chatMessage.getContent());
            }
            if (anInt == 1) {
                chatMessage = ChatMessage.builder()
                        .senderId(1L)
                        .senderName("Author")
                        .recipientName("Chuck Norris")
                        .recipientId(2L)
                        .chatId("1_2")
                        .content(faker.chuckNorris().fact()).build();
                chatMessageRepository.save(chatMessage,anInt);
                System.out.println("Author  -> " + chatMessage.getContent());
            }
            Thread.sleep(1000);
        }
    }
}
