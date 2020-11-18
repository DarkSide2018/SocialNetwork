package com.highload.socialNetwork.model;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class ChatRoom {
    private String id;
    private String chatId;
    private String senderId;
    private String recipientId;

    public String getChatId() {
        return id;
    }
}
