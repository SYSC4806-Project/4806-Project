package com.sysc4806project.controllers;

import com.sysc4806project.models.Shop;
import com.sysc4806project.models.User;
import com.sysc4806project.services.ShopService;
import com.sysc4806project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class MerchantController {
    @Autowired
    private ShopService shopService;

    @Autowired
    private UserService userService;

    @GetMapping("/merchant")
    public String merchantDashboard() {
        return "merchantDashboard";
    }

    @GetMapping("/merchant/shops")
    public String getMerchantShopPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());
        model.addAttribute("shops", shopService.getAllMerchantShops(user.getId()));
        return "merchantShops";
    }

    @GetMapping("/merchant/shops/add")
    public String getMerchantShops(Model model) {
        model.addAttribute("shop", new Shop());
        return "merchantShopsAdd";
    }

    @PostMapping("/merchant/shops/add")
    public String postMerchantShops(@ModelAttribute("shop")Shop shop) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        shop.setOwner(userService.findByUsername(auth.getName()));
        shopService.addShop(shop);
        return "redirect:/merchant/shops";
    }

    @GetMapping("/merchant/shops/delete/{id}")
    public String deleteShop(@PathVariable Long id) {
        shopService.removeShopById(id);
        return "redirect:/merchant/shops";
    }

    @GetMapping("/merchant/shops/update/{id}")
    public String getUpdateShop(@PathVariable Long id, Model model) {
        Optional<Shop> shop = shopService.getShopById(id);
        if(shop.isPresent()) {
            model.addAttribute("shop", shop.get());
            //System.out.println(shop.get().getCategoryList());
            return "merchantSpecificShop";
        } else {
            return "404page";
        }
    }

//    @PostMapping("/merchant/shops/update/{id}")
//    public String postUpdateShop(@PathVariable Long id, Model model)
}
