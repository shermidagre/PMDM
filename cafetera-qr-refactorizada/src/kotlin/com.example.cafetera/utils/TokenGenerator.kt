package com.example.cafetera.utils

import org.springframework.stereotype.Component
import java.util.*

@Component
class TokenGenerator {
    fun generar(): String {
        return UUID.randomUUID().toString()
    }
}