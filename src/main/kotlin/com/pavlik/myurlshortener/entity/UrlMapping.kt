package com.pavlik.myurlshortener.entity

import com.amazonaws.services.dynamodbv2.datamodeling.*

@DynamoDBTable(tableName = "urls")
class UrlMapping(
    @field:DynamoDBHashKey
    @field:DynamoDBAttribute(attributeName = "shortCode")
    var shortCode: String = "",

    @field:DynamoDBAttribute(attributeName = "originalUrl")
    var originalUrl: String = ""
)