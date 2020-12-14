package com.highload.socialNetwork.repos;

import com.highload.socialNetwork.model.ChatNotification;
import com.highload.socialNetwork.config.DbConnectionProvider;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class ChatNotificationRepository {
    private final DbConnectionProvider provider;

    public ChatNotificationRepository(DbConnectionProvider provider) {
        this.provider = provider;
    }
    public void save(ChatNotification notification) {
        try (PreparedStatement preparedStatement = provider.getConnection().prepareStatement("INSERT INTO chat_notification (sender_id,sender_name) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);) {
            int i = 1;
            preparedStatement.setLong(i++, notification.getSenderId());
            preparedStatement.setString(i, notification.getSenderName());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
