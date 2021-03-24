package com.sysc4806project.controllers;

import com.sysc4806project.models.Shop;
import com.sysc4806project.services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MerchantController {
    @Autowired
    private ShopService shopService;

    @GetMapping("/merchant")
    public String merchantDashboard() {
        return "merchantDashboard";
    }

    @GetMapping("/merchant/shops")
    public String getMerchantShopPage() {
        return "merchantShops";
    }

    @GetMapping("/merchant/shops/add")
    public String getMerchantShops(Model model) {
        model.addAttribute("shop", new Shop());
        return "merchantShopsAdd";
    }

    @PostMapping("/merchant/shops/add")
    public String postMerchantShops(@ModelAttribute("shop")Shop shop) {
        shopService.addShop(shop);
        return "redirect:/merchant/shops";
    }
}
