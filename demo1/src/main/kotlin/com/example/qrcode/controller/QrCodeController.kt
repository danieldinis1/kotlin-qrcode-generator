package com.example.qrcode.controller

import com.example.qrcode.model.UrlRequest
import com.example.qrcode.services.QrCodeService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/")
class QrCodeController(
    private val qrService: QrCodeService
)
 {

     @PostMapping("/generate", consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.IMAGE_PNG_VALUE])
     @ResponseBody
     fun generateQr(@RequestBody url: UrlRequest): ResponseEntity<ByteArray> {
         println("Received URL: $url")
        try {
            val image = qrService.generateQrCode(url.url)
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(image)
        }
        catch(e:Exception){
           return ResponseEntity.status(500).body("Error generating QR code".toByteArray())
        }
    }


}
