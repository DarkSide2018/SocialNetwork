package com.highload.socialNetwork.model;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class ChatRoom {
    private Long id;
    private Long chatId;
    private Long senderId;
    private Long recipientId;

    public Long getChatId() {
        return id;
    }
}
