package com.tutorial.aws.dynamodb.movies_utils;///**
// * Copyright 2010-2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.
// * <p>
// * This file is licensed under the Apache License, Version 2.0 (the "License").
// * You may not use this file except in compliance with the License. A copy of
// * the License is located at
// * <p>
// * http://aws.amazon.com/apache2.0/
// * <p>
// * This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
// * CONDITIONS OF ANY KIND, either express or implied. See the License for the
// * specific language governing permissions and limitations under the License.
// */
//
//
//package com.example.myapp;
//
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;

//
///*
// * Step 5: (Optional) Delete the Table
// * */
//


public class MoviesDeleteTable {


    public static void main(String[] args) throws Exception {

        //        DynamoDbClient client = DynamoDbClient.builder()
//            .region(Region.US_EAST_1)
//            .credentialsProvider(StaticCredentialsProvider.create(
//                AwsBasicCredentials.create("access_key_id", "secret_key_id")))
//            .build();
//

        BasicAWSCredentials awsCreds = new BasicAWSCredentials("access_key_id", "secret_key_id");

        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
            .withRegion(Regions.US_EAST_1)
            .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
            .build();

        DynamoDB dynamoDB = new DynamoDB(client);

        Table table = dynamoDB.getTable("Movies");

        try {
            System.out.println("Attempting to delete table; please wait...");
            table.delete();
            table.waitForDelete();
            System.out.print("Success.");

        } catch (Exception e) {
            System.err.println("Unable to delete table: ");
            System.err.println(e.getMessage());
        }
    }
}
