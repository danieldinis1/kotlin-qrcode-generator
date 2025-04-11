package com.example.qrcode

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class QrCodeApplication

fun main(args: Array<String>) {
    runApplication<QrCodeApplication>(*args)
    TODO("Maybe a link shortener")
}
