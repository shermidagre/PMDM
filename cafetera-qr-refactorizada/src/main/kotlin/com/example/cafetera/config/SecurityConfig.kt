package com.example.cafetera.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests { authorize ->
                authorize
                    // Permitir acceso a vistas públicas
                    .requestMatchers("/login", "/static/**", "/css/**", "/js/**", "/images/**").permitAll()
                    // Permitir acceso a vistas protegidas solo para usuarios autenticados
                    .requestMatchers("/admin", "/dashboard", "/index").authenticated()
                    .anyRequest().authenticated()
            }
            .formLogin { login ->
                login
                    .loginPage("/dashboard")
                    .defaultSuccessUrl("/dashboard", true)
                    .permitAll()
            }
            .logout { logout ->
                logout.permitAll()
            }
        return http.build()
    }
}