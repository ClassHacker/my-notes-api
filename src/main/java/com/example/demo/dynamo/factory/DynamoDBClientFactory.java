package com.example.demo.dynamo.factory;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;

public interface DynamoDBClientFactory {
	
	AmazonDynamoDB getDynamoDBClient();
	DynamoDB getDynamoDB();

}
