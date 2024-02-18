package com.pavlik.myurlshortener.service

import com.pavlik.myurlshortener.entity.UrlMapping
import com.pavlik.myurlshortener.entity.UrlCreateRequest

interface ShortenUrlService {
    fun createShortUrl(request: UrlCreateRequest): UrlMapping
    fun findByShortUrl(shortUrl: String): UrlMapping?
}
