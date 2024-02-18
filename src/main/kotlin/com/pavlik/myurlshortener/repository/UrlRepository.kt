package com.pavlik.myurlshortener.repository

import com.pavlik.myurlshortener.entity.UrlMapping
import org.socialsignin.spring.data.dynamodb.repository.EnableScan
import org.springframework.data.repository.CrudRepository

@EnableScan
interface UrlRepository : CrudRepository<UrlMapping, String> {

    fun findByShortUrl(shortUrl: String): UrlMapping?
}