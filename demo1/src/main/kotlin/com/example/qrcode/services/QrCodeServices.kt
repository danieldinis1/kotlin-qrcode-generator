package com.example.qrcode.services

import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import org.springframework.stereotype.Service
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import javax.imageio.ImageIO

@Service
class QrCodeService {

    fun generateQrCode(text: String): ByteArray {
        val writer = QRCodeWriter()
        val bitMatrix = writer.encode(text, BarcodeFormat.QR_CODE, 250, 250)

        val image = BufferedImage(250, 250, BufferedImage.TYPE_INT_RGB)
        for (x in 0 until 250) {
            for (y in 0 until 250) {
                image.setRGB(x, y, if (bitMatrix[x, y]) 0x000000 else 0xFFFFFF)
            }
        }

        val outputStream = ByteArrayOutputStream()
        ImageIO.write(image, "PNG", outputStream)
        return outputStream.toByteArray()
    }
}
