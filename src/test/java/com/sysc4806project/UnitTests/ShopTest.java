package com.sysc4806project.UnitTests;

import com.sysc4806project.models.Shop;
import com.sysc4806project.models.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ShopTest {

    Shop coffeeShop;
    User simon;
    List<String> categories;
    String[] scat = {"Coffee, Beans, Morning"};

    @org.junit.Before
    public void setUp() throws Exception {
        simon = new User("Simon", "Yacoub", "simon@bing.com", "S");
        coffeeShop = new Shop("beans", simon);
        categories = new ArrayList<String>(Arrays.asList(scat));


    }

    @org.junit.After
    public void tearDown() throws Exception {
        User simon = null;
        coffeeShop = null;
        categories = null;
        scat = null;
    }

    @org.junit.Test
    public void getName() {
        assertTrue(coffeeShop.getName().equals("beans"));
    }

    @org.junit.Test
    public void setName() {
        assertTrue(coffeeShop.getName().equals("beans"));
        coffeeShop.setName("BestBeans");
        assertTrue(coffeeShop.getName().equals("BestBeans"));
    }

    @org.junit.Test
    public void setCategories() {
        coffeeShop.setCategories(categories);
        assertTrue(coffeeShop.getCategories().equals(categories));
    }

    @org.junit.Test
    public void getCategories() {
        coffeeShop.setCategories(categories);
        assertTrue(coffeeShop.getCategories().equals(categories));
    }

    @org.junit.Test
    public void getOwner() {
        assertTrue(coffeeShop.getOwner().equals(simon));
    }

    @org.junit.Test
    public void setOwner() {
        User sandeep = new User("Sandeep", "Sadarsana", "sandeep@bing.com", "ss");
        assertTrue(coffeeShop.getOwner().equals(simon));
        coffeeShop.setOwner(sandeep);
        assertTrue(coffeeShop.getOwner().equals(sandeep));
    }
}