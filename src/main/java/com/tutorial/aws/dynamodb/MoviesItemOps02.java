package com.tutorial.aws.dynamodb;///**
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
//
//import com.amazonaws.client.builder.AwsClientBuilder;
//import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
//import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
//import com.amazonaws.services.dynamodbv2.document.DynamoDB;
//import com.amazonaws.services.dynamodbv2.document.Item;
//import com.amazonaws.services.dynamodbv2.document.Table;
//import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
//
//
///*
//* get the item from the table
//* */
//
//public class MoviesItemOps02 {
//
//
//
//    public static void main(String[] args) throws Exception {
//
//        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
//            .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "us-west-2"))
//            .build();
//
//        DynamoDB dynamoDB = new DynamoDB(client);
//
//        Table table = dynamoDB.getTable("Movies");
//
//        int year = 2015;
//        String title = "The Big New Movie";
//
//        GetItemSpec spec = new GetItemSpec().withPrimaryKey("year", year, "title", title);
//
//        try {
//            System.out.println("Attempting to read the item...");
//            Item outcome = table.getItem(spec);
//            System.out.println("GetItem succeeded: " + outcome);
//
//        }
//        catch (Exception e) {
//            System.err.println("Unable to read item: " + year + " " + title);
//            System.err.println(e.getMessage());
//        }
//
//    }
//}
