package com.example.cafetera.controllers

import com.example.cafetera.models.Usuario
import com.example.cafetera.services.UsuarioService
import org.springframework.web.bind.annotation.*
import org.springframework.http.ResponseEntity
import org.slf4j.LoggerFactory
import java.math.BigDecimal

@RestController // Importante: Usamos RestController para APIs JSON
@RequestMapping("/api/admin")
class AdminController(private val usuarioService: UsuarioService) {

    companion object {
        private val logger = LoggerFactory.getLogger(AdminController::class.java)
    }

    /**
     * Endpoint: /api/admin/usuarios
     * Función: Devuelve una lista de todos los usuarios. El frontend usa estos datos para llenar la tabla.
     */
    @GetMapping("/usuarios")
    fun getAllUsuarios(): ResponseEntity<List<Usuario>> {
        logger.info("Solicitud GET para /api/admin/usuarios recibida.")
        val usuarios = usuarioService.findAll()
        return ResponseEntity.ok(usuarios)
    }

    /**
     * Endpoint: /api/admin/ventas
     * Función: Devuelve datos simulados o reales para el gráfico de ventas.
     * El JS espera un objeto con 'labels' y 'values'.
     */
    @GetMapping("/ventas")
    fun getVentasData(): Map<String, List<Any>> {
        logger.info("Solicitud GET para /api/admin/ventas (Gráfico) recibida.")
        // --- SIMULACIÓN DE DATOS ---
        // Estos datos se cargan correctamente en el Chart.js.
        val labels = listOf("Ene 2024", "Feb 2024", "Mar 2024", "Abr 2024")
        val values = listOf(50, 75, 60, 90)

        return mapOf(
            "labels" to labels,
            "values" to values
        )
    }

    /**
     * Endpoint: /api/admin/auditoria
     * Función: Devuelve un mensaje de auditoría simple (pendiente de implementación real).
     * El frontend NO lo llama automáticamente, pero puedes usarlo para el botón.
     */
    @GetMapping("/auditoria")
    fun getAuditoria(): ResponseEntity<String> {
        return ResponseEntity.ok("Acceso al registro de auditoría. Implementación pendiente.")
    }

    /**
     * Endpoint: /api/admin/recargar/{userId}
     * Función: Recarga saldo a un usuario. El frontend envía {userId} en la URL y {amount} en el cuerpo.
     * Importante: Se cambió el tipo de userId a Int.
     */
    data class RecargaRequest(val amount: BigDecimal)

    @PostMapping("/recargar/{userId}")
    fun recargarSaldo(
        @PathVariable userId: Int, // Cambiado a Int
        @RequestBody request: RecargaRequest
    ): ResponseEntity<String> {
        logger.info("Recibida solicitud para recargar {}€ al usuario ID: {}", request.amount, userId)

        if (request.amount <= BigDecimal.ZERO) {
            return ResponseEntity.badRequest().body("Error: El monto de recarga debe ser positivo.")
        }

        return try {
            usuarioService.recargarSaldo(userId, request.amount)
            logger.info("Recarga exitosa para el usuario ID: {}", userId)
            ResponseEntity.ok("Saldo recargado exitosamente al usuario ID $userId. Nuevo saldo actualizado en la tabla.")
        } catch (e: NoSuchElementException) {
            logger.error("Usuario no encontrado con ID: {}", userId)
            ResponseEntity.status(404).body("Error: Usuario no encontrado.")
        } catch (e: Exception) {
            logger.error("Error al recargar saldo para el usuario ID: {}. Error: {}", userId, e.message)
            ResponseEntity.badRequest().body("Error interno al procesar la recarga.")
        }
    }
}