package com.highload.socialNetwork.service;

import com.highload.socialNetwork.model.User;
import com.highload.socialNetwork.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public UserService(UserRepository repository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.repository = repository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User getUserByName(String name){
        return repository.findByUserName(name);
    }

    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        repository.save(user);
    }

}
