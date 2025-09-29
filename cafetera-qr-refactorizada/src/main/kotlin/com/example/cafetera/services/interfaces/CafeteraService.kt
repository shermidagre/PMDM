package com.example.cafetera.services.interfaces

interface CafeteraService {
    fun comprarCafe(usuarioId: Int, maquinaId: Int, tipoCafe: String, conAzucar: Boolean): String
}