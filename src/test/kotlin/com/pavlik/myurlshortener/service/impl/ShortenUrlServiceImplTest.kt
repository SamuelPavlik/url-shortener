package com.pavlik.myurlshortener.service.impl

import com.ninjasquad.springmockk.MockkBean
import com.pavlik.myurlshortener.entity.UrlCreateRequest
import com.pavlik.myurlshortener.entity.UrlMapping
import com.pavlik.myurlshortener.repository.UrlRepository
import com.pavlik.myurlshortener.service.ShortenUrlService
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ShortenUrlServiceImplTest {

    @Autowired
    lateinit var shortenUrlService: ShortenUrlService

    @MockkBean
    lateinit var urlRepository: UrlRepository

    @BeforeEach
    fun setUp() {
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun `when provided valid url then save it`() {
        //when
        val mapping = UrlMapping("shortUrl", "originalUrl")
        every { urlRepository.save(any()) } answers {
            mapping
        }

        val request = UrlCreateRequest(mapping.originalUrl)
        val result = shortenUrlService.createShortUrl(request)

        //then
        verify { urlRepository.save(any()) }

        assertEquals("shortUrl", result.shortCode)
        assertEquals("originalUrl", result.originalUrl)
    }

    @Test
    fun `when provided valid url creates unique url each time`() {
        val seenUrls = HashSet<String>()
        val numOfReps = 100
        val slot = mutableListOf<UrlMapping>()

        //when
        every { urlRepository.save(any()) } answers { UrlMapping() }

        repeat (numOfReps) {
            val request = UrlCreateRequest("originalUrl")
            shortenUrlService.createShortUrl(request)

            verify { urlRepository.save(capture(slot)) }

            //then
            assertFalse(seenUrls.contains(slot.last().shortCode))
            seenUrls.add(slot.last().shortCode)
        }
    }
}