package com.sysc4806project.controllers;

import com.sysc4806project.models.Shop;
import com.sysc4806project.repositories.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/shops")
public class ShopController {

    @Autowired
    private ShopRepository shopRepository;

    @GetMapping
    public @ResponseBody
    Iterable<Shop> getAllShops() {
        // This returns a JSON or XML with the users
        return shopRepository.findAll();
    }

//    @GetMapping
//    public String shop() {
//        return "shops";
//    }
}
