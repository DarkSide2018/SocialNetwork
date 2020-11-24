package com.highload.socialNetwork.repos;

import com.highload.socialNetwork.model.ChatMessage;
import com.highload.socialNetwork.model.MessageStatus;
import com.highload.socialNetwork.service.DbConnectionProvider;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class ChatMessageRepository {
    private final DbConnectionProvider provider;

    public ChatMessageRepository(DbConnectionProvider provider) {
        this.provider = provider;
    }

    public void save(ChatMessage chatMessage, int shardNumber) {
        try (PreparedStatement preparedStatement = provider.getConnection().prepareStatement("INSERT INTO chat_message (chat_id,sender_id,recipient_id,sender_name,recipient_name,content,status,`timestamp`) VALUES (?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);) {
            int i = 1;
            preparedStatement.setString(i++, chatMessage.getChatId());
            preparedStatement.setLong(i++, chatMessage.getSenderId());
            preparedStatement.setLong(i++, chatMessage.getRecipientId());
            preparedStatement.setString(i++, chatMessage.getSenderName());
            preparedStatement.setString(i++, chatMessage.getRecipientName());
            preparedStatement.setString(i++, chatMessage.getContent());
            preparedStatement.setString(i++, MessageStatus.DELIVERED.toString());
            preparedStatement.setDate(i, chatMessage.getTimestamp());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
