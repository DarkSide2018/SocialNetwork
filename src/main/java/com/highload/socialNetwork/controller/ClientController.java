package com.highload.socialNetwork.controller;

import com.highload.socialNetwork.model.Client;
import com.highload.socialNetwork.service.ClientService;
import com.highload.socialNetwork.service.SecurityService;
import com.highload.socialNetwork.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ClientController {
    private final ClientService service;
    private final UserService userService;
    private final SecurityService securityService;
    private int currentPage = 1;
    private int size = 10;

    public ClientController(ClientService service, UserService userService, SecurityService securityService) {
        this.service = service;
        this.userService = userService;
        this.securityService = securityService;
    }
    @ModelAttribute("clientForm")
    public Client clientForm() {
        return new Client();
    }

    @ModelAttribute("page")
    public Integer page() {
        return currentPage;
    }

    @ModelAttribute("size")
    public Integer size() {
        return size;
    }

    @GetMapping({"/pageLeft"})
    public String pageLeft(Model model) {
        currentPage = currentPage - 1;
        List<Client> all = service.getAll(currentPage, size);
        model.addAttribute("clients", all);
        return "index";
    }

    @GetMapping({"/pageRight"})
    public String pageRight(Model model) {
        currentPage = currentPage + 1;
        List<Client> all = service.getAll(currentPage, size);
        model.addAttribute("clients", all);
        return "index";
    }

    @PostMapping({"/findByPrefix"})
    public String findByPrefix(Model model,@ModelAttribute("clientForm") Client clientForm) {
        List<Client> all = service.getByFirstNameAndSecondNamePrefix(clientForm.getName(), clientForm.getSurName());
        model.addAttribute("clients", all);
        model.addAttribute("clientForm", clientForm);
        return "index";
    }
}
