package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.example.demo.db.factory.ClientFactory;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@EnableAutoConfiguration(exclude = {
        MongoAutoConfiguration.class,
        MongoDataAutoConfiguration.class
})
@ActiveProfiles("dynamo-db")
public class DemoSpringBootApplicationTests {

    @Mock
    private ClientFactory dynamoDBClientFactory;

    @Mock
    private DynamoDB dynamoDB;

    @InjectMocks
    private DemoSpringBootApplication demoSpringBootApplication;

    @Test
    public void contextLoads() {
        // This test ensures that the Spring application context loads successfully
    }

    @Test
    public void testGetDynamoDB() {
        when(dynamoDBClientFactory.getDynamoDB()).thenReturn(dynamoDB);

        DynamoDB result = demoSpringBootApplication.getDynamoDB();

        verify(dynamoDBClientFactory).getDynamoDB();
        assertSame(dynamoDB, result);
    }
}