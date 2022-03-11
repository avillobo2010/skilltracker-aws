package com.cognizant.skilltracker.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import lombok.RequiredArgsConstructor;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.inject.Qualifier;

@Configuration
@RequiredArgsConstructor
@EnableDynamoDBRepositories
        (basePackages = "com.cognizant.skilltracker.repository")
public class DynamoDBConfiguration {

    private final AwsConfig config;

    public AWSCredentialsProvider amazonAWSCredentialsProvider() {
        return new AWSStaticCredentialsProvider(new BasicAWSCredentials(config.getAccessKey(), config.getSecretKey()));
    }

    @Bean
    public DynamoDBMapperConfig dynamoDBMapperConfig() {
        return DynamoDBMapperConfig.DEFAULT;
    }

   /* @Bean
    public DynamoDBMapper dynamoDBMapper(DynamoDBMapperConfig dynamoDBMapperConfig,AmazonDynamoDB amazonDynamoDB) {
        return new DynamoDBMapper(amazonDynamoDB, dynamoDBMapperConfig);
    }*/

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        return AmazonDynamoDBClientBuilder.standard().withCredentials(amazonAWSCredentialsProvider()).withEndpointConfiguration(
                new AwsClientBuilder.EndpointConfiguration(config.getEndpoint(), config.getRegion())).build();

    }
}
