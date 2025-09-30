package com.example.cafetera.services

import com.example.cafetera.models.Usuario
import com.example.cafetera.models.rol
import java.math.BigDecimal

interface UsuarioService {
    fun crearUsuario(nombre: String, email: String, rol: rol): Usuario // <-- Cambia el tipo de rol
    fun findById(id: Int): Usuario?
    fun findAll(): List<Usuario>
    fun updateSaldo(id: Int, nuevoSaldo: BigDecimal)
    fun recargarSaldo(id: Int, monto: BigDecimal)
    fun findByEmail(email: String): Usuario?
}