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

    public AppController(ClientService service, UserService userService, SecurityService securityService) {
        this.service = service;
        this.userService = userService;
        this.securityService = securityService;
    }
    @ModelAttribute("page")
    public String messages() {
        return "5";
    }
    @GetMapping({"/"})
    public String signup(Model model) {
        List<Client> all = service.getAll(1, 10);
        List<User> userServiceAll = userService.getAll();
        model.addAttribute("clients", all);
        model.addAttribute("size", 10);
        model.addAttribute("users", userServiceAll);
        return "index";
    }

    @GetMapping({"/pageLeft/{page}/{size}"})
    public String pageLeft(Model model,
                           @PathVariable("page") String page,
                            @PathVariable("size")String size) {
        int pageMinus = Integer.parseInt(page) - 1;
        List<Client> all = service.getAll(pageMinus, Integer.parseInt(size));
        model.addAttribute("clients", all);
        model.addAttribute("page", pageMinus);
        model.addAttribute("size", size);
        return "index";
    }

    @GetMapping({"/pageRight/{page}/{size}"})
    public String pageRight(Model model,
            @PathVariable("page") String page,
                            @PathVariable("size") String size) {
        int pagePlus = Integer.parseInt(page) + 1;
        List<Client> all = service.getAll(pagePlus, Integer.parseInt(size));
        model.addAttribute("clients", all);
        model.addAttribute("page", pagePlus);
        model.addAttribute("size", size);
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
