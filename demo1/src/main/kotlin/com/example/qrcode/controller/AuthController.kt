package com.example.qrcode.controller

import com.example.qrcode.model.LoginRequest
import com.example.qrcode.services.AuthServices
import jakarta.servlet.http.HttpSession
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class AuthController(private val authService: AuthServices) {

    @PostMapping("/login")
    fun login(@RequestBody login: LoginRequest, session: HttpSession): ResponseEntity<String> {
        return if (authService.authenticate(login.username, login.password)) {
            session.setAttribute("user", login.username)
            ResponseEntity.ok("Logged in")
        } else {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials")
        }
    }

    @PostMapping("/logout")
    fun logout(session: HttpSession): ResponseEntity<String> {
        session.invalidate()
        return ResponseEntity.ok("Logged out")
    }

    @GetMapping("/me")
    fun whoAmI(session: HttpSession): ResponseEntity<String> {
        val user = session.getAttribute("user") as? String
            ?: return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not logged in")
        return ResponseEntity.ok(user)
    }
}
