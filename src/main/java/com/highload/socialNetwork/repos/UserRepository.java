package com.highload.socialNetwork.repos;

import com.highload.socialNetwork.model.User;
import com.highload.socialNetwork.config.DbConnectionProvider;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    private final DbConnectionProvider provider;

    public UserRepository(DbConnectionProvider provider) {
        this.provider = provider;
    }

    public User findByUserName(String name) {

        try (PreparedStatement preparedStatement = provider.getConnection().prepareStatement("SELECT * FROM `user` u where u.name  = ?");) {
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
    public void updateById(User user){
        try (PreparedStatement preparedStatement = provider.getConnection().prepareStatement("UPDATE `user` u  SET u.name=?, u.password=?  WHERE u.id=?", Statement.RETURN_GENERATED_KEYS);) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setLong(3, user.getId());
            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public User getById(Long id){
        try (PreparedStatement preparedStatement = provider.getConnection().prepareStatement("SELECT * FROM `user` u where u.id  = ?");) {
            preparedStatement.setLong(1, id);
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
        try (PreparedStatement preparedStatement = provider.getConnection().prepareStatement("INSERT INTO `user` (`name`,password) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteById(Integer id) {
        try (PreparedStatement preparedStatement = provider.getConnection().prepareStatement("DELETE FROM `user`  WHERE id = ?");) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (PreparedStatement preparedStatement = provider.getConnection().prepareStatement("SELECT * FROM `user`");) {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                String password = resultSet.getString("password");

                User client = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        password
                );
                users.add(client);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }
}
