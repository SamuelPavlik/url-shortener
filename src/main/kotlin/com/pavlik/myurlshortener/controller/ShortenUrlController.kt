package com.pavlik.myurlshortener.controller

import com.pavlik.myurlshortener.entity.UrlCreateRequest
import com.pavlik.myurlshortener.service.ShortenUrlService
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/v1")
class ShortenUrlController (
    private val shortenUrlService: ShortenUrlService
) {

    @PostMapping("/shortenUrl")
    fun createShortUrl(@RequestBody request: UrlCreateRequest) = shortenUrlService.createShortUrl(request)

    @GetMapping("/{shortCode}")
    fun redirectToLongUrl(@PathVariable shortCode: String, response: HttpServletResponse) {
        shortenUrlService.findOriginalLink(shortCode)
            ?.let { response.sendRedirect(it) }
            ?:throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }
}