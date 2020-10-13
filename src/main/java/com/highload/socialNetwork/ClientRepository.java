package com.highload.socialNetwork;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public class ClientRepository {
    @Value("${spring.datasource.url}")
    private String jdbcUrl;
    @Value("${spring.datasource.username}")
    private String user;
    @Value("${spring.datasource.password}")
    private String password;

    @PostConstruct
    public List<Client> getAll(){
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM client");
            boolean execute = preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
            System.out.println("agee -> " + resultSet.getInt("age"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new ArrayList<>();
    }
}
