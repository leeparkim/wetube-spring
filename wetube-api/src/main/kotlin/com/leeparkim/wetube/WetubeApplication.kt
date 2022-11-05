package com.leeparkim.wetube

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class WetubeApplication

fun main(args: Array<String>) {
    runApplication<WetubeApplication>(*args)
}
