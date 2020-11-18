package com.highload.socialNetwork.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ChatNotification {
    private String id;
    private String senderId;
    private String senderName;

}
