package com.sysc4806project.IntegrationTests;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sysc4806project.controllers.AppController;
import com.sysc4806project.models.Product;
import com.sysc4806project.models.Shop;
import com.sysc4806project.models.User;
import com.sysc4806project.repositories.ShopRepository;
import com.sysc4806project.repositories.UserRepository;
import com.sysc4806project.repositories.ProductRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockServletContext;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.WebApplicationContext;


import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ShopControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @After
    public void tearDown()  {

        System.out.println("After Baby");
        userRepository.deleteAll();
    }

    @Test
    @WithMockUser
    public void testShopDisplayPage() throws Exception {
        this.mockMvc.perform(get("/shops")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("E-commerce")))
                .andExpect(content().contentType("text/html;charset=UTF-8"));
    }

    @Test
    @WithMockUser
    @Transactional
    public void testGetProducts() throws Exception {

        User user = new User("username","pass","f","l");
        userRepository.save(user);

        System.out.println("User "+user.getId());
        System.out.println("Repo "+userRepository.findById(user.getId()).get().getId());

        Shop shop = new Shop("Shop",user, new ArrayList<>());
        shopRepository.save(shop);

        System.out.println("Shop "+shop.getId());
        System.out.println("Repo "+shopRepository.findByName("Shop").getId());


        Product product = new Product("prod",shop,2,"desc","img",10);
        productRepository.save(product);

        System.out.println("Product "+product.getId());
        System.out.println("Repo "+productRepository.getById(product.getId()).getId());


        this.mockMvc.perform(get("/merchant/products/{id}",4))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Products")))
                .andExpect(content().contentType("text/html;charset=UTF-8"));

    }

    @Test
    @WithMockUser
    @Transactional
    public void testGETaProduct() throws Exception {

        User user = new User("username","pass","f","l");
        userRepository.save(user);

        System.out.println("User "+user.getId());
        System.out.println("Repo "+userRepository.findById(user.getId()).get().getId());

        Shop shop = new Shop("Shop",user, new ArrayList<>());
        shopRepository.save(shop);

        System.out.println("Shop "+shop.getId());
        System.out.println("Repo "+shopRepository.findByName("Shop").getId());

        Product product = new Product("prod",shop,2,"desc","img",10);
        productRepository.save(product);

        System.out.println("Product "+product.getId());
        System.out.println("Repo "+productRepository.getById(product.getId()).getId());


        this.mockMvc.perform(get("/products/{id}/{productId}",2,2))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("prod")));


    }

    @Test
    @WithMockUser
    @Transactional
    public void testPOSTaProduct() throws Exception {

        User user = new User("username","pass","f","l");
        userRepository.save(user);

        System.out.println("User "+user.getId());
        System.out.println("Repo "+userRepository.findById(user.getId()).get().getId());

        Shop shop = new Shop("Shop",user, new ArrayList<>());
        shopRepository.save(shop);

        System.out.println("Shop "+shop.getId());
        System.out.println("Repo "+shopRepository.findByName("Shop").getId());

        Product product = new Product("prod",shop,2,"desc","img",10);
        productRepository.save(product);

        System.out.println("Product "+product.getId());
        System.out.println("Repo "+productRepository.getById(product.getId()).getId());


        this.mockMvc.perform(post("/products/{id}/{productId}",3,2).with(csrf()).secure( true ))
                .andDo(print())
                .andExpect(redirectedUrl("/products/3"));


    }

    @Test
    @WithMockUser
    @Transactional
    public void testShopSearchByCategory() throws Exception {

        User user = new User("username","pass","f","l");
        userRepository.save(user);

        System.out.println("User "+user.getId());
        System.out.println("Repo "+userRepository.findById(user.getId()).get().getId());

        Shop shop = new Shop("Shop",user, new ArrayList<String>( Arrays.asList("sports", "art")));
        shopRepository.save(shop);

        System.out.println("Shop "+shop.getId());
        System.out.println("Repo "+shopRepository.findByName("Shop").getId());

        Product product = new Product("prod",shop,2,"desc","img",10);
        productRepository.save(product);

        System.out.println("Product "+product.getId());
        System.out.println("Repo "+productRepository.getById(product.getId()).getId());



        this.mockMvc.perform(get("/products/{id}/{productId}",1,1))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("prod")))
                .andExpect(content().contentType("text/html;charset=UTF-8"));
    }

    @Test
    @WithMockUser
    @Transactional
    public void testShopSearchByName() throws Exception {


        this.mockMvc.perform(get("/shops/shopsByName")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("E-commerce")))
                .andExpect(content().contentType("text/html;charset=UTF-8"));
    }


}
