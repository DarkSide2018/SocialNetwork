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
    @Value("${spring.datasource.username}")
    private String user;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.url-shard}")
    private String jdbcShardUrl;
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
    public Connection getConnection(int shard) {
     if(shard == 1) return getFirstShardConnection();
     if(shard == 2) return getSecondShardConnection();
     return getFirstShardConnection();
    }
    public Connection getFirstShardConnection() {
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
    public Connection getSecondShardConnection() {
        try {
            if(connection == null){
                connection = DriverManager.getConnection(jdbcShardUrl, user, password);
            }
            return connection;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
}
