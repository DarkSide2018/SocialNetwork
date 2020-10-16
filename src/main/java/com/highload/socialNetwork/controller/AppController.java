package com.highload.socialNetwork.controller;

import com.highload.socialNetwork.model.Client;
import com.highload.socialNetwork.model.User;
import com.highload.socialNetwork.service.ClientService;
import com.highload.socialNetwork.service.SecurityService;
import com.highload.socialNetwork.service.UserService;
import com.highload.socialNetwork.service.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AppController {
    private final ClientService service;
    private final UserService userService;
    private final SecurityService securityService;
    private final UserValidator userValidator;

    public AppController(ClientService service, UserService userService, SecurityService securityService, UserValidator userValidator) {
        this.service = service;
        this.userService = userService;
        this.securityService = securityService;
        this.userValidator = userValidator;
    }

    @GetMapping({"/","/signup"})
    public String signup(Model model ) {
        List<Client> all = service.getAll();
        model.addAttribute("clients",all);
        return "index";
    }
    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getName(), userForm.getPassword());

        return "redirect:/welcome";
    }


}
