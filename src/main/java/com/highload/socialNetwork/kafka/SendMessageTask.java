package com.highload.socialNetwork.kafka;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.time.LocalDate;
import java.util.concurrent.ExecutionException;

@Component
public class SendMessageTask {

    @Autowired
    private Producer producer;

    @Autowired
    private Faker faker;

    @Scheduled(fixedRateString = "3000")
    public void send() throws ExecutionException, InterruptedException {
        String quote = faker.friends().quote();

        ListenableFuture<SendResult<String, String>> listenableFuture = this.producer.sendMessage("INPUT_DATA", "IN_KEY", quote);

        SendResult<String, String> result = listenableFuture.get();
        System.out.println(String.format("Produced:\ntopic: %s\noffset: %d\npartition: %d\nvalue size: %d", result.getRecordMetadata().topic(),
                result.getRecordMetadata().offset(),
                result.getRecordMetadata().partition(), result.getRecordMetadata().serializedValueSize()));
    }
}