package com.highload.socialNetwork.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@AllArgsConstructor
public class ChatRoom {
    private Long id;
    private String chatId;
    private Long senderId;
    private Long recipientId;
}
