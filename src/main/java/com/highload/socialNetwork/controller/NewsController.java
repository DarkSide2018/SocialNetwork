package com.highload.socialNetwork.controller;

import com.highload.socialNetwork.model.NewsMessage;
import com.highload.socialNetwork.model.Usage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverRecord;

import java.time.Duration;
import java.util.Date;
import java.util.Random;

@RestController
public class NewsController {

    @Autowired
    private KafkaReceiver<String,String> kafkaReceiver;

    @CrossOrigin(allowedHeaders = "*")
    @GetMapping(value = "/event/resources/usage", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Usage> getResourceUsage() {

        Random random = new Random();

        return Flux.interval(Duration.ofSeconds(1))
                .map(it -> new Usage(
                        random.nextInt(101),
                        random.nextInt(101),
                        new Date()));

    }

    @CrossOrigin(allowedHeaders = "*")
    @GetMapping(value = "/event/news", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<NewsMessage> getNews() {
        Flux<ReceiverRecord<String,String>> kafkaFlux = kafkaReceiver.receive();
        return kafkaFlux.checkpoint("Messages are started being consumed")
                .log()
                .doOnNext(r -> r.receiverOffset().acknowledge())
                .map(it -> new NewsMessage(
                        "test",
                "random.nextInt(101)",
                "random.nextInt(101)",
                it.value(),
                new Date().toString()))
                .checkpoint("Messages are done consumed");

    }
}
