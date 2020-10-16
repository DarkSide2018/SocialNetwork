package com.highload.socialNetwork.controller;

import com.highload.socialNetwork.model.Client;
import com.highload.socialNetwork.model.User;
import com.highload.socialNetwork.service.ClientService;
import com.highload.socialNetwork.service.SecurityService;
import com.highload.socialNetwork.service.UserService;
import com.highload.socialNetwork.service.UserValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping({"/"})
    public String signup(Model model) {
        List<Client> all = service.getAll();
        List<User> userServiceAll = userService.getAll();
        model.addAttribute("clients", all);
        model.addAttribute("users", userServiceAll);
        return "index";
    }

    @GetMapping("/signup")
    public String showSignUpForm(User user) {
        return "registration";
    }
    @GetMapping("/login")
    public String login(User user) {
        return "login";
    }
    @PostMapping("/login")
    public String postlogin(User user) {
        System.out.println("loginPage");
        return "login";
    }

    @PostMapping("/addUser")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userService.save(userForm);
        securityService.autoLogin(userForm.getName(), userForm.getPassword());
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        userService.deleteById(id);
        return "index";
    }

}
