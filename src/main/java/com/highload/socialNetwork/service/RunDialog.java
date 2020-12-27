package com.highload.socialNetwork.service;

import com.github.javafaker.Faker;
import com.highload.socialNetwork.model.ChatMessage;
import com.highload.socialNetwork.repos.ChatMessageRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Random;

public class RunDialog implements Runnable {

    private Random random;

    private final MessageService messageService;

    private final Faker faker;

    public RunDialog(Random random, MessageService messageService, Faker faker) {
        this.random = random;
        this.messageService = messageService;
        this.faker = faker;
    }

    @SneakyThrows
    @Override
    public void run() {
    }
}
