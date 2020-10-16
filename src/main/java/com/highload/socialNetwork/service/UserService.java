package com.highload.socialNetwork.service;

import com.highload.socialNetwork.model.User;
import com.highload.socialNetwork.repos.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User getUserByName(String name){
        return repository.findByUserName(name);
    }
}
