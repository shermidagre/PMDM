package com.example.cafetera.services.interfaces

interface NotificacionService {
    fun enviarNotificacion(usuarioId: Int, mensaje: String)
}