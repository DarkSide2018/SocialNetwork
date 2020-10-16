package com.highload.socialNetwork.repos;

import com.highload.socialNetwork.model.User;
import com.highload.socialNetwork.service.DbConnectionProvider;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserRepository {
    private final DbConnectionProvider provider;

    public UserRepository(DbConnectionProvider provider) {
        this.provider = provider;
    }

    public User findByUserName(String name) {

        try (PreparedStatement preparedStatement = provider.getConnection().prepareStatement("SELECT * FROM social.user u where u.name  = ?");) {
            preparedStatement.setString(1, name);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                return new User(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("password")
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return null;
    }

    public void save(User user) {
        try (PreparedStatement preparedStatement = provider.getConnection().prepareStatement("INSERT INTO social.user VALUES (?,?)");) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
