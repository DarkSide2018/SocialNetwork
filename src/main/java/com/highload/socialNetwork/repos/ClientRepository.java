package com.highload.socialNetwork.repos;

import com.highload.socialNetwork.model.Client;
import com.highload.socialNetwork.model.User;
import com.highload.socialNetwork.service.DbConnectionProvider;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClientRepository {
    private final DbConnectionProvider provider;

    public ClientRepository(DbConnectionProvider provider) {
        this.provider = provider;
    }

    public List<Client> getAll(int page, int size) {
        List<Client> clients = new ArrayList<>();
        try (PreparedStatement preparedStatement = provider.getConnection().prepareStatement("SELECT * FROM client LIMIT ? OFFSET ?");) {
            preparedStatement.setInt(1, size);
            preparedStatement.setInt(2, page * size);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                Client client = new Client(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surName"),
                        resultSet.getInt("age"),
                        resultSet.getString("gender"),
                        resultSet.getString("interest"),
                        resultSet.getString("city")
                );
                clients.add(client);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return clients;
    }

    public Integer checkCount() {
        Connection connection = provider.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM client;", Statement.RETURN_GENERATED_KEYS);) {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (Throwable throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public void batchSave(List<Client> clients) {
        Connection connection = provider.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO client (`name`,surname,age,gender,interest,city) VALUES (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);) {
            for (Client client : clients) {
                int i = 1;
                preparedStatement.setString(i++, client.getName());
                preparedStatement.setString(i++, client.getSurName());
                preparedStatement.setInt(i++, client.getAge());
                preparedStatement.setString(i++, client.getGender());
                preparedStatement.setString(i++, client.getInterest());
                preparedStatement.setString(i, client.getCity());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Client> findByPrefixFirstNameAndSecondName(String first, String second) {
        List<Client> clients = new ArrayList<>();
        try (PreparedStatement preparedStatement = provider.getSlaveConnection().prepareStatement("SELECT * FROM client sc WHERE sc.name LIKE ? and sc.surname LIKE ? order by sc.id LIMIT 50 ");) {
            int i = 1;
            preparedStatement.setString(i++, first+"%");
            preparedStatement.setString(i, second+"%");
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                Client client = new Client(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surName"),
                        resultSet.getInt("age"),
                        resultSet.getString("gender"),
                        resultSet.getString("interest"),
                        resultSet.getString("city")
                );
                clients.add(client);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return clients;
    }
    public List<Client> getClientByName(String first) {
        List<Client> clients = new ArrayList<>();
        try (PreparedStatement preparedStatement = provider.getSlaveConnection().prepareStatement("SELECT * FROM client sc WHERE sc.name LIKE ? order by sc.id LIMIT 50 ");) {
            int i = 1;
            preparedStatement.setString(i, first+"%");
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                Client client = new Client(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surName"),
                        resultSet.getInt("age"),
                        resultSet.getString("gender"),
                        resultSet.getString("interest"),
                        resultSet.getString("city")
                );
                clients.add(client);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return clients;
    }
}
