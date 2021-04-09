package com.sysc4806project.controllers;

import com.sysc4806project.dto.UserRegistrationDto;
import com.sysc4806project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
    @Autowired
    private UserService userService;

    @PostMapping
    public String registerUserAccount(@ModelAttribute("userDTO")UserRegistrationDto registrationDto) {
        userService.registerUser(registrationDto);
        return "login";
    }

    @GetMapping
    public String registrationForm(Model model) {
        model.addAttribute("userDTO", new UserRegistrationDto());
//        boolean userRole = false;
//        model.addAttribute("userRole", userRole);
        return "registration";
    }
}