package com.highload.socialNetwork.controller;

import com.highload.socialNetwork.model.NewsMessage;
import com.highload.socialNetwork.repos.NewsRepository;
import com.highload.socialNetwork.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverRecord;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(value = { "http://localhost:3000","http://localhost:8080" },
        allowedHeaders = { "*" },
        maxAge = 900
)
public class NewsController {

    @Autowired
    private KafkaReceiver<String, String> kafkaReceiver;
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private NewsService newsService;

    @CrossOrigin(allowedHeaders = "*")
    @GetMapping(value = "/event/news", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<NewsMessage> getNews() {
        Flux<ReceiverRecord<String, String>> kafkaFlux = kafkaReceiver.receive();
        return kafkaFlux.checkpoint("Messages are started being consumed")
                .log()
                .doOnNext(r -> r.receiverOffset().acknowledge())
                .map(it -> {
                    NewsMessage newsMessage = new NewsMessage(
                            1L,
                            "FRIENDS_TYPE",
                            "messageAboutFriends",
                            "Alice",
                            "Bob"
                            , it.value(),
                            LocalDateTime.now());
                    newsRepository.save(newsMessage);
                    return newsMessage;

                })
                .checkpoint("Messages are done consumed");
    }

    @CrossOrigin(allowedHeaders = "*")
    @GetMapping(value = "/event/last/news", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<NewsMessage> getLastNews() {
        return newsService.getLastNews();
    }
}
