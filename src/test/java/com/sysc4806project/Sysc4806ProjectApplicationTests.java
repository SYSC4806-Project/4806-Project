package com.sysc4806project;


import com.sysc4806project.IntegrationTests.AppControllerTest;
import com.sysc4806project.IntegrationTests.LoginControllerTests;
import com.sysc4806project.UnitTests.ShopRepositoryTest;
import com.sysc4806project.UnitTests.ShopTest;
import com.sysc4806project.UnitTests.UserRepositoryTest;
import com.sysc4806project.UnitTests.UserTest;
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
		LoginControllerTests.class
})
@SpringBootTest
public class Sysc4806ProjectApplicationTests {


	@Test
	public void contextLoads() {
			}

//	@Test
//	public void main() {
//
//		//Result result = JUnitCore.runClasses(Sysc4806ProjectApplicationTests.class);
//
////		for (Failure failure : result.getFailures()) {
////			System.out.println(failure.toString());
////		}
////
////		System.out.println(result.wasSuccessful());
//
//	}
}

