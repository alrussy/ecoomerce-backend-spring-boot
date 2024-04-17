package com.alrussy.idantityservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@RequestMapping("/api/test")
public class IdantityServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdantityServiceApplication.class, args);
	}

	@GetMapping("/admin")
	public String testAdmin() {
		return "test admin successfuly";
	}
	@GetMapping("/user")
	public String getMethodName() {
		return "test User successfuly";
	}
	
}
