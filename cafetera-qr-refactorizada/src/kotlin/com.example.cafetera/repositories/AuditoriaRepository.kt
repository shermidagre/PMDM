package com.example.cafetera.repositories

import com.example.cafetera.models.Auditoria
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuditoriaRepository : JpaRepository<Auditoria, Int>