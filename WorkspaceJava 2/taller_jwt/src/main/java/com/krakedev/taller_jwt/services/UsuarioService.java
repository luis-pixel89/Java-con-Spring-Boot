package com.krakedev.taller_jwt.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.krakedev.taller_jwt.entidades.Usuario;
import com.krakedev.taller_jwt.repositories.UsuarioRepository;

@Service
@Transactional
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    // Constructor Injection
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Guarda un usuario en la base de datos
     */
    public Usuario guardarUsuario(Usuario usuario) {
    	String contrasenaEncryptada=BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt());
    	
    	usuario.setPassword(contrasenaEncryptada);
    	
        return usuarioRepository.save(usuario);
    }

    /**
     * Autentica un usuario validando username y password
     */
    @Transactional(readOnly = true)
    public boolean autenticar(String username, String password) {

        Optional<Usuario> usuarioOpt =
                usuarioRepository.findByUsername(username);

        if (usuarioOpt.isPresent()) {

            Usuario usuario = usuarioOpt.get();

            if (BCrypt.checkpw(password, usuario.getPassword())) {
                return true;
            }
        }

        return false;
    }
}