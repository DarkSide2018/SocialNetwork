package com.highload.socialNetwork.repos;

import com.highload.socialNetwork.model.ChatRoom;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ChatRoomRepository {

    public ChatRoom findBySenderIdAndRecipientId(String senderId, String recipientId) {
        return ChatRoom.builder().build();

    }
}
