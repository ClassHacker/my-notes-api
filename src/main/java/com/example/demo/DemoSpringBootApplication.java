package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.example.demo.db.factory.ClientFactory;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class DemoSpringBootApplication {
	
	@Autowired
	ClientFactory dynamoDBClientFactory;

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(DemoSpringBootApplication.class, args);
	}

	@Profile("dynamo-db")
	@Bean
	DynamoDB getDynamoDB() {
		return dynamoDBClientFactory.getDynamoDB();
	}

}
