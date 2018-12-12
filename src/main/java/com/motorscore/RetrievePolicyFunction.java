package com.motorscore;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Index;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;


public class RetrievePolicyFunction implements RequestHandler<RetrievePolicyRequest, RetrievePolicyResponse>{

    @Override
    public RetrievePolicyResponse handleRequest(final RetrievePolicyRequest input, final Context context) {
        final AmazonDynamoDBClient client = new AmazonDynamoDBClient(new EnvironmentVariableCredentialsProvider());
        client.withRegion(Regions.US_EAST_1);
        final DynamoDB dynamoDB = new DynamoDB(client);

        System.out.println("input = " + input);

        final Table table = dynamoDB.getTable("Policies");
        final Index index = table.getIndex("policyNumber-index");
        final ItemCollection<QueryOutcome> items = index.query("policyNumber", input.getPolicyNumber());

        final RetrievePolicyResponse response = new RetrievePolicyResponse();
        for (final Item item : items) {
            final Policy policy = new Policy();
            policy.setPolicyNumber(item.getString("policyNumber"));
            policy.setxAcceleration(item.getDouble("xAcceleration"));
            policy.setyAcceleration(item.getDouble("yAcceleration"));
            policy.setSpeed(item.getDouble(("speed")));
            response.getLocations().add(policy);
        }

        return response;
    }
}
