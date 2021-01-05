package com.highload.socialNetwork.repos;

import com.highload.socialNetwork.config.DbConnectionProvider;
import com.highload.socialNetwork.model.NewsMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class NewsRepository {
    @Autowired
    private  DbConnectionProvider provider;

    public void save(NewsMessage newsMessage) {
        try (PreparedStatement preparedStatement = provider.getConnection().prepareStatement("INSERT INTO chat_message (chat_id,sender_id,recipient_id,sender_name,recipient_name,content,status,`timestamp`) VALUES (?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);) {
//            int i = 1;
//            preparedStatement.setString(i++, chatMessage.getChatId());
//            preparedStatement.setLong(i++, chatMessage.getSenderId());
//            preparedStatement.setLong(i++, chatMessage.getRecipientId());
//            preparedStatement.setString(i++, chatMessage.getSenderName());
//            preparedStatement.setString(i++, chatMessage.getRecipientName());
//            preparedStatement.setString(i++, chatMessage.getContent());
//            preparedStatement.setString(i++, MessageStatus.DELIVERED.toString());
//            preparedStatement.setDate(i, chatMessage.getTimestamp());
//            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
