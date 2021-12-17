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
//
import java.util.Iterator;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.ScanOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec;
import com.amazonaws.services.dynamodbv2.document.utils.NameMap;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;




/*
*
* The scan method reads every item in the entire table and returns all the data in the table. You can provide an optional filter_expression so that only the items matching your criteria are returned. However, the filter is applied only after the entire table has been scanned.

The following program scans the entire Movies table, which contains approximately 5,000 items. The scan specifies the optional filter to retrieve only the movies from the 1950s (approximately 100 items) and discard all the others.
* */


public class MoviesScan {


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

        ScanSpec scanSpec = new ScanSpec().withProjectionExpression("#yr, title, info.rating")
            .withFilterExpression("#yr between :start_yr and :end_yr").withNameMap(new NameMap().with("#yr", "year"))
            .withValueMap(new ValueMap().withNumber(":start_yr", 1950).withNumber(":end_yr", 1959));

        try {
            ItemCollection<ScanOutcome> items = table.scan(scanSpec);

            Iterator<Item> iter = items.iterator();
            while (iter.hasNext()) {
                Item item = iter.next();
                System.out.println(item.toString());
            }

        }
        catch (Exception e) {
            System.err.println("Unable to scan the table:");
            System.err.println(e.getMessage());
        }
    }
}
