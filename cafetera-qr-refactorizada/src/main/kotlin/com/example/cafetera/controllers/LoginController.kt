package com.example.cafetera.controllers

import com.example.cafetera.models.Usuario
import com.example.cafetera.models.rol
import com.example.cafetera.services.UsuarioService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.slf4j.LoggerFactory
@Controller
class LoginController(private val usuarioService: UsuarioService) { // <-- Inyecta usuarioService aquí

    @GetMapping("/login")
    fun login(): String {
        return "login"
    }
    companion object {
        private val logger = LoggerFactory.getLogger(LoginController::class.java)
    }
    @PostMapping("/login")
    fun processLogin(
        @RequestParam email: String,
        model: Model
    ): String {
        val usuario: Usuario? = usuarioService.findByEmail(email)

        if (usuario != null) {
            val rolLeido = usuario.rolusuario
            val esAdmin = (rolLeido == rol.admin)

            logger.info("---------------------------------")
            logger.info("ROL LEÍDO (DB): {}", rolLeido)
            logger.info("ROL ENUM (admin): {}", rol.admin)
            logger.info("¿ES ADMIN? (Comparación): {}", esAdmin)
            logger.info("---------------------------------")

            model.addAttribute("usuario", usuario)

            // Redirigir según rol
            return if (esAdmin) { // Usamos la variable booleana para la redirección
                "admin"
            } else {
                "dashboard"
            }
        }  else {
            model.addAttribute("error", "Email no encontrado")
            return "login"
        }
    }
}