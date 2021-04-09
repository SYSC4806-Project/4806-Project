package com.sysc4806project.controllers;

import com.sysc4806project.dto.ProductDTO;
import com.sysc4806project.models.Product;
import com.sysc4806project.models.Shop;
import com.sysc4806project.models.User;
import com.sysc4806project.repositories.ShopRepository;
import com.sysc4806project.services.ProductService;
import com.sysc4806project.services.ShopService;
import com.sysc4806project.services.UserService;
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

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @GetMapping("/shops")
    public String getShopsPage(Model model) {
        model.addAttribute("shops", shopService.getAllShops());
        return "shops";
    }

    @GetMapping("/products/{id}")
    public String getShopProductsPage(@PathVariable Long id, Model model) {
        model.addAttribute("products", productService.getAllProductsWithinShop(id)); //this id is shop id
        model.addAttribute("shop", shopService.getShopById(id).get());
        return "products";
    }

    @GetMapping("/products/{id}/{productId}")
    public String getProductPage(@PathVariable Long id,@PathVariable Long productId, Model model) {
        Product product = productService.getProductById(productId);
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setDescription(product.getDescription());
        productDTO.setImageName(product.getImageName());
        productDTO.setInventoryNum(product.getInventoryNum());
        System.out.println("ProductDTO update " + productDTO.getId());
        model.addAttribute("productDTO", productDTO);
        model.addAttribute("shop", shopService.getShopById(id).get());
        return "product";
    }

    @PostMapping("/products/{id}/{productId}")
    public String postProductPage(@PathVariable Long id,@PathVariable Long productId, Model model) {

        return "redirect:/products/{id}";
    }
    
    @GetMapping("/shops/shopsByName")
    public String getShopsPageByName(Model model, @RequestParam(defaultValue = "") String name) {
        model.addAttribute("shops", shopService.findByName(name));
        return "shops";
    }

    @GetMapping("/shops/shopsByCategory")
    public String getShopsPageByCategory (Model model, @RequestParam(defaultValue = "") String category_list) {
        model.addAttribute("shops", shopService.findByCategory(category_list));
        return "shops";
    }



}
