package com.highload.socialNetwork.news;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.highload.socialNetwork.model.NewsMessage;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;
@Component
public class ReactiveWebSocketHandler implements WebSocketHandler {

    @Autowired
    private KafkaService kafkaService;
    @Autowired
    private ObjectMapper json;

    @Override
    public Mono<Void> handle(WebSocketSession webSocketSession) {
        return webSocketSession.send(kafkaService.getTestTopicFlux()
                .map(record -> {
                    NewsMessage message = new NewsMessage("[Test] Add message", record.value());

                    try {
                        return json.writeValueAsString(message);
                    } catch (JsonProcessingException e) {
                        return "Error while serializing to JSON";
                    }
                })
                .map(webSocketSession::textMessage))
                .and(webSocketSession.receive()
                        .map(WebSocketMessage::getPayloadAsText).log());
    }
}
