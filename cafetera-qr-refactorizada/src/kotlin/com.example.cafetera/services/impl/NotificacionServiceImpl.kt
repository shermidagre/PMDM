package com.example.cafetera.services.impl

import com.example.cafetera.services.interfaces.NotificacionService
import org.springframework.stereotype.Service

@Service
class NotificacionServiceImpl : NotificacionService {
    override fun enviarNotificacion(usuarioId: Int, mensaje: String) {
        println("[NOTIFICACIÓN] Usuario $usuarioId: $mensaje")
    }
}