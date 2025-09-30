package com.example.cafetera.services.impl

import com.example.cafetera.models.Usuario
import com.example.cafetera.models.rol
import com.example.cafetera.repositories.UsuarioRepository
import com.example.cafetera.services.UsuarioService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Service
class UsuarioServiceImpl(private val usuarioRepository: UsuarioRepository) : UsuarioService {

    // --- Métodos de la interfaz UsuarioService ---

    override fun crearUsuario(nombre: String, email: String, rol: rol): Usuario {
        val nuevoUsuario = Usuario(nombre = nombre, email = email, rolusuario = rol)
        return usuarioRepository.save(nuevoUsuario)
    }

    override fun findById(id: Int): Usuario? {
        return usuarioRepository.findById(id).orElse(null)
    }

    override fun findAll(): List<Usuario> {
        return usuarioRepository.findAll()
    }

    override fun updateSaldo(id: Int, nuevoSaldo: BigDecimal) {
        // En un caso real, esto se haría dentro de recargarSaldo o comprarCafe.
    }

    override fun findByEmail(email: String): Usuario? {
        return usuarioRepository.findByEmail(email)
    }

    /**
     * Lógica crítica: Incrementa el saldo de un usuario en la base de datos.
     */
    @Transactional
    override fun recargarSaldo(id: Int, monto: BigDecimal) {
        val usuario = usuarioRepository.findById(id).orElseThrow {
            NoSuchElementException("Usuario con ID $id no encontrado.")
        }
        val nuevoSaldo = usuario.saldo.add(monto)
        val usuarioActualizado = usuario.copy(saldo = nuevoSaldo)
        usuarioRepository.save(usuarioActualizado)
    }
}
