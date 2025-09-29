package com.example.cafetera.repositories

import com.example.cafetera.models.MaquinaCafe
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MaquinaRepository : JpaRepository<MaquinaCafe, Int>