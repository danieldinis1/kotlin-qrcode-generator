package com.example.qrcode

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `should return 200 when login is successful`() {
        mockMvc.perform(
            post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""{"username": "validUser", "password": "validPassword"}""")
        ).andExpect(status().isOk)
    }

    @Test
    fun `should return 400 when username is missing`() {
        mockMvc.perform(
            post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""{"password": "validPassword"}""")
        ).andExpect(status().isBadRequest)
    }

    @Test
    fun `should return 400 when password is missing`() {
        mockMvc.perform(
            post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""{"username": "validUser"}""")
        ).andExpect(status().isBadRequest)
    }

    @Test
    fun `should return 400 when username and password are blank`() {
        mockMvc.perform(
            post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""{"username": "", "password": ""}""")
        ).andExpect(status().isBadRequest)
    }

    @Test
    fun `should return 400 when request body is invalid`() {
        mockMvc.perform(
            post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("\"invalid-json\"")
        ).andExpect(status().isBadRequest)
    }

    @Test
    fun `should return 200 when login with different valid credentials`() {
        mockMvc.perform(
            post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""{"username": "anotherUser", "password": "anotherPassword"}""")
        ).andExpect(status().isOk)
    }
}