package com.example.cafetera.services.impl

import com.example.cafetera.services.interfaces.CafeteraService
import com.example.cafetera.services.interfaces.QRService
import com.example.cafetera.utils.QRGenerator
import com.example.cafetera.utils.TokenGenerator
import org.springframework.stereotype.Service
import java.util.*

@Service
class QRServiceImpl(
    private val qrGenerator: QRGenerator,
    private val tokenGenerator: TokenGenerator,
    private val cafeteraService: CafeteraService
) : QRService {
    private val tokensUsados = mutableSetOf<String>()

    override fun generarQR(usuarioId: Int, maquinaId: Int, tipoCafe: String): String {
        val token = tokenGenerator.generar()
        val contenido = "$token:$usuarioId:$maquinaId:$tipoCafe"
        return qrGenerator.generateQR(contenido)
    }

    override fun usarQR(token: String): String {
        if (token in tokensUsados) return "QR ya utilizado."

        val partes = token.split(':')
        if (partes.size != 4) return "QR inválido."

        val (_, usuarioId, maquinaId, tipoCafe) = partes
        tokensUsados.add(token)

        return cafeteraService.comprarCafe(usuarioId.toInt(), maquinaId.toInt(), tipoCafe, false)
    }
}