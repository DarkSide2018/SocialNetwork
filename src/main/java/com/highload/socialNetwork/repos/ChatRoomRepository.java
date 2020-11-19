package com.highload.socialNetwork.repos;

import com.highload.socialNetwork.model.ChatRoom;
import org.springframework.stereotype.Repository;

@Repository
public class ChatRoomRepository {

    public ChatRoom findBySenderIdAndRecipientId(Long senderId, Long recipientId) {
        return ChatRoom.builder().build();

    }
}
