package com.highload.socialNetwork;

import com.github.javafaker.Faker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.util.Random;

@EnableWebMvc
@SpringBootApplication
public class SocialNetworkApplication {

    public static void main(String[] args) {
        System.setProperty("spring.profiles.default", "docker");
        SpringApplication.run(SocialNetworkApplication.class, args);
    }

    @Bean
    public Faker faker() throws Exception {
        return new Faker();
    }
    @Bean(name = "datasource1")
    @ConfigurationProperties("spring.datasource.url")
    @Primary
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "datasource2")
    @ConfigurationProperties("spring.datasource.url-shard")
    public DataSource dataSource2(){
        return DataSourceBuilder.create().build();
    }
    @Bean
    public Random random() throws Exception {
        return new Random();
    }
}
