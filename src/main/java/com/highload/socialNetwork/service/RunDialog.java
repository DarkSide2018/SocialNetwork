package com.highload.socialNetwork.service;

import com.github.javafaker.Faker;
import com.highload.socialNetwork.model.ChatMessage;
import com.highload.socialNetwork.repos.ChatMessageRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;

import java.util.Random;

public class RunDialog implements Runnable {

    private Random random;
    @Value("${runDialog}")
    private Boolean runDialog = false;
    private final ChatMessageRepository chatMessageRepository;

    private final Faker faker;

    public RunDialog(Random random, ChatMessageRepository chatMessageRepository, Faker faker) {
        this.random = random;
        this.chatMessageRepository = chatMessageRepository;
        this.faker = faker;
    }

    @SneakyThrows
    @Override
    public void run() {

        while (runDialog) {
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
