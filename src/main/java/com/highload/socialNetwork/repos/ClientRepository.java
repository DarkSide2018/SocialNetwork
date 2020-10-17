package com.highload.socialNetwork.repos;

import com.highload.socialNetwork.model.Client;
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

    public List<Client> getAll() {
        List<Client> clients = new ArrayList<>();
        try (PreparedStatement preparedStatement = provider.getConnection().prepareStatement("SELECT * FROM social.client");) {

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
