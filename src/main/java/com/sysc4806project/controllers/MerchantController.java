package com.sysc4806project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MerchantController {

    @GetMapping("/merchant")
    public String merchantDashboard() {
        return "merchantDashboard";
    }
}
