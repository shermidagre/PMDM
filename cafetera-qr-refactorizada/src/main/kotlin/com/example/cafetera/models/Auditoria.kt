package com.example.cafetera.models

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "auditoria")
data class Auditoria(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    val usuario: Usuario? = null,

    @Column(nullable = false)
    val accion: String,

    @Column(columnDefinition = "TEXT")
    val detalles: String,

    @Column(name = "fecha", nullable = false)
    val fecha: LocalDateTime = LocalDateTime.now()
)