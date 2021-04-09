package com.sysc4806project.IntegrationTests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ShopControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void testShopDisplayPage() throws Exception {
        this.mockMvc.perform(get("/shops")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("E-commerce")))
                .andExpect(content().contentType("text/html;charset=UTF-8"));
    }

    @Test
    @WithMockUser
    public void testShopSearchByCategory() throws Exception {
        this.mockMvc.perform(get("/shops/shopsByCategory")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("E-commerce")))
                .andExpect(content().contentType("text/html;charset=UTF-8"));
    }

    @Test
    @WithMockUser
    public void testShopSearchByName() throws Exception {
        this.mockMvc.perform(get("/shops/shopsByName")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("E-commerce")))
                .andExpect(content().contentType("text/html;charset=UTF-8"));
    }


}
