package com.highload.socialNetwork.controller;

import com.highload.socialNetwork.model.ChatMessage;
import com.highload.socialNetwork.model.ChatNotification;
import com.highload.socialNetwork.service.ChatMessageService;


import com.highload.socialNetwork.service.ServiceChatRoom;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageService chatMessageService;
    private final ServiceChatRoom chatRoomService;

    public ChatController(SimpMessagingTemplate messagingTemplate,
                          ChatMessageService chatMessageService,
                          ServiceChatRoom chatRoomService) {
        this.messagingTemplate = messagingTemplate;
        this.chatMessageService = chatMessageService;
        this.chatRoomService = chatRoomService;
    }

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage) {
        chatRoomService.getChatId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true);


        ChatMessage saved = chatMessageService.save(chatMessage);

        messagingTemplate.convertAndSendToUser(
                String.valueOf(chatMessage.getRecipientId()),"/queue/messages",
                new ChatNotification(
                        saved.getId(),
                        saved.getSenderId(),
                        saved.getSenderName()));
    }
    @GetMapping("/chatting")
    public String chatting() {
        return "chat";
    }

}
