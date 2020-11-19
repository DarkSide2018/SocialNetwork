package com.highload.socialNetwork.service;

import com.highload.socialNetwork.model.ChatRoom;
import com.highload.socialNetwork.repos.ChatRoomRepository;
import org.springframework.stereotype.Service;

@Service
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;

    public ChatRoomService(ChatRoomRepository chatRoomRepository) {
        this.chatRoomRepository = chatRoomRepository;
    }

    public Long getChatId(
            Long senderId, Long recipientId, boolean createIfNotExist) {

        Long chatId = chatRoomRepository
                .findBySenderIdAndRecipientId(senderId, recipientId).getChatId();
        ChatRoom senderRecipient = ChatRoom
                .builder()
                .chatId(chatId)
                .senderId(senderId)
                .recipientId(recipientId)
                .build();

        ChatRoom recipientSender = ChatRoom
                .builder()
                .chatId(chatId)
                .senderId(recipientId)
                .recipientId(senderId)
                .build();
        return chatId;
    }
}
