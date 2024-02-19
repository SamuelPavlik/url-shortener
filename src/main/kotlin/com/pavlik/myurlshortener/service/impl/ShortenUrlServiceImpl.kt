package com.pavlik.myurlshortener.service.impl

import com.pavlik.myurlshortener.repository.UrlRepository
import com.pavlik.myurlshortener.entity.UrlMapping
import com.pavlik.myurlshortener.entity.UrlCreateRequest
import com.pavlik.myurlshortener.service.ShortenUrlService
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class ShortenUrlServiceImpl(
    private val urlRepository: UrlRepository
) : ShortenUrlService {

    override fun createShortUrl(request: UrlCreateRequest): UrlMapping {
        val shortCode = generateSequence { generateShortCode() }
            .first { urlRepository.findByShortCode(it) == null }

        return urlRepository.save(UrlMapping(shortCode, request.originalUrl))
    }

    private fun generateShortCode(): String {
        val charPool : List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        return (1..8)
            .map { Random.nextInt(0, charPool.size) }
            .map(charPool::get)
            .joinToString("")
    }

    override fun findOriginalLink(shortUrl: String): String? {
        return urlRepository.findByShortCode(shortUrl)
            ?.originalUrl
    }
}