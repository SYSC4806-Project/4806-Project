package com.sysc4806project.UnitTests;

import com.sysc4806project.models.Shop;
import com.sysc4806project.models.User;
import com.sysc4806project.repositories.ShopRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ShopRepositoryTest {
    @Autowired
    private ShopRepository shopRepository;

    private Shop leShop;

    @Autowired
    private TestEntityManager entityManager;

    User simon;
    @Before
    public void setUp() throws Exception {
        simon = new User("Simon", "Yacoub", "simon@bing.com", "s123");
        leShop = new Shop("leShop", simon);
    }

    @After
    public void tearDown() throws Exception {
        simon = null;
        leShop = null;

    }

    @Test
    public void saveShop() {
        Shop savedShop = this.entityManager.persistAndFlush(leShop);
        assertThat(savedShop.getName()).isEqualTo("leShop");

    }

//    @Test
//    @Rollback(false)
//    public void getShops(){
//
//        entityManager.persist(leShop);
//        Shop aShop = shopRepository.findByName("leShop");
//        assertTrue(aShop != null);
//    }
}