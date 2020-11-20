package com.highload.socialNetwork.service;

import com.highload.socialNetwork.model.ChatRoom;
import com.highload.socialNetwork.repos.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceChatRoom {
    private final ChatRoomRepository chatRoomRepository;

    public ServiceChatRoom(ChatRoomRepository chatRoomRepository) {
        this.chatRoomRepository = chatRoomRepository;
    }

    public String getChatId(Long senderId, Long recipientId, boolean createIfNotExist) {

        ChatRoom bySenderIdAndRecipientId = chatRoomRepository
                .findBySenderIdAndRecipientId(senderId, recipientId);
        String chatId = String.format("%s_%s", senderId, recipientId);
        if (bySenderIdAndRecipientId != null) {
            chatId = bySenderIdAndRecipientId.getChatId();
        }
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
        chatRoomRepository.save(senderRecipient);
        chatRoomRepository.save(recipientSender);
        return chatId;
    }

}
