package com.example.cafetera.services.impl

import com.example.cafetera.cafe.CafeteraAPI
import com.example.cafetera.models.Transaccion
import com.example.cafetera.models.Usuario
import com.example.cafetera.repositories.*
import com.example.cafetera.services.interfaces.CafeteraService
import com.example.cafetera.services.interfaces.UsuarioService
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class CafeteraServiceImpl(
    private val usuarioService: UsuarioService,
    private val maquinaRepository: MaquinaRepository,
    private val recursoRepository: RecursoRepository,
    private val transaccionRepository: TransaccionRepository,
    private val cafeteraAPI: CafeteraAPI
) : CafeteraService {

    override fun comprarCafe(usuarioId: Int, maquinaId: Int, tipoCafe: String, conAzucar: Boolean): String {
        val usuario = usuarioService.findById(usuarioId) ?: return "Usuario no encontrado."
        val maquina = maquinaRepository.findById(maquinaId).orElse(null) ?: return "Máquina no encontrada."

        val importe = getPrecio(tipoCafe)
        if (usuario.saldo >= importe) {
            if (!verificarRecursos(maquinaId, tipoCafe)) {
                return "Recursos insuficientes en la máquina."
            }

            val nuevoSaldo = usuario.saldo - importe
            usuarioService.updateSaldo(usuarioId, nuevoSaldo)

            val transaccion = Transaccion(
                usuario = usuario, // Objeto completo
                maquina = maquina, // Objeto completo
                tipoCafe = tipoCafe,
                importe = importe
            )
            transaccionRepository.save(transaccion)

            cafeteraAPI.prepararCafe(tipoCafe, conAzucar)
            return "Café dispensado."
        }
        return "Saldo insuficiente."
    }

    private fun verificarRecursos(maquinaId: Int, tipoCafe: String): Boolean {
        val recursos = recursoRepository.findByMaquinaId(maquinaId)
        return recursos.all { it.cantidad > 0 }
    }

    private fun getPrecio(tipoCafe: String): BigDecimal = when (tipoCafe) {
        "solo" -> BigDecimal("0.40")
        "leche" -> BigDecimal("0.50")
        "capuchino" -> BigDecimal("0.60")
        else -> BigDecimal("0.00")
    }
}