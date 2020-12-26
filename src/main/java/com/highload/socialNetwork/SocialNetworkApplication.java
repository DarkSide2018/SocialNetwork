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
    @Value("${spring.datasource.driver-class-name}")
    private String driver;
    @Value("${spring.primaryliquibase.change-log}")
    private String chLogFilePrimary;
    @Value("${spring.secondaryliquibase.change-log}")
    private String chLogFileSecondary;
    @Value("${spring.primarydatasource.url}")
    private String jdbcUrl;
    @Value("${spring.secondarydatasource.url}")
    private String urlShard;
    @Value("${spring.datasource.username}")
    private String user;
    @Value("${spring.datasource.password}")
    private String password;

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

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.primarydatasource")
    public DataSource primaryDataSource() {

        return DataSourceBuilder.create()
                .username(user)
                .password(password)
                .url(jdbcUrl)
                .build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.secondarydatasource")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create()
                .username(user)
                .password(password)
                .url(urlShard)
                .build();
    }

    @Bean
    @ConfigurationProperties(prefix = "datasource.primaryliquibase")
    public LiquibaseProperties primaryLiquibaseProperties() {

        LiquibaseProperties liquibaseProperties = new LiquibaseProperties();
        liquibaseProperties.setChangeLog(chLogFilePrimary);
        return liquibaseProperties;
    }

    @Bean("primaryLiquibase")
    public SpringLiquibase primaryLiquibase() {
        return springLiquibase(primaryDataSource(), primaryLiquibaseProperties());
    }

    @Bean
    @ConfigurationProperties(prefix = "datasource.secondaryliquibase")
    public LiquibaseProperties secondaryLiquibaseProperties() {
        LiquibaseProperties liquibaseProperties = new LiquibaseProperties();
        liquibaseProperties.setChangeLog(chLogFileSecondary);
        return liquibaseProperties;
    }

    @Bean("secondaryLiquibase")
    public SpringLiquibase secondaryLiquibase() {
        return springLiquibase(secondaryDataSource(), secondaryLiquibaseProperties());
    }

    private static SpringLiquibase springLiquibase(DataSource dataSource, LiquibaseProperties properties) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog(properties.getChangeLog());
        liquibase.setContexts(properties.getContexts());
        liquibase.setDefaultSchema(properties.getDefaultSchema());
        liquibase.setDropFirst(properties.isDropFirst());
        liquibase.setShouldRun(properties.isEnabled());
        liquibase.setLabels(properties.getLabels());
        liquibase.setChangeLogParameters(properties.getParameters());
        liquibase.setRollbackFile(properties.getRollbackFile());
        return liquibase;
    }
}
