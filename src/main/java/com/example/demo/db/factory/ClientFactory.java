package com.example.demo.db.factory;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;

public interface ClientFactory {
	
	AmazonDynamoDB getDynamoDBClient();
	DynamoDB getDynamoDB();

}
