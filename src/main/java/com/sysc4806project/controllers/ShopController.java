package com.sysc4806project.controllers;

import com.sysc4806project.models.Shop;
import com.sysc4806project.models.User;
import com.sysc4806project.repositories.ShopRepository;
import com.sysc4806project.services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ShopController {
    @Autowired
    private ShopService shopService;

    @GetMapping("/shops")
    public String getShopsPage(Model model) {
        model.addAttribute("shops", shopService.getAllShops());
        return "shops";
    }

    @GetMapping("/shops/shopsByName")
    public String getShopsPageByName(Model model, @RequestParam(defaultValue = "") String name) {
        model.addAttribute("shops", shopService.findByName(name));
        return "shopsByName";
    }




}
