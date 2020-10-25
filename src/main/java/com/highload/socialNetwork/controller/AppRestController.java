package com.highload.socialNetwork.controller;

import com.highload.socialNetwork.model.Client;
import com.highload.socialNetwork.model.User;
import com.highload.socialNetwork.service.ClientService;
import com.highload.socialNetwork.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AppRestController {

    private final UserService userService;
    private final ClientService clientService;

    public AppRestController(UserService userService, ClientService clientService) {
        this.userService = userService;
        this.clientService = clientService;
    }

    @GetMapping({"/user/{name}"})
    public User getByName(@PathVariable(value = "name") String name) {

        return   userService.getUserByName(name);
    }
    @PostMapping(value = "/client/findbyprefix",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<Client> findClient(@RequestBody Client clientForm){
        return clientService.getByFirstNameAndSecondNamePrefix(clientForm.getName(), clientForm.getSurName());
    }
}
