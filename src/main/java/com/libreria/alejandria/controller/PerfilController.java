
package com.libreria.alejandria.controller;

import com.libreria.alejandria.model.Usuario;
import com.libreria.alejandria.repository.UsuarioRepository;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PerfilController {

    private final UsuarioRepository usuarioRepository;

    public PerfilController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/perfil")
    public String mostrarPerfil(Authentication authentication, Model model) {

        String correo = authentication.getName();

        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() ->
                    new IllegalStateException("Usuario no encontrado")
                );

        model.addAttribute("usuario", usuario);

        return "perfil";
    }
}
