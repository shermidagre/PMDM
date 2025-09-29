package com.example.cafetera.repositories

import com.example.cafetera.models.Transaccion
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TransaccionRepository : JpaRepository<Transaccion, Int>