package com.sysc4806project.controllers;

import com.sysc4806project.models.Shop;
import com.sysc4806project.repositories.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class AppController {

    @Autowired
    ShopRepository shopRepo;

    @GetMapping("/")
    public String index () {

//        System.out.println("Categories ");
//        Shop sh1 = shopRepo.findByName("Boom");
//        List<Shop> sh = shopRepo.findByCategories("sport");
//        System.out.println(sh);
//
//        System.out.println(sh1.getName());
//        System.out.println(sh1.getCategories().add("sport"));
//        shopRepo.save(sh1);
////        sh = shopRepo.findByCategories("sport");
//        long longnum = 1;
//        List<Shop> sh = shopRepo.findByOwnerId(longnum);
//        System.out.println(sh);
//        System.out.println(sh.get(0));
        return "index";
    }
}
