package com.example.cafetera.config

import com.example.cafetera.services.UsuarioDetailsService // <-- ¡Revisa esta línea!
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig(private val userDetailsService: UsuarioDetailsService) { // <--- Aquí ya no debería dar error

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    // Configuración para usar nuestro UserDetailsService
    fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder())
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests { requests ->
                requests
                    // Rutas públicas
                    .requestMatchers("/login", "/register", "/css/**", "/js/**").permitAll()

                    // Rutas del Dashboard
                    .requestMatchers("/dashboard").authenticated()

                    // Rutas de Administración (Requiere login)
                    .requestMatchers("/admin").authenticated()

                    // *** CAMBIO CRUCIAL: Autoriza todas las APIs de /api/admin/ ***
                    .requestMatchers("/api/admin/**").authenticated()

                    // El resto de rutas requiere autenticación por defecto (buena práctica)
                    .anyRequest().authenticated()
            }
            .formLogin { form ->
                form
                    .loginPage("/login")
                    .defaultSuccessUrl("/dashboard", true) // Redirige al dashboard después del login
                    .permitAll()
            }
            .logout { logout ->
                logout
                    .logoutSuccessUrl("/login?logout")
                    .permitAll()
            }
            .csrf { csrf -> csrf.disable() }

        return http.build()
    }
}