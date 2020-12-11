package com.highload.socialNetwork.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
@EnableWebSocketMessageBroker
@Configuration
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry
                                               registry) {
        registry.addEndpoint("/chat-messaging").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry confi) {
        confi.enableSimpleBroker("/chat");
        confi.setApplicationDestinationPrefixes("/app");
    }
}
