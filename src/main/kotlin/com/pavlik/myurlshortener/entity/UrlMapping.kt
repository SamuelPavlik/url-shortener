package com.pavlik.myurlshortener.entity

import com.amazonaws.services.dynamodbv2.datamodeling.*

@DynamoDBTable(tableName = "urls")
class UrlMapping(
    @field:DynamoDBHashKey
    @field:DynamoDBAttribute(attributeName = "shortUrl")
    var shortUrl: String = "",

    @field:DynamoDBAttribute(attributeName = "originalUrl")
    var originalUrl: String = ""
)