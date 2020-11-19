package com.highload.socialNetwork.repos;

import com.highload.socialNetwork.model.ChatRoom;
import com.highload.socialNetwork.service.DbConnectionProvider;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class ChatRoomRepository {
    private final DbConnectionProvider provider;

    public ChatRoomRepository(DbConnectionProvider provider) {
        this.provider = provider;
    }

    public ChatRoom findBySenderIdAndRecipientId(Long senderId, Long recipientId) {
        return ChatRoom.builder().build();

    }

    public void save(ChatRoom chatRoom) {
        try (PreparedStatement preparedStatement = provider.getConnection().prepareStatement("INSERT INTO chat_room (chat_id,sender_id,recipient_id) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);) {
            int i = 1;
            preparedStatement.setLong(i++, chatRoom.getChatId());
            preparedStatement.setLong(i++, chatRoom.getSenderId());
            preparedStatement.setLong(i, chatRoom.getRecipientId());
            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
