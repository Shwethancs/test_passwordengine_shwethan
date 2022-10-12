package com.example.passwordengine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.example.passwordengine", "com.example.passwordengine.exception" })
public class PasswordengineApplication {

	public static void main(String[] args) {
		SpringApplication.run(PasswordengineApplication.class, args);
	}
}
