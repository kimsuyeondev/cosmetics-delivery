package com.hello.cosmetics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class CosmeticsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CosmeticsApplication.class, args);
	}

}
