package com.example.cafetera.cafe

import com.example.cafetera.models.Recurso

class CafeteraAPI {
    fun prepararCafe(tipoCafe: String, conAzucar: Boolean = false) {
        println("Sirviendo $tipoCafe ${if (conAzucar) "con azúcar" else "sin azúcar"}...")
    }

    fun comprobarRecursos(recursos: List<Recurso>): Boolean {
        return recursos.all { it.cantidad > 0 }
    }
}