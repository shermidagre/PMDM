package com.example.cafetera.models

import jakarta.persistence.*

@Entity
@Table(name = "maquinas")
data class MaquinaCafe(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,

    @Column(nullable = false)
    val ubicacion: String
)