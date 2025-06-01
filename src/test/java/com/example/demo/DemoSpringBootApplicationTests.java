package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.example.demo.db.factory.ClientFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
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