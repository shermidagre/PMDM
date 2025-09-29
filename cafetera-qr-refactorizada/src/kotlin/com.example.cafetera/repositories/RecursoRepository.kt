package com.example.cafetera.repositories

import com.example.cafetera.models.Recurso
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RecursoRepository : JpaRepository<Recurso, Int> {
    fun findByMaquinaId(maquinaId: Int): List<Recurso>
}