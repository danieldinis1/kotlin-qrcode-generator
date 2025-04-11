package com.example.qrcode.model

data class UrlRequest(
    val url: String
){
    init {
        require(url.isNotBlank()) { "URL must not be blank" }
        require(url.startsWith("http://") || url.startsWith("https://")) { "URL must start with http:// or https://" }
    }
}

//MAYBE USE java.net.URL for memory purposes?
