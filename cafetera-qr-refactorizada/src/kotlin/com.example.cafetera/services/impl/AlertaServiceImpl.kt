package com.example.cafetera.services.interfaces

import com.example.cafetera.models.Recurso

interface AlertaService {
    fun verificarRecursos(recursos: List<Recurso>, umbral: Int = 10)
}