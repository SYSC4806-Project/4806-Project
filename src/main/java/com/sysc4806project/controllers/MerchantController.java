package com.sysc4806project.controllers;

import com.sysc4806project.dto.ProductDTO;
import com.sysc4806project.models.Product;
import com.sysc4806project.models.Shop;
import com.sysc4806project.models.User;
import com.sysc4806project.services.ProductService;
import com.sysc4806project.services.ShopService;
import com.sysc4806project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Controller
public class MerchantController {
    @Autowired
    private ShopService shopService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @GetMapping("/merchant")
    public String getMerchantDashboard() {
        return "merchantDashboard";
    }

    //Shop Endpoints
    @GetMapping("/merchant/shops")
    public String getMerchantShopPage( Model model) {
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
            model.addAttribute("categoryList",shop.get().getCategoryList());
            return "merchantSpecificShop";
        } else {
            return "404page";
        }
    }

    @PostMapping("/merchant/shops/update/{id}")
    public String postUpdateShop(@PathVariable Long id, Model model,
                                 @ModelAttribute("shop")Shop shop,
                                 @ModelAttribute("category") String category,
                                 @ModelAttribute("oldCategory") String oldCategory,
                                 @ModelAttribute("name") String name ) {
        Optional<Shop> shopSir = shopService.getShopById(id);
        Shop shop1 = shopSir.get();
        shop =shopService.getShopById(id).get();

        if(!category.isEmpty()){
            List<String> categories = (shop.getCategoryList());
            categories.add(category);
            shop.setCategoryList(categories);
        }

        if(!oldCategory.isEmpty()){
            List<String> categories = (shop.getCategoryList());
            categories.remove(oldCategory);
            shop.setCategoryList(categories);
        }

        if(!name.isEmpty()) {
            shop.setName(name);
        }
        shopService.addShop(shop);
        return "redirect:/merchant/shops/update/{id}";
    }

    //Product Endpoints
    @GetMapping("/merchant/products/{id}")
    public String getMerchantProductPage(@PathVariable Long id, Model model) {
        model.addAttribute("products", productService.getAllProductsWithinShop(id)); //this id is shop id
        model.addAttribute("shop", shopService.getShopById(id).get());
        return "merchantProducts";
    }

    @GetMapping("/merchant/products/add/{id}")
    public String getProductAdd(@PathVariable Long id, Model model) {
        model.addAttribute("productDTO", new ProductDTO());
        model.addAttribute("shop", shopService.getShopById(id).get());
        return "merchantProductsAdd";
    }

    @PostMapping("/merchant/products/add/{id}")
    public String postProductAdd(@PathVariable Long id, @ModelAttribute("productDTO")ProductDTO productDTO,
                                 @RequestParam("productImage")MultipartFile file,
                                 @RequestParam("imgName")String imgName) throws IOException {
        productDTO.setParentShopId(id);
        Product product = productService.dtoConvertToProductObject(productDTO);
        String imageUUID;
        String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/productImages";
        if (!file.isEmpty()) {
            imageUUID = file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDirectory, imageUUID);
            Files.write(fileNameAndPath, file.getBytes());
        } else {
            imageUUID = imgName;
        }
        product.setImageName(imageUUID);
        productService.addProduct(product);
        return "redirect:/merchant/products/{id}";
    }

    @GetMapping("/merchant/products/delete/{id}/{productId}")
    public String deleteProduct(@PathVariable Long id, @PathVariable Long productId) {
        // id path variable represents shop id
        // productId path variable represents product id
        productService.removeProductById(productId);
        return "redirect:/merchant/products/{id}";
    }

    @GetMapping("/merchant/products/update/{id}")
    public String getUpdateProduct(@PathVariable Long id, Model model) {
        System.out.println("GET "+id+" and model: "+model);
        Product product = productService.getProductById(id);
        model.addAttribute("shop", shopService.getShopById(id).get());
        System.out.println("Product: "+product);
        System.out.println(product.getId()+" "+product.getName());
        System.out.println(product.getDescription());
        System.out.println("Shop ID: "+model.getAttribute("shop"));
        if(product!=null) {

            model.addAttribute("product", product);

            return "merchantProductsUpdate";
        } else {
            return "404page";
        }
    }
}
