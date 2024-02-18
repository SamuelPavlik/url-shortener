package com.pavlik.myurlshortener.controller

import com.pavlik.myurlshortener.service.ShortenUrlService
import com.pavlik.myurlshortener.entity.UrlCreateRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/shortenUrl")
class ShortenUrlController (
    private val shortenUrlService: ShortenUrlService
) {

    @PostMapping
    fun createShortUrl(@RequestBody request: UrlCreateRequest) = shortenUrlService.createShortUrl(request)

}