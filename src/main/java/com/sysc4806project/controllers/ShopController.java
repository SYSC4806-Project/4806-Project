package com.sysc4806project.controllers;

import com.sysc4806project.models.Shop;
import com.sysc4806project.repositories.ShopRepository;
import com.sysc4806project.services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping(path="/shops")
public class ShopController {
    @Autowired
    private ShopService shopService;

    @GetMapping
    public String shops() {
        return "shops";
    }


}
