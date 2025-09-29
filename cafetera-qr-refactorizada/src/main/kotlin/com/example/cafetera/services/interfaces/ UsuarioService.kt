package com.example.cafetera.services.interfaces

import com.example.cafetera.models.Usuario
import java.math.BigDecimal

interface UsuarioService {
    fun crearUsuario(nombre: String, email: String, saldo: BigDecimal): Usuario
    fun findById(id: Int): Usuario?
    fun findAll(): List<Usuario>
    fun updateSaldo(id: Int, nuevoSaldo: BigDecimal)
    fun recargarSaldo(id: Int, monto: BigDecimal)
}