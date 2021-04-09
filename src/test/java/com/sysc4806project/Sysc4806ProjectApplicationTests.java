package com.sysc4806project;


import com.sysc4806project.IntegrationTests.AppControllerTest;
import com.sysc4806project.IntegrationTests.ShopControllerTest;
import com.sysc4806project.IntegrationTests.LoginControllerTest;
import com.sysc4806project.IntegrationTests.MerchantControllerTest;
import com.sysc4806project.IntegrationTests.UserRegistrationControllerTest;
import com.sysc4806project.UnitTests.*;
import com.sysc4806project.models.Product;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		UserRepositoryTest.class,
		ShopTest.class,
		UserTest.class,
		AppControllerTest.class,
		ShopRepositoryTest.class,
		ShopControllerTest.class,
		LoginControllerTest.class,
		UserRegistrationControllerTest.class,
		MerchantControllerTest.class,
		ProductRepositoryTest.class,
		ProductTest.class
})
@SpringBootTest
public class Sysc4806ProjectApplicationTests {
	
	@Test
	public void contextLoads() {
	}

}

