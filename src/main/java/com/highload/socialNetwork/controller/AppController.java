package com.highload.socialNetwork.controller;

import com.highload.socialNetwork.model.Client;
import com.highload.socialNetwork.model.User;
import com.highload.socialNetwork.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AppController {
    private final ClientService service;

    public AppController(ClientService service) {
        this.service = service;
    }

    @ModelAttribute("clientForm")
    public Client clientForm() {
        return new Client();
    }

    @GetMapping({"/"})
    public String signup(Model model) {
        List<Client> all = service.getAll(1, 10);
        model.addAttribute("clients", all);
        return "index";
    }

    @GetMapping("/signup")
    public String showSignUpForm(User user) {
        return "registration";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String postlogin() {
        return "login";
    }


}
