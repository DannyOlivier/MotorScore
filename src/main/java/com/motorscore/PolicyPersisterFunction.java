package com.motorscore;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.UUID;


public class PolicyPersisterFunction implements RequestHandler<Policy, Void> {

    @Override
    public Void handleRequest(final Policy input, final Context context) {
        final AmazonDynamoDBClient client = new AmazonDynamoDBClient(new EnvironmentVariableCredentialsProvider());
        client.withRegion(Regions.US_EAST_1); // specify the region you created the table in.
        final DynamoDB dynamoDB = new DynamoDB(client);

        System.out.println("input = " + input); // Pure for testing. Do not use System.out in production code
        final Table table = dynamoDB.getTable("Policies");
        final Item item = new Item()
                .withPrimaryKey("id", UUID.randomUUID().toString()) // Every item gets a unique id
                .withString("policyNumber", input.getPolicyNumber())
                .withDouble("xAcceleration", input.getxAcceleration())
                .withDouble("yAcceleration", input.getyAcceleration())
                .withDouble("rate", input.getyAcceleration())
                .withDouble("speed", input.getSpeed());
        table.putItem(item);
        return null;
    }
}
