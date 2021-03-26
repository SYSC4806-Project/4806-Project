package com.sysc4806project.IntegrationTests;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sysc4806project.controllers.AppController;
import com.sysc4806project.models.Shop;
import com.sysc4806project.repositories.ShopRepository;
import com.sysc4806project.services.ShopService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MerchantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShopService shopService;

    @Test
    @WithMockUser
    public void testMerchantDashboard() throws Exception {
        this.mockMvc.perform(get("/merchant")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Easily manage your shops")))
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

//    @Test
//    @WithMockUser
//    public void testGETDelete() throws Exception {
//
//        this.mockMvc.perform( MockMvcRequestBuilders
//                .get("/merchant/shops/delete/{id}")
//                .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.shopId").value(null));
//
////        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
////                .andExpect(content().string(containsString("Welcome to Sysc 4806 project home page.")))
////                .andExpect(content().contentType("text/html;charset=UTF-8"));
//    }
    public void testMerchantShopsAdd() throws Exception {
        this.mockMvc.perform(get("/merchant/shops/add")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Shop Name")))
                .andExpect(content().contentType("text/html;charset=UTF-8"));
    }


//    @Test
//    @WithMockUser
//    public void testMerchantShops() throws Exception {
//        this.mockMvc.perform(get("/merchant/shops")).andDo(print()).andExpect(status().isOk())
//                .andExpect(content().string(containsString("Shop #")))
//                .andExpect(content().contentType("text/html;charset=UTF-8"));
//    }








}
