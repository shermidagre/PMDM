package com.example.cafetera.models

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "usuarios")
data class Usuario(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,

    @Column(nullable = false)
    val nombre: String,

    @Column(unique = true, nullable = false)
    val email: String,

    @Column(name = "rol", nullable = false)
    @Enumerated(EnumType.STRING)
    val rolusuario: rol = rol.user,

    @Column(nullable = false)
    val saldo: BigDecimal = BigDecimal.ZERO,

    @Column(name = "fecha_registro", nullable = false)
    val fechaRegistro: LocalDateTime = LocalDateTime.now()
)

enum class rol {
    user, admin
}