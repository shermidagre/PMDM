package com.example.cafetera.services.interfaces

interface QRService {
    fun generarQR(usuarioId: Int, maquinaId: Int, tipoCafe: String): String
    fun usarQR(token: String): String
}