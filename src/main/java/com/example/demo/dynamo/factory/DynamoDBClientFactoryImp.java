package com.example.demo.dynamo.factory;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.local.embedded.DynamoDBEmbedded;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;

@Component
public class DynamoDBClientFactoryImp implements DynamoDBClientFactory {

	@Override
	public AmazonDynamoDB getDynamoDBClient() {
		AmazonDynamoDB amazonDynamoDBClient = DynamoDBEmbedded.create().amazonDynamoDB();
		System.out.println("Created amazonDynamoDBClient");
		return amazonDynamoDBClient;
	}

	@Override
	public DynamoDB getDynamoDB() {
		DynamoDB dynamoDB = new DynamoDB(getDynamoDBClient());
		System.out.println("Created dynamoDB instance");
		createTable(dynamoDB);
		System.out.println("Notes table created");
		return dynamoDB;
	}
	
	private void createTable(DynamoDB dynamoDB) {
		ArrayList<KeySchemaElement> keySchema = new ArrayList<>();
		keySchema.add(new KeySchemaElement().withAttributeName("id").withKeyType(KeyType.HASH));
		
		ArrayList<AttributeDefinition> attributeDefinitions = new ArrayList<>();
		attributeDefinitions.add(new AttributeDefinition().withAttributeName("id").withAttributeType("S"));
		
		CreateTableRequest request = new CreateTableRequest()
				.withTableName("Notes")
				.withKeySchema(keySchema)
				.withAttributeDefinitions(attributeDefinitions)
				.withProvisionedThroughput(new ProvisionedThroughput()
						.withReadCapacityUnits(5L)
						.withWriteCapacityUnits(5L));
		dynamoDB.createTable(request);
	}

}
