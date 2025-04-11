package com.example.demo.model

import com.example.qrcode.model.UrlRequest
import org.junit.jupiter.api.Test

class UrlTests {

    @Test
    fun `test it doesn't initialize a UrlRequest with a blank URL`() {
        val exception = org.junit.jupiter.api.assertThrows<IllegalArgumentException> {
            UrlRequest("")
        }
        assert(exception.message == "URL must not be blank")
    }


    @Test
    fun `test it doesn't initialize a UrlRequest with an invalid URL`() {
        val exception = org.junit.jupiter.api.assertThrows<IllegalArgumentException> {
            UrlRequest("invalid-url")
        }
        assert(exception.message == "URL must start with http:// or https://")
    }
}