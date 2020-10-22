package com.highload.socialNetwork.controller;

import com.highload.socialNetwork.model.User;
import com.highload.socialNetwork.service.ClientService;
import com.highload.socialNetwork.service.SecurityService;
import com.highload.socialNetwork.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final ClientService service;
    private final UserService userService;
    private final SecurityService securityService;
    public UserController(ClientService service, UserService userService, SecurityService securityService) {
        this.service = service;
        this.userService = userService;
        this.securityService = securityService;
    }
    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User userForm) {
        userService.update(userForm);
        securityService.autoLogin(userForm.getName(), userForm.getPassword());
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        userService.deleteById(id);
        return "redirect:/";
    }
    @PostMapping("/adduser")
    public String registration(@ModelAttribute("user") User userForm) {
        userService.save(userForm);
        securityService.autoLogin(userForm.getName(), userForm.getPassword());
        return "redirect:/";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getByUserId(id));
        return "registration";
    }
}
