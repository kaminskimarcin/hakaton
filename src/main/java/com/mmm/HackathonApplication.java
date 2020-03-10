package com.mmm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class HackathonApplication {

	public static void main(String[] args) {
		SpringApplication.run(HackathonApplication.class, args);
	}
	public String hello() {
		return "hello";
	}
}
