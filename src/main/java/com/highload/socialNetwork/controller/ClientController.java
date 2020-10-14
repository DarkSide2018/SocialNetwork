package com.highload.socialNetwork.controller;

import com.highload.socialNetwork.service.Client;
import com.highload.socialNetwork.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ClientController {
    private final ClientService service;


    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping({"/","/signup"})
    public String signup(Model model ) {
        List<Client> all = service.getAll();
        model.addAttribute("clients",all);
        return "index";
    }
}
