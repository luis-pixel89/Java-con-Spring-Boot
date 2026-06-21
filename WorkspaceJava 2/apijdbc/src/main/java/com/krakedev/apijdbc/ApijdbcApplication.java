package com.krakedev.apijdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.krakedev.jdbc.videojuegos.controller", "com.krakedev.jdbc.videojuegos.services"})
public class ApijdbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApijdbcApplication.class, args);
	}

}
