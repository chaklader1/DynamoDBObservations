/**
 * Copyright 2010-2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * <p>
 * This file is licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License. A copy of
 * the License is located at
 * <p>
 * http://aws.amazon.com/apache2.0/
 * <p>
 * This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */


package com.tutorial.aws.dynamodb.movies_utils;

import java.util.Arrays;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;


//@Component
public class MoviesCreateTable {



//    @Value("${cloud.aws.credentials.accessKey}")
//    private String key;
//
//    @Value("${cloud.aws.credentials.secretKey}")
//    private String secretKey;

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

        //        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
//            .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "us-west-2"))
//            .build();

        DynamoDB dynamoDB = new DynamoDB(client);

        String tableName = "Songs";

        try {
            System.out.println("Attempting to create table; please wait...");

            Table table = dynamoDB.createTable(tableName,
                Arrays.asList(

                    new KeySchemaElement("year", KeyType.HASH), // Partition
                    // key
                    new KeySchemaElement("title", KeyType.RANGE)), // Sort key
                Arrays.asList(new AttributeDefinition("year", ScalarAttributeType.N),
                    new AttributeDefinition("title", ScalarAttributeType.S)),
                new ProvisionedThroughput(10L, 10L));

            table.waitForActive();
            System.out.println("Success.  Table status: " + table.getDescription().getTableStatus());

        } catch (Exception e) {
            System.err.println("Unable to create table: ");
            System.err.println(e.getMessage());
        }

    }
}
