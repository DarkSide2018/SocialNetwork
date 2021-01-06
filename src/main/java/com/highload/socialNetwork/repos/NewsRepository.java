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
        try (PreparedStatement preparedStatement = provider.getConnection().prepareStatement("INSERT INTO news (content,`timestamp`) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);) {
            int i = 1;
            preparedStatement.setString(i++, newsMessage.getContent());
            preparedStatement.setObject(i, newsMessage.getCreatedAt());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
