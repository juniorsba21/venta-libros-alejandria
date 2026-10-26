
package com.libreria.alejandria.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
            .authorizeHttpRequests(auth -> auth

                // Páginas públicas
                .requestMatchers(
                     "/", "/login", "/registro",
                    "/libros", "/libros/**", "/contacto",
                    "/error",
                    "/css/**", "/js/**",
                    "/images/**", "/img/**",
                    "/webjars/**"
                ).permitAll()

                // Solo administradores
                .requestMatchers("/admin", "/admin/**")
                .hasRole("ADMIN")

                // Otras páginas: requieren iniciar sesión
                .anyRequest().authenticated()
            )

            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("correo")
                .passwordParameter("password")
                .defaultSuccessUrl("/", false)
                .failureUrl("/login?error")
                .permitAll()
            )

            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Sin cifrado: la contraseña se guarda y se compara tal cual (solo para el proyecto)
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return rawPassword.toString().equals(encodedPassword);
            }
        };

    }
}
