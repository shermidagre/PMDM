package com.example.cafetera.controllers

import com.example.cafetera.models.Usuario
import com.example.cafetera.models.rol
import com.example.cafetera.services.UsuarioService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/usuarios")
class UsuarioController(private val usuarioService: UsuarioService) {

    @GetMapping("/{id}")
    fun obtenerUsuario(@PathVariable id: Int): Usuario? {
        return usuarioService.findById(id)
    }

    @PostMapping("/crear")
    fun crearUsuario(@RequestBody req: CrearUsuarioRequest): Usuario {
        val rolEnum = when (req.rol.lowercase()) { // <-- Convierte el string a enum
            "admin" -> rol.admin
            "user" -> rol.user
            else -> throw IllegalArgumentException("Rol no válido: ${req.rol}")
        }
        return usuarioService.crearUsuario(req.nombre, req.email, rolEnum) // <-- Pasa el enum
    }

    @PostMapping("/{id}/recargar")
    fun recargarSaldo(@PathVariable id: Int, @RequestBody req: RecargarSaldoRequest) {
        usuarioService.recargarSaldo(id, req.monto)
    }
}

data class CrearUsuarioRequest(val nombre: String, val email: String, val rol: String)
data class RecargarSaldoRequest(val monto: java.math.BigDecimal)