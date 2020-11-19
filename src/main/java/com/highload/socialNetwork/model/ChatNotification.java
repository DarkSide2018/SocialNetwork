package com.highload.socialNetwork.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ChatNotification {
    private Long id;
    private Long senderId;
    private String senderName;

}
