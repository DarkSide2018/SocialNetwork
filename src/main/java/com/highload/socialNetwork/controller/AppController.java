package com.highload.socialNetwork.controller;

import com.highload.socialNetwork.model.Client;
import com.highload.socialNetwork.service.ClientService;
import com.highload.socialNetwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AppController {
    private final ClientService service;
    private final UserService userService;


    public AppController(ClientService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @GetMapping({"/","/signup"})
    public String signup(Model model ) {
        List<Client> all = service.getAll();
        model.addAttribute("clients",all);
        return "index";
    }


}
