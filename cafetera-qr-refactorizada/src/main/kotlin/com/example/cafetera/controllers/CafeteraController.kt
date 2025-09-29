package com.example.cafetera.controllers

import com.example.cafetera.services.interfaces.CafeteraService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/cafetera")
class CafeteraController(private val cafeteraService: CafeteraService) {

    @PostMapping("/comprar")
    fun comprarCafe(@Valid @RequestBody req: CompraCafeRequest): String {
        return cafeteraService.comprarCafe(req.usuarioId, req.maquinaId, req.tipoCafe, req.conAzucar)
    }
}

data class CompraCafeRequest(
    val usuarioId: Int,
    val maquinaId: Int,
    val tipoCafe: String,
    val conAzucar: Boolean = false
)