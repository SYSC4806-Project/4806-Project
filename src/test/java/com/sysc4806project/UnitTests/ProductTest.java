package com.sysc4806project.UnitTests;

import com.sysc4806project.models.Product;
import com.sysc4806project.models.Shop;
import com.sysc4806project.models.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ProductTest {

    Shop dankShop;
    User cheech;
    Product purkle;

    @org.junit.Before
    public void setUp() throws Exception {
        cheech = new User("Cheech", "Kong", "cheech@chong.com", "K");
        dankShop = new Shop("Dank Shop", cheech, new ArrayList<>());
        purkle = new Product("Purkle",dankShop,999,"That purkle urkle","purp",9);


    }

    @org.junit.After
    public void tearDown() throws Exception {
        cheech = null;
        dankShop = null;
        purkle = null;
    }

    @org.junit.Test
    public void testGetName() {
        assertTrue(purkle.getName().equals("Purkle"));

    }

    @org.junit.Test
    public void testSetName() {
        purkle.setName("Sativaction");
        assertTrue(purkle.getName().equals("Sativaction"));
    }

    @org.junit.Test
    public void testGetParentShop() {
        assertTrue(purkle.getParentShop().equals(dankShop));

    }

    @org.junit.Test
    public void testSetParentShop() {
        Shop midShop = new Shop("Mid Shop", cheech, new ArrayList<>());
        purkle.setParentShop(midShop);
        assertTrue(purkle.getParentShop().equals(midShop));
    }

    @org.junit.Test
    public void testGetPrice() {
        assertTrue(purkle.getPrice()==(999));

    }

    @org.junit.Test
    public void testSetPrice() {
        purkle.setPrice(20);
        assertTrue(purkle.getPrice() == (20));
    }

    @org.junit.Test
    public void testGetDescription() {
        assertTrue(purkle.getDescription().equals("That purkle urkle"));

    }

    @org.junit.Test
    public void testSetDescription() {
        purkle.setDescription("meh");
        assertTrue(purkle.getDescription().equals("meh"));
    }

    @org.junit.Test
    public void testGetImageName() {
        assertTrue(purkle.getImageName().equals("purp"));

    }

    @org.junit.Test
    public void testSetImageName() {
        purkle.setImageName("meh");
        assertTrue(purkle.getImageName().equals("meh"));
    }

    @org.junit.Test
    public void testGetInventoryNum() {
        assertTrue(purkle.getInventoryNum()==(9));

    }

    @org.junit.Test
    public void testSetInventoryNum() {
        purkle.setInventoryNum(3);
        assertTrue(purkle.getInventoryNum() == (3));
    }

}
