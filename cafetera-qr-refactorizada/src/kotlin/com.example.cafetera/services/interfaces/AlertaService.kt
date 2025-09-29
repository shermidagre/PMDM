package com.example.cafetera.services.impl

import com.example.cafetera.models.Recurso
import com.example.cafetera.services.interfaces.AlertaService
import org.springframework.stereotype.Service

@Service
class AlertaServiceImpl : AlertaService {
    override fun verificarRecursos(recursos: List<Recurso>, umbral: Int) {
        recursos.forEach { recurso ->
            if (recurso.cantidad < umbral) {
                println("[ALERTA] Recurso '${recurso.nombre}' bajo: ${recurso.cantidad} unidades.")
            }
        }
    }
}