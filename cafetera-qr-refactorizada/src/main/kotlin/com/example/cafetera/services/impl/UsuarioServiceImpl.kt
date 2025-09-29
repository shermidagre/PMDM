package com.example.cafetera.services.impl

import com.example.cafetera.models.Usuario
import com.example.cafetera.repositories.UsuarioRepository
import com.example.cafetera.services.interfaces.UsuarioService
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDateTime

@Service
class UsuarioServiceImpl(
    private val usuarioRepository: UsuarioRepository
) : UsuarioService {

    override fun crearUsuario(nombre: String, email: String, saldo: BigDecimal): Usuario {
        if (usuarioRepository.findByEmail(email) != null) {
            throw RuntimeException("El email ya está registrado.")
        }

        val nuevoUsuario = Usuario(
            nombre = nombre,
            email = email,
            saldo = saldo,
            rol = "cliente",
            fechaRegistro = LocalDateTime.now()
        )
        return usuarioRepository.save(nuevoUsuario)
    }

    override fun findById(id: Int): Usuario? {
        return usuarioRepository.findById(id).orElse(null)
    }

    override fun findAll(): List<Usuario> {
        return usuarioRepository.findAll()
    }

    override fun updateSaldo(id: Int, nuevoSaldo: BigDecimal) {
        val usuario = findById(id) ?: throw RuntimeException("Usuario no encontrado")
        val actualizado = usuario.copy(saldo = nuevoSaldo)
        usuarioRepository.save(actualizado)
    }

    override fun recargarSaldo(id: Int, monto: BigDecimal) {
        val usuario = findById(id) ?: throw RuntimeException("Usuario no encontrado")
        val nuevoSaldo = usuario.saldo + monto
        updateSaldo(id, nuevoSaldo)
    }
}