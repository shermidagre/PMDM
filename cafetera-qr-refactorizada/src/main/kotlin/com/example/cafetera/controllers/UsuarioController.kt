package com.example.cafetera.controllers

import com.example.cafetera.models.Usuario
import com.example.cafetera.services.interfaces.UsuarioService
import jakarta.validation.Valid
import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal

@RestController
@RequestMapping("/api/usuarios")
class UsuarioController(private val usuarioService: UsuarioService) {

    @GetMapping("/{id}")
    fun obtenerUsuario(@PathVariable id: Int): Usuario? {
        return usuarioService.findById(id)
    }

    @PostMapping("/crear")
    fun crearUsuario(@Valid @RequestBody req: CrearUsuarioRequest): Usuario {
        return usuarioService.crearUsuario(req.nombre, req.email, req.saldo)
    }

    @PostMapping("/{id}/recargar")
    fun recargarSaldo(@PathVariable id: Int, @Valid @RequestBody req: RecargarSaldoRequest) {
        usuarioService.recargarSaldo(id, req.monto)
    }
}

data class CrearUsuarioRequest(
    @field:NotBlank val nombre: String,
    @field:Email val email: String,
    @field:DecimalMin("0.0", message = "El saldo no puede ser negativo") val saldo: BigDecimal = BigDecimal.ZERO
)

data class RecargarSaldoRequest(
    @field:DecimalMin("0.01", message = "El monto debe ser mayor a 0") val monto: BigDecimal
)
