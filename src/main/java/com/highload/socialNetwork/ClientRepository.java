package com.highload.socialNetwork;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClientRepository {
    @Autowired
    private DbConnectionProvider provider;

    public List<Client> getAll() {
        List<Client> clients = new ArrayList<>();
        try (PreparedStatement preparedStatement = provider.getConnection().prepareStatement("SELECT * FROM client");) {

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
