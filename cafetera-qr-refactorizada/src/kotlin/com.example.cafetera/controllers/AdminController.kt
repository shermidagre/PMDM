package com.example.cafetera.controllers

import com.example.cafetera.models.Usuario
import com.example.cafetera.services.interfaces.UsuarioService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/admin")
class AdminController(private val usuarioService: UsuarioService) {

    @GetMapping("/usuarios")
    fun listarUsuarios(): List<Usuario> {
        return usuarioService.findAll()
    }

    @GetMapping("/ventas")
    fun reporteVentas() = mapOf(
        "total_ventas" to 100,
        "ingresos_totales" to 150.00
    )
}