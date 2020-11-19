package com.highload.socialNetwork.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatNotification {
    private Long id;
    private Long senderId;
    private String senderName;

}
