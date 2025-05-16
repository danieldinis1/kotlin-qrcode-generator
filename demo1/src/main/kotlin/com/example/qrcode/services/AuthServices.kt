package com.example.qrcode.services;

import org.springframework.stereotype.Service

//simple 1 way login

@Service
class AuthServices{
    private val validUser = "admin"
    private val validPass ="123"
    fun authenticate(user: String, pass: String): Boolean = user == validUser && pass == validPass
}
