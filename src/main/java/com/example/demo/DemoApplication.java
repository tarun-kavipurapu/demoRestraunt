package com.example.demo;

import com.example.demo.auth.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootApplication
@EnableMongoRepositories

//@SpringBootApplication(exclude= {SecurityAutoConfiguration.class})
//@ComponentScan(basePackages = {"com.example.demo.controller", "com.example.demo.auth", "com.example.demo.service","com.example.demo.model"})
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
//	@Bean
//	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http.authorizeHttpRequests().anyRequest().permitAll();
//		return http.build();
//	}

}
