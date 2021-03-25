package com.sysc4806project.UnitTests;

import com.sysc4806project.models.Shop;
import com.sysc4806project.models.User;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class UserTest {

    User testUser;

    @org.junit.Before
    public void setUp() throws Exception {
        testUser = new User("sandeeplayam", "myPassword", "Sandeep", "Layam");
    }

    @org.junit.After
    public void tearDown() throws Exception {
        User testUser = null;
    }

    @org.junit.Test
    public void getFirstNameTest() {
        assertEquals("Sandeep", testUser.getFirstName());
    }

    @org.junit.Test
    public void setFirstNameTest() {
        testUser.setFirstName("Joe");
        assertEquals("Joe", testUser.getFirstName());
    }

    @org.junit.Test
    public void getLastNameTest() {
        assertEquals("Layam", testUser.getLastName());
    }

    @org.junit.Test
    public void setLastNameTest() {
        testUser.setLastName("Johnson");
        assertEquals("Johnson", testUser.getLastName());
    }

    @org.junit.Test
    public void getPasswordTest() {
        assertEquals("myPassword", testUser.getPassword());
    }

    @org.junit.Test
    public void setPasswordTest() {
        testUser.setPassword("newPassword");
        assertEquals("newPassword", testUser.getPassword());
    }

    @org.junit.Test
    public void addShopTest() {
        Shop testShop1 = new Shop("Grocery Shop", testUser, new ArrayList<>());
        testUser.addShop(testShop1);
        assertTrue(testUser.getAllShops().contains(testShop1));

    }

    @org.junit.Test
    public void getAllShopsTest() {
        Shop testShop1 = new Shop("Grocery Shop", testUser, new ArrayList<>());
        testUser.addShop(testShop1);
        ArrayList testList = new ArrayList<Shop>();
        testList.add(testShop1);
        assertTrue(testUser.getAllShops().equals(testList));
    }
}