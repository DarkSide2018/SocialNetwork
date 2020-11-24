package com.highload.socialNetwork;

import com.github.javafaker.Faker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

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
    @Bean
    public Random random() throws Exception {
        return new Random();
    }
}
