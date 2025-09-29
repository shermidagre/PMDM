package com.example.cafetera.controllers

import com.example.cafetera.services.interfaces.QRService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/qr")
class QRController(private val qrService: QRService) {

    @GetMapping("/generar/{usuarioId}/{maquinaId}/{tipoCafe}")
    fun generarQR(@PathVariable usuarioId: Int, @PathVariable maquinaId: Int, @PathVariable tipoCafe: String): String {
        return qrService.generarQR(usuarioId, maquinaId, tipoCafe)
    }

    @PostMapping("/usar")
    fun usarQR(@Valid @RequestBody req: QRRequest): String {
        return qrService.usarQR(req.token)
    }
}

data class QRRequest(val token: String)