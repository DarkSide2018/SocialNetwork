package com.highload.socialNetwork.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
    private Long id;
    private Long chatId;
    private Long senderId;
    private Long recipientId;
    private String senderName;
    private String recipientName;
    private String content;
    private Date timestamp;
    private MessageStatus status;
}
