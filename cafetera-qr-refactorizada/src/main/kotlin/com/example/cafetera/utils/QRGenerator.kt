package com.example.cafetera.utils

import com.google.zxing.BarcodeFormat
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter
import org.springframework.stereotype.Component
import java.io.ByteArrayOutputStream
import java.util.Base64

@Component
class QRGenerator {
    fun generateQR(contenido: String): String {
        val qrWriter = QRCodeWriter()
        val bitMatrix: BitMatrix = qrWriter.encode(contenido, BarcodeFormat.QR_CODE, 200, 200)
        val pngOutputStream = ByteArrayOutputStream()
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream)
        return Base64.getEncoder().encodeToString(pngOutputStream.toByteArray())
    }
}