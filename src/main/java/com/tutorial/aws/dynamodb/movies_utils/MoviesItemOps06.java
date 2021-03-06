package com.tutorial.aws.dynamodb.movies_utils;///**
// * Copyright 2010-2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.
// *
// * This file is licensed under the Apache License, Version 2.0 (the "License").
// * You may not use this file except in compliance with the License. A copy of
// * the License is located at
// *
// * http://aws.amazon.com/apache2.0/
// *
// * This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
// * CONDITIONS OF ANY KIND, either express or implied. See the License for the
// * specific language governing permissions and limitations under the License.
//*/
//
//
//package com.example.myapp;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.PrimaryKey;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;


/*
* delete an item
* */
public class MoviesItemOps06 {

    public static void main(String[] args) throws Exception {

//        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
//            .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "us-west-2"))
//            .build();

        BasicAWSCredentials awsCreds = new BasicAWSCredentials("access_key_id", "secret_key_id");

        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
            .withRegion(Regions.US_EAST_1)
            .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
            .build();

        DynamoDB dynamoDB = new DynamoDB(client);

        Table table = dynamoDB.getTable("Movies");

        int year = 2015;
        String title = "The Big New Movie";

//        DeleteItemSpec deleteItemSpec = new DeleteItemSpec()
//            .withPrimaryKey(new PrimaryKey("year", year, "title", title)).withConditionExpression("info.rating <= :val")
//            .withValueMap(new ValueMap().withNumber(":val", 5.0));

        // Conditional delete (we expect this to fail)
        DeleteItemSpec deleteItemSpec = new DeleteItemSpec()
            .withPrimaryKey(new PrimaryKey("year", 2015, "title", "The Big New Movie"));

        try {
            System.out.println("Attempting a conditional delete...");
            table.deleteItem(deleteItemSpec);
            System.out.println("DeleteItem succeeded");
        }
        catch (Exception e) {
            System.err.println("Unable to delete item: " + year + " " + title);
            System.err.println(e.getMessage());
        }
    }
}





