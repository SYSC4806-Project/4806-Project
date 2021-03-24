package com.sysc4806project;

import com.sysc4806project.repositories.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class Sysc4806ProjectApplication {





	public static void main(String[] args) {
		SpringApplication.run(Sysc4806ProjectApplication.class, args);




	}

}
