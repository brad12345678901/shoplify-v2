package com.shoplify.ecommerce_springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EcommerceSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceSpringbootApplication.class, args);
	}

}
