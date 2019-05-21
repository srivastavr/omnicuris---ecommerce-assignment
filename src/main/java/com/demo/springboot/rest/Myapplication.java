package com.demo.springboot.rest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.demo.springboot.rest.model.Product;
import com.demo.springboot.rest.service.Myservice;

@SpringBootApplication
public class Myapplication {

	public static void main(String[] args) {
		SpringApplication.run(Myapplication.class, args);
	}

	@Bean
	CommandLineRunner runner(Myservice productService) {
		return args -> {
			productService.save(new Product(1, "product 1", 500));
			productService.save(new Product(2, "product 2", 500));
			productService.save(new Product(3, "product 3", 100));
			productService.save(new Product(4, "product 4", 0));
			productService.save(new Product(5, "product 5", 5));

		};
	}
}