package com.krakedev.proyectos.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.krakedev.proyectos.entidades.Usuario;
import com.krakedev.proyectos.repositories.UsuarioRepository;

@Service
@Transactional
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;

	public UsuarioService(UsuarioRepository usuarioRepository) {
		super();
		this.usuarioRepository = usuarioRepository;
	}
	
	public Usuario guardarUsuario(Usuario usuario) {
		String contrasenaEncryptada=BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt());
		
		usuario.setPassword(contrasenaEncryptada);
		
		return usuarioRepository.save(usuario);
	}
	
	@Transactional(readOnly=true)
	public boolean autenticar(String username, String password) {
		Optional<Usuario> usuarioOpt=usuarioRepository.findByUsername(username);
		
		if(usuarioOpt.isPresent()) {
			Usuario usuario=usuarioOpt.get();
			
			if(BCrypt.checkpw(password, usuario.getPassword())) {
				return true;
			}
		}
		
		return false;
	}
}
