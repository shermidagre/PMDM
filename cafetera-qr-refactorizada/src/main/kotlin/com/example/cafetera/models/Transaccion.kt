package com.example.cafetera.models

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "transacciones")
data class Transaccion(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    val usuario: Usuario,

    @ManyToOne
    @JoinColumn(name = "maquina_id", nullable = false)
    val maquina: MaquinaCafe,

    @Column(nullable = false)
    val tipoCafe: String,

    @Column(nullable = false)
    val importe: BigDecimal,

    @Column(name = "fecha", nullable = false)
    val fecha: LocalDateTime = LocalDateTime.now()
)