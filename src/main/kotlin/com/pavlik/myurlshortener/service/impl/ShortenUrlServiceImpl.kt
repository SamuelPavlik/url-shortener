package com.pavlik.myurlshortener.service.impl

import com.pavlik.myurlshortener.repository.UrlRepository
import com.pavlik.myurlshortener.entity.UrlMapping
import com.pavlik.myurlshortener.entity.UrlCreateRequest
import com.pavlik.myurlshortener.service.ShortenUrlService
import org.springframework.stereotype.Service

@Service
class ShortenUrlServiceImpl(
    private val urlRepository: UrlRepository
) : ShortenUrlService {

    companion object {
        var counter: Int = 0
    }

    override fun createShortUrl(request: UrlCreateRequest): UrlMapping {
        return urlRepository.save(UrlMapping("short${counter++}", request.originalUrl))
    }

    override fun findOriginalLink(shortUrl: String): String? {
        return urlRepository.findByShortCode(shortUrl)
            ?.originalUrl
    }
}