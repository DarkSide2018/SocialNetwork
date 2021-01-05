package com.highload.socialNetwork;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.sql.DataSource;
import java.util.Random;

@SpringBootApplication
@EnableScheduling
public class SocialNetworkApplication {


    public static void main(String[] args) {
        System.setProperty("spring.profiles.default", "docker");
        SpringApplication.run(SocialNetworkApplication.class, args);
    }

    @Bean
    public Faker faker() throws Exception {
        return new Faker();
    }

    @Bean
    public Random random() throws Exception {
        return new Random();
    }

    @Bean
    public ObjectMapper objectMapper() throws Exception {
        return new ObjectMapper();
    }
}
