
package com.libreria.alejandria.service;

import com.libreria.alejandria.model.Usuario;
import com.libreria.alejandria.repository.UsuarioRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder) {

        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void guardar(Usuario usuario) {

        String passwordCifrada =
                passwordEncoder.encode(usuario.getPassword());

        usuario.setPassword(passwordCifrada);

        usuarioRepository.save(usuario);
    }
}
