
package com.sysc4806project.UnitTests;


import com.sysc4806project.models.Shop;
import com.sysc4806project.models.User;
import com.sysc4806project.repositories.ShopRepository;
import com.sysc4806project.repositories.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@DataJpaTest
public class ShopRepositoryTest {

    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private UserRepository userRepository;

    private Shop daShop;
    private User tester;

    @Autowired
    private TestEntityManager entityManager;




    @Before
    public void setUp() {
        tester = new User("Sim", "s123", "Simon", "Yacoub");
        userRepository.save(tester);
        daShop = new Shop("daShop", tester, new ArrayList<>());
        shopRepository.save(daShop);

    }

    @After
    public void tearDown() throws Exception {
        tester = null;
        daShop = null;
        shopRepository.deleteAll();
    }

    @Test
    public void testCreateShop() {

        Shop savedShop = shopRepository.save(daShop);

        assertThat(savedShop.getId()).isGreaterThan(0);

    }

    @Test
    public void testFindByName() {

        Shop shop = shopRepository.findByName(daShop.getName());


        assertThat(shop.getName()).isEqualTo(daShop.getName());
    }

//    @Test
//    public void testFindByCategory() {
//
//        List<Shop> shops = shopRepository.findByCategory("sports");
//
//
//        assertThat(shop.getName()).isEqualTo(daShop.getName());
//    }


    @Test
    public void testShowShops() {
        List<Shop> shops = (List<Shop>) shopRepository.findAll();
        assertThat(shops).size().isGreaterThan(0);
    }



    @Test
    public void testSaveShop() {

        Shop shop = shopRepository.findByName(daShop.getName());
        shop.setName("The Underworld");

        shopRepository.save(shop);

        Shop updatedShop = shopRepository.findByName(shop.getName());

        assertThat(updatedShop.getName()).isEqualTo(shop.getName());

    }

    @Test
    public void testDeleteShop() {

        Shop shop = shopRepository.findByName(daShop.getName());

        shopRepository.delete(shop);

        Shop deletedShop = shopRepository.findByName(daShop.getName());

        assertThat(deletedShop).isNull();

    }


}
