package com.sysc4806project.UnitTests;


import com.sysc4806project.models.Product;
import com.sysc4806project.models.Shop;
import com.sysc4806project.models.User;
import com.sysc4806project.repositories.ProductRepository;
import com.sysc4806project.repositories.ShopRepository;
import com.sysc4806project.repositories.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    private Shop daShop;
    private Product ice;
    private User tester;


    @Before
    public void setUp() {

        ice = new Product("Ice",daShop,10000,"Coool","ice",1);
        ice = productRepository.save(ice);


        tester = new User("Sim", "s123", "Simon", "Yacoub");
        tester = userRepository.save(tester);



        daShop = new Shop("daShop", tester, new ArrayList<>());
        daShop = shopRepository.save(daShop);

    }

    @After
    public void tearDown()  {
        ice = null;
        daShop = null;
        tester = null;

        shopRepository.deleteAll();
        userRepository.deleteAll();
        productRepository.deleteAll();
    }

    @Test
    public void testCreateProduct() {

        Product savedProduct = productRepository.save(ice);
        assertThat(savedProduct.getId()).isGreaterThan(0);

    }

    @Test
    public void testGetById() {

        Product product = productRepository.getById(ice.getId());
        assertThat(product.getName()).isEqualTo(ice.getName());
    }

    @Test
    public void testFindByParentShopId() {

        List<Product> products = productRepository.findByParentShopId(daShop.getId());
        assertThat(products.containsAll(daShop.getProducts()));
    }

    @Test
    public void testShowProducts() {
        List<Product> products = productRepository.findAll();
        assertThat(products).size().isGreaterThan(0);
    }



    @Test
    public void testSaveProduct() {

        Product lava = productRepository.getById(ice.getId());
        lava.setName("Lava");

        productRepository.save(lava);

        Product updatedLava = productRepository.getById(lava.getId());

        assertThat(updatedLava.getName()).isEqualTo(lava.getName());

    }

    @Test
    public void testDeleteProduct() {

        Product product = productRepository.getById(ice.getId());

        productRepository.delete(product);

        Product deletedProduct = productRepository.getById(product.getId());

        assertThat(deletedProduct).isNull();

    }

}
