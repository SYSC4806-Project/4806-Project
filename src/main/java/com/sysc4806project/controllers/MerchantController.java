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

        String imageUUID;
        String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/productImages";
        if (!file.isEmpty()) {
            imageUUID = file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDirectory, imageUUID);
            Files.write(fileNameAndPath, file.getBytes());
        } else {
            imageUUID = imgName;
        }


        productDTO.setParentShopId(id);
        productDTO.setImageName(imageUUID);

        Product product = productService.dtoConvertToProductObject(productDTO);
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

    @GetMapping("/merchant/products/update/{id}/{productId}")
    public String getUpdateProduct(@PathVariable Long id, @PathVariable Long productId, Model model) {
        System.out.println("GET "+productId+" and model: "+model);
        Product product = productService.getProductById(productId);
        ProductDTO productDTO = productService.dtoConvertFromProductObject(product);

        model.addAttribute("shop", shopService.getShopById(id).get());
        System.out.println("ProductDTO: "+productDTO);
        System.out.println(productDTO.getId()+" "+productDTO.getName());
        System.out.println(productDTO.getDescription());
        System.out.println("Image name: "+ productDTO.getImageName());
        System.out.println("Shop: "+shopService.getShopById(id).get());
        System.out.println(shopService.getShopById(id).get().getId()+" "+shopService.getShopById(id).get().getName());
        if(product!=null) {

            model.addAttribute("productDTO", productDTO);

            return "merchantProductsUpdate";
        } else {
            return "404page";
        }
    }

    @PostMapping("/merchant/products/update/{id}/{productId}")
    public String postUpdateShop(@PathVariable Long id,  @PathVariable Long productId, Model model,
                                 @ModelAttribute("productDTO")ProductDTO productDTO,
                                 @RequestParam("productImage")MultipartFile file
                                ) throws IOException {

        System.out.println("POST---------------------------");


        String imageUUID;
        String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/productImages";
        if (!file.isEmpty()) {

            imageUUID = file.getOriginalFilename();
            System.out.println("If not empty "+imageUUID);
            Path fileNameAndPath = Paths.get(uploadDirectory, imageUUID);
            Files.write(fileNameAndPath, file.getBytes());
        } else {
            imageUUID = productService.getProductById(productId).getImageName();
            System.out.println("If empty "+imageUUID);
        }

        productDTO.setImageName(imageUUID);
        productDTO.setId(productId);
        productDTO.setParentShopId(id);


//        System.out.println("ProductDTO "+productDTO);
//        System.out.println("ID "+productDTO.getId()+"  Shop ID:  "+productDTO.getParentShopId());
//        System.out.println("Img "+productDTO.getImageName());
//        System.out.println("Inventory "+productDTO.getInventoryNum());
//
//        System.out.println("Product1 "+productService.getProductById(productId));
//        System.out.println("ID "+productService.getProductById(productId).getId()+"  Shop ID:  "+productService.getProductById(productId).getParentShop().getId());
//        System.out.println("Img "+productService.getProductById(productId).getImageName());
//        System.out.println("Inventory "+productService.getProductById(productId).getInventoryNum());

        Product product = productService.dtoConvertToProductObject(productDTO);
//        System.out.println("Product2 "+product);
//        System.out.println("ID "+product.getId()+"  Shop ID:  "+product.getParentShop().getId());
//        System.out.println("Img "+product.getImageName());
//        System.out.println("Inventory "+product.getInventoryNum());


//        System.out.println("ImageUUID name: "+imageUUID);


        productService.addProduct(product);
        return "redirect:/merchant/products/{id}";
    }
}
