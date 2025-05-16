package com.example.qrcode.model

data class LoginRequest(val username: String, val password: String){
    init{
        require(username.isNotBlank() && password.isNotBlank()) { "Please introduce password" }
    }
}