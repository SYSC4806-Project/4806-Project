package com.sysc4806project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginRoleController {

    @RequestMapping("/loginRole")
    public String defaultAfterLogin(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_CUSTOMER")) {
            return "redirect:/shops";
        }
        return "redirect:/merchant";
    }

}
