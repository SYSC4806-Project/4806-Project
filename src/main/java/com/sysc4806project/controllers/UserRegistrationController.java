package com.sysc4806project.controllers;

import com.sysc4806project.dto.UserRegistrationDto;
import com.sysc4806project.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    private UserService userService;

    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @PostMapping
    @ResponseBody
    public String registerUserAccount(@ModelAttribute("user")UserRegistrationDto registrationDto) {
        userService.registerUser(registrationDto);
        return "redirect:/registration?success";
    }

    @GetMapping
    public String registrationForm() {
        return "registration";
    }
}
