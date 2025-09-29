package com.example.cafetera.models

import jakarta.persistence.*

@Entity
@Table(name = "recursos")
data class Recurso(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,

    @Column(nullable = false)
    val nombre: String,

    @Column(nullable = false)
    val cantidad: Int,

    @ManyToOne
    @JoinColumn(name = "maquina_id", nullable = false)
    val maquina: MaquinaCafe
)