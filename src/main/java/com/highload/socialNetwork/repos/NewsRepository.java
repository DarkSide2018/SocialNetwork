package com.highload.socialNetwork.repos;

import com.highload.socialNetwork.config.DbConnectionProvider;
import com.highload.socialNetwork.model.NewsMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;

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
    public List<NewsMessage> getLastNews() {
        List<NewsMessage> newsMessageList = new ArrayList<>();
        try (PreparedStatement preparedStatement = provider.getConnection().prepareStatement("SELECT id, content, `timestamp` FROM news order by `timestamp` DESC limit 5", Statement.RETURN_GENERATED_KEYS)) {
              preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                NewsMessage newsMessage = new NewsMessage(
                        resultSet.getLong("id"),
                        "type",
                        "anyMessage",
                        "fromAnyOne",
                        "toSomeOne",
                        resultSet.getString("content"),
                         resultSet.getTimestamp("timestamp").toLocalDateTime()
                );
                newsMessageList.add(newsMessage);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return newsMessageList;
    }
}
