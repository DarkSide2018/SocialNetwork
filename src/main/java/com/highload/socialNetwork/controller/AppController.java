package com.highload.socialNetwork.controller;

import com.highload.socialNetwork.model.Client;
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

import java.util.List;

@Controller
public class AppController {
    private final ClientService service;
    private final UserService userService;
    private final SecurityService securityService;
    private  int currentPage = 1;
    private  int size = 10;

    public AppController(ClientService service, UserService userService, SecurityService securityService) {
        this.service = service;
        this.userService = userService;
        this.securityService = securityService;
    }
    @ModelAttribute("page")
    public Integer page() {
        return currentPage;
    }
    @ModelAttribute("size")
    public Integer size() {
        return size;
    }
    @GetMapping({"/"})
    public String signup(Model model) {
        List<Client> all = service.getAll(currentPage, size);
        List<User> userServiceAll = userService.getAll();
        model.addAttribute("clients", all);
        model.addAttribute("users", userServiceAll);
        return "index";
    }

    @GetMapping({"/pageLeft"})
    public String pageLeft(Model model) {
        currentPage = currentPage-1;
        List<Client> all = service.getAll(currentPage,size);
        model.addAttribute("clients", all);
        model.addAttribute("size", size);
        return "index";
    }

    @GetMapping({"/pageRight"})
    public String pageRight(Model model) {
        currentPage = currentPage + 1;
        List<Client> all = service.getAll(currentPage, size);
        model.addAttribute("clients", all);
        return "index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getByUserId(id));
        return "registration";
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

    @PostMapping("/adduser")
    public String registration(@ModelAttribute("user") User userForm) {
        userService.save(userForm);
        securityService.autoLogin(userForm.getName(), userForm.getPassword());
        return "redirect:/";
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

}
