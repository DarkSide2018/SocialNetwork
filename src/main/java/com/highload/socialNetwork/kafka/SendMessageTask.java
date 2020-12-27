package com.highload.socialNetwork.kafka;

import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.time.LocalDate;
import java.util.concurrent.ExecutionException;

@Component
public class SendMessageTask {


    private final Producer producer;

    public SendMessageTask(Producer producer) {
        this.producer = producer;
    }

    @Scheduled(fixedRateString = "3000")
    public void send() throws ExecutionException, InterruptedException {

        ListenableFuture<SendResult<String, String>> listenableFuture = this.producer.sendMessage("INPUT_DATA", "IN_KEY", "start of Epoch Great THINGS The Big One"+LocalDate.now().toString());

        SendResult<String, String> result = listenableFuture.get();
        System.out.println(String.format("Produced:\ntopic: %s\noffset: %d\npartition: %d\nvalue size: %d", result.getRecordMetadata().topic(),
                result.getRecordMetadata().offset(),
                result.getRecordMetadata().partition(), result.getRecordMetadata().serializedValueSize()));
    }
}