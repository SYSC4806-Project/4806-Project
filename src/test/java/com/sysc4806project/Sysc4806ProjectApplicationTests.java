package com.sysc4806project;

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
		UserTest.class
})
public class Sysc4806ProjectApplicationTests {

	@Test
	public void contextLoads() {
	}

}

