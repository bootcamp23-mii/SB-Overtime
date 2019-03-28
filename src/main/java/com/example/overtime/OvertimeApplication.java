package com.example.overtime;

import com.example.overtime.property.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class OvertimeApplication {
	public static void main(String[] args) {
		SpringApplication.run(OvertimeApplication.class, args);
		System.out.println("SUCCESSFULLY DEPLOY SPRINGBOOT");
	}

}
