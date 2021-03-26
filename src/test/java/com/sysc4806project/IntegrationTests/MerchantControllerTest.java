package com.sysc4806project.IntegrationTests;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sysc4806project.controllers.AppController;
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
import org.springframework.web.context.WebApplicationContext;


import javax.servlet.ServletContext;

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
    public void testMerchantShopsAdd() throws Exception {
        this.mockMvc.perform(get("/merchant/shops/add")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Shop Name")))
                .andExpect(content().contentType("text/html;charset=UTF-8"));
    }


    @Test
    @WithMockUser
    public void testMerchantShops() throws Exception {
        this.mockMvc.perform(get("/merchant/shops")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Shop #")))
                .andExpect(content().contentType("text/html;charset=UTF-8"));
    }








}
