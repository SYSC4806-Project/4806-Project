package com.sysc4806project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {

    @GetMapping
    public String dashboard () {
        return "dashboard";
    }
}