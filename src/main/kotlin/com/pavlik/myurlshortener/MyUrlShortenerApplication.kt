package com.pavlik.myurlshortener

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MyUrlShortenerApplication

fun main(args: Array<String>) {
    runApplication<MyUrlShortenerApplication>(*args)
}
