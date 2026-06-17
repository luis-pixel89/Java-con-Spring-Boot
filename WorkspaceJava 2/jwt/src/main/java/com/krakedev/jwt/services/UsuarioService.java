package com.krakedev.jwt.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.krakedev.jwt.entidades.Usuario;
import com.krakedev.jwt.repositories.UsuarioRepository;


@Service
@Transactional
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;
	
	//Constructor Injection
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository=usuarioRepository;
	}
	
	//Guarda un usuario en la base de datos
	public Usuario guardarUsuario(Usuario usuario) {
		//Sin encriptar - texto plano
		//return usuarioRepository.save(usuario);
		
		//Encriptacion BCrypt
		String contarsenaEncryptada=BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt());
		
		usuario.setPassword(contarsenaEncryptada);
		
		return usuarioRepository.save(usuario);
	}
	
	//Autentica un usuario validando username y password
	@Transactional(readOnly = true)
	public boolean autenticar(String username, String password) {
		
		Optional<Usuario> usuarioOpt=usuarioRepository.findByUsername(username);
		
		if(usuarioOpt.isPresent()) {
			Usuario usuario=usuarioOpt.get();
			
			/*Sin encriptar - texto plano
			if(usuario.getPassword().equals(password)) {
				return true;
			}*/
			
			//Encriptacion BCrypt
			if(BCrypt.checkpw(password, usuario.getPassword())) {
				return true;
			}
		}
		
		return false;
	}
}
