package com.highload.socialNetwork.service;

import com.github.javafaker.Faker;
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
       new Thread(new RunDialog(
               random,
               chatMessageRepository,
               faker
       )).start();
    }
}
