package com.example.overtime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OvertimeApplication {
	public static void main(String[] args) {
		SpringApplication.run(OvertimeApplication.class, args);
		System.out.println("SUCCESSED DEPLOY SPRINGBOOT");
		
	// @EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class});
	}

}
