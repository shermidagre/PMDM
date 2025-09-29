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
                    .requestMatchers("/login", "/static/**", "/css/**", "/js/**", "/images/**").permitAll()
                    .anyRequest().authenticated()
            }
            .formLogin { login ->
                login
                    .loginPage("/login") // Página de login personalizada
                    .defaultSuccessUrl("/dashboard", true)
                    .permitAll()
            }
            .logout { logout ->
                logout.permitAll()
            }
        return http.build()
    }
}