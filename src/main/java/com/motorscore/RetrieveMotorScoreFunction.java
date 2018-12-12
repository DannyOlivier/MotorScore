package com.motorscore;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class RetrieveMotorScoreFunction implements RequestHandler<RetrieveMotorScoreRequest,RetrieveMotorScoreResponse> {
    @Override
    public RetrieveMotorScoreResponse handleRequest(RetrieveMotorScoreRequest input, Context context) {

        final double maxMotorSocre = 900;

        final AmazonDynamoDBClient client = new AmazonDynamoDBClient(new EnvironmentVariableCredentialsProvider());
        client.withRegion(Regions.US_EAST_1);
        final DynamoDB dynamoDB = new DynamoDB(client);

        System.out.println("input = " + input);

        final Table table = dynamoDB.getTable("Policies");
        final Index index = table.getIndex("policyNumber-index");
        final ItemCollection<QueryOutcome> items = index.query("policyNumber", input.getPolicyNumber());

        final RetrieveMotorScoreResponse response = new RetrieveMotorScoreResponse();


        double accelerationPoints = 350;
        double speedPoints = 550;

        for (final Item item : items) {
            double xAcceleration = item.getDouble("xAcceleration");
            double yAcceleration = item.getDouble("yAcceleration");
            double speed = item.getDouble("speed");

            /** Acceleration Deduction */
            double acceleration = (Math.sqrt(Math.pow(xAcceleration, 2d) + Math.pow(yAcceleration, 2d)));
            if(acceleration < 0.3d){
                accelerationPoints = accelerationPoints + 10;
            }
            if(acceleration >= 0.3d && acceleration < 0.5d ){
                accelerationPoints = accelerationPoints + 5;
            }
            if(acceleration >= 0.5d && acceleration < 0.7d ){
                accelerationPoints = accelerationPoints - 10;
            }
            if(acceleration >= 0.7d && acceleration < 1.2d ){
                accelerationPoints = accelerationPoints - 50;
            }
            if(acceleration >= 1.2d ){
                accelerationPoints = accelerationPoints - 250;
            }

            if(accelerationPoints > 350){
                accelerationPoints = 350;
            } else if (accelerationPoints < 0){
                accelerationPoints = 0;
            }

            /** Speed Deduction */
            if(speed < 30d){
                speedPoints = speedPoints + 10;
            }
            if(speed >= 30d && speed < 45d ){
                speedPoints = speedPoints + 5;
            }
            if(speed >= 75d && speed < 80d ){
                speedPoints = speedPoints  - 50;
            }
            if(speed >= 80d ){
                speedPoints = speedPoints - 250;
            }

            if(speedPoints > 550){
                speedPoints = 550;
            } else if (speedPoints < 0){
                speedPoints = 0;
            }

        }

        response.setPolicyNumber(input.getPolicyNumber());
        response.setMotorScore(accelerationPoints + speedPoints);

        return response;
    }
}

