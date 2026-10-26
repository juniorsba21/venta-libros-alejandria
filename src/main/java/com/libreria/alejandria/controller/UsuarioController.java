
package com.libreria.alejandria.controller;

import com.libreria.alejandria.model.Rol;
import com.libreria.alejandria.model.Usuario;
import com.libreria.alejandria.repository.RolRepository;
import com.libreria.alejandria.repository.UsuarioRepository;
import com.libreria.alejandria.service.UsuarioService;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final RolRepository rolRepository;
    private final UsuarioRepository usuarioRepository;

    public UsuarioController(
            UsuarioService usuarioService,
            RolRepository rolRepository,
            UsuarioRepository usuarioRepository) {

        this.usuarioService = usuarioService;
        this.rolRepository = rolRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
        model.addAttribute("paginaActual", "registro");
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    @PostMapping("/registro")
    public String registrar(
            @Valid @ModelAttribute("usuario") Usuario usuario,
            BindingResult result,
            Model model) {

        if (usuario.getCorreo() != null &&
            usuarioRepository.findByCorreo(usuario.getCorreo()).isPresent()) {

            result.rejectValue(
                "correo",
                "correo.duplicado",
                "Este correo ya está registrado"
            );
        }

        if (result.hasErrors()) {
            model.addAttribute("paginaActual", "registro");
            return "registro";
        }

        Rol rolUser = rolRepository.findByNombre("USER")
                .orElseThrow(() ->
                    new IllegalStateException(
                        "No existe el rol USER en la base de datos"
                    )
                );

        usuario.setRol(rolUser);
        usuarioService.guardar(usuario);

        return "redirect:/login";
    }
}
