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
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
public class MerchantControllerTest {

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
    public void testGetShops() throws Exception {

        User user = new User("user","pass","f","l");
        userRepository.save(user);

        System.out.println("User "+user.getId());
        System.out.println("Repo "+userRepository.findById(user.getId()).get().getId());


        this.mockMvc.perform(get("/merchant/shops")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Shop #")))
                .andExpect(content().contentType("text/html;charset=UTF-8"));
    }

    @Test
    @WithMockUser
    @Transactional
    public void testMerchantGetProducts() throws Exception {

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


        this.mockMvc.perform(get("/merchant/products/{id}",3))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Products")))
                .andExpect(content().contentType("text/html;charset=UTF-8"));


    }



    @Test
    @WithMockUser
    @Transactional
    public void testMerchantShopsAdd() throws Exception {



        this.mockMvc.perform(get("/merchant/shops/add")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Shop Name")))

                .andExpect(content().contentType("text/html;charset=UTF-8"));

    }

    @Test
    @WithMockUser
    @Transactional
    public void testMerchantShopsAddPOST() throws Exception{
        System.out.println("Content " +content());
        System.out.println("String  "+ content().toString());
        System.out.println("Model  "+ model());



        this.mockMvc.perform(post("/merchant/shops/add").with(csrf()).secure( true )
                .param("name", "MyShop")
                .param("categoryList", "sports"))
                .andDo(print())
                .andExpect(redirectedUrl("/merchant/shops"));

        Shop shop = shopRepository.findByName("MyShop");
        System.out.println("Added Shop "+shop);
        System.out.println("Shop name: "+shop.getName()+" List: "+ shop.getCategoryList());

        assertThat(shop.getName()).isEqualTo("MyShop");
        assertThat(shop.getCategoryList()).contains("sports");

    }

    @Test
    @WithMockUser
    @Transactional
    public void testMerchantGetProductAdd() throws Exception {

        User user = new User("user","pass","f","l");
        userRepository.save(user);

        Shop shop = new Shop("Shop",user, new ArrayList<>());
        //shop.setId(1L);
        shopRepository.save(shop);

        System.out.println("Shop "+shop.getId());
        System.out.println("Repo "+shopRepository.findByName("Shop").getId());

        this.mockMvc.perform(get("/merchant/products/add/{id}",8)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Add a new Product")))

                .andExpect(content().contentType("text/html;charset=UTF-8"));
    }

    @Test
    @WithMockUser
    @Transactional
    public void testMerchantPostProductAdd() throws Exception{
        System.out.println("Content " +content());
        System.out.println("String  "+ content().toString());
        System.out.println("Model  "+ model());

        User user = new User("username","pass","f","l");
        userRepository.save(user);

        System.out.println("User "+user.getId());
        System.out.println("Repo "+userRepository.findById(user.getId()).get().getId());

        Shop shop = new Shop("Shop",user, new ArrayList<>());
        //shop.setId(1L);
        shopRepository.save(shop);

        System.out.println("Shop "+shop.getId());
        System.out.println("Repo "+shopRepository.findByName("Shop").getId());


        Product product = new Product("prod",shop,2,"desc","img",10);
        productRepository.save(product);

        System.out.println("Product "+product.getId());
        System.out.println("Repo "+productRepository.getById(product.getId()).getId());

        MockMultipartFile file = new MockMultipartFile("file", "original_filename.ext", null, "data".getBytes());


        this.mockMvc.perform(multipart("/merchant/products/add/{id}",3).file(file).with(csrf()).secure( true )
                .param("id", "1")
                .param("name", "Ball")
                .param("productImage","file"))
                .andDo(print())
        //.andExpect(redirectedUrl("/merchant/products/1"))
        ;

        product = productRepository.getById(2L);
        System.out.println("Added Product "+product);
        System.out.println("Product name: "+product.getName()+" Id: "+ product.getId());

        assertThat(product.getName()).isEqualTo("prod");
        assertThat(product.getId()==2);

    }

    @Test
    @WithMockUser
    @Transactional
    public void testGETDeleteProduct() throws Exception {

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


        this.mockMvc.perform(get("/merchant/products/delete/{id}/{productId}",6,3))
                .andDo(print())
                .andExpect(redirectedUrl("/merchant/products/6"));

        assertThat(productRepository.findById(3L)==null);

    }



    @Test
    @WithMockUser
    @Transactional
    public void testGETDeleteShop() throws Exception {

        User user = new User("username","pass","f","l");
        userRepository.save(user);

        System.out.println("User "+user.getId());
        System.out.println("Repo "+userRepository.findById(user.getId()).get().getId());

        Shop shop = new Shop("Shop",user, new ArrayList<>());
        shopRepository.save(shop);

        System.out.println("Shop "+shop.getId());
        System.out.println("Repo "+shopRepository.findByName("Shop").getId());


        this.mockMvc.perform(get("/merchant/shops/delete/{id}",9))
                .andDo(print())
                .andExpect(redirectedUrl("/merchant/shops"));

        assertThat(shopRepository.findByName("Shop")==null);

    }


    @Test
    @WithMockUser
    @Transactional
    public void testMerchantProductUpdate() throws Exception {

        User user = new User("username","pass","f","l");
        userRepository.save(user);

        System.out.println("User "+user.getId());
        System.out.println("Repo "+userRepository.findById(user.getId()).get().getId());

        Shop shop = new Shop("Shop",user, new ArrayList<>());
        //shop.setId(1L);
        shopRepository.save(shop);

        System.out.println("Shop "+shop.getId());
        System.out.println("Repo "+shopRepository.findByName("Shop").getId());

        Product product = new Product("prod",shop,2,"desc","img",10);
        productRepository.save(product);

        System.out.println("Product "+product.getId());
        System.out.println("Repo "+productRepository.getById(product.getId()).getId());


        this.mockMvc.perform(get("/merchant/products/update/{id}",4)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Update")))
                .andExpect(content().contentType("text/html;charset=UTF-8"));

    }


    @Test
    @WithMockUser
    @Transactional
    public void testMerchantShopsUpdate() throws Exception {

        User user = new User("username","pass","f","l");
        userRepository.save(user);

        System.out.println("User "+user.getId());
        System.out.println("Repo "+userRepository.findById(user.getId()).get().getId());

        Shop shop = new Shop("Shop",user, new ArrayList<>());
        //shop.setId(1L);
        shopRepository.save(shop);

        System.out.println("Shop "+shop.getId());
        System.out.println("Repo "+shopRepository.findByName("Shop").getId());

        this.mockMvc.perform(get("/merchant/shops/update/{id}",2)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Edit")))
                .andExpect(content().contentType("text/html;charset=UTF-8"));

    }

    @Test
    @WithMockUser
    @Transactional
    public void testMerchantShopsUpdatePOST() throws Exception{

        User user = new User("username","pass","f","l");
        userRepository.save(user);

        System.out.println("User "+user.getId());
        System.out.println("Repo "+userRepository.findById(user.getId()).get().getId());

        Shop shop = new Shop("Shop",user, new ArrayList<>());
        shopRepository.save(shop);

        System.out.println("Shop "+shop.getId());
        System.out.println("Repo "+shopRepository.findByName("Shop").getId());


        System.out.println("Added Shop "+shop);
        System.out.println("Shop name: "+shop.getName()+" List: "+ shop.getCategoryList());


        this.mockMvc.perform(post("/merchant/shops/update/{id}",4).with(csrf()).secure( true )
                .param("name", "MyShop")
                .param("category", ("sports")))
                .andDo(print())
                .andExpect(redirectedUrl("/merchant/shops/update/4"));

        System.out.println("NEW name: "+shop.getName()+" List: "+ shop.getCategoryList());


        assertThat(shop.getName()).isEqualTo("MyShop");
        assertThat(shop.getCategoryList()).contains("sports");

    }



}