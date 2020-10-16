package com.highload.socialNetwork.controller;

import com.highload.socialNetwork.model.User;
import com.highload.socialNetwork.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppRestController {

    private final UserService userService;

    public AppRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/user/{name}"})
    public User getByName(@PathVariable(value = "name") String name) {
        return   userService.getUserByName(name);
    }
}
