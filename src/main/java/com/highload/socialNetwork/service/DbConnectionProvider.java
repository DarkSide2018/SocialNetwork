package com.highload.socialNetwork.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Service
public class DbConnectionProvider {
    @Value("${spring.datasource.url}")
    private String jdbcUrl;
    @Value("${slave.datasource.url}")
    private String slaveJdbcUrl;
    @Value("${spring.datasource.username}")
    private String user;
    @Value("${spring.datasource.password}")
    private String password;
    private Connection connection = null;

    public Connection getConnection() {
        try {
            if(connection == null){
               connection = DriverManager.getConnection(jdbcUrl, user, password);
           }
           return connection;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
    public Connection getSlaveConnection() {
        try {
            if(connection == null){
                connection = DriverManager.getConnection(slaveJdbcUrl, user, password);
            }
            return connection;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
}
