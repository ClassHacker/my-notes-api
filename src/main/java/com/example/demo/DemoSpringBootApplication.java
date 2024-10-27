package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.example.demo.dynamo.factory.DynamoDBClientFactory;

//@SpringBootApplication
@ComponentScan
@SpringBootConfiguration
@EnableAutoConfiguration
public class DemoSpringBootApplication {
	
	@Autowired DynamoDBClientFactory dynamoDBClientFactory;

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringBootApplication.class, args);
	}
	
	@Bean
	DynamoDB getDynamoDB() {
		return dynamoDBClientFactory.getDynamoDB();
	}

}
