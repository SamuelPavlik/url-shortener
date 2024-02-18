package com.pavlik.myurlshortener.config

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
@EnableDynamoDBRepositories(basePackages = ["com.pavlik.myurlshortener"])
class DynamoDBConfig(
    @Value("\${amazon.dynamodb.endpoint}") private val endpoint: String,
    @Value("\${amazon.aws.region}") private val region: String
) {

    @Primary
    @Bean
    fun dynamoDBMapper(amazonDynamoDB: AmazonDynamoDB): DynamoDBMapper {
        return DynamoDBMapper(amazonDynamoDB, DynamoDBMapperConfig.DEFAULT)
    }

    @Bean
    fun amazonDynamoDB(): AmazonDynamoDB {
        val awsCredentialsProvider = DefaultAWSCredentialsProviderChain()
        val endpointConfiguration = AwsClientBuilder.EndpointConfiguration(endpoint, region)
        return AmazonDynamoDBClientBuilder.standard()
            .withCredentials(awsCredentialsProvider)
            .withEndpointConfiguration(endpointConfiguration)
            .build()
    }

}