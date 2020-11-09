package com.highload.socialNetwork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class SocialNetworkApplication {

    public static void main(String[] args) {
        System.setProperty("spring.profiles.default", "docker");
        SpringApplication.run(SocialNetworkApplication.class, args);
    }
}
