package com.desenvolvimento.pibetting.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.desenvolvimento.pibetting.model.Usuario;
import com.desenvolvimento.pibetting.repository.Usuarios;
import com.desenvolvimento.pibetting.service.exception.EmailUsuarioCadastradoException;

@Service
public class CadastroUsuarioService {

	@Autowired
	private Usuarios usuarios;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	public void salvar(Usuario usuario) {
		Optional<Usuario> usuarioOptional = usuarios.findByEmailIgnoreCase(usuario.getEmail());
		
		if(usuarioOptional.isPresent()) {
			throw new EmailUsuarioCadastradoException("O email informado já pertence a um usuário cadastrado");
		}
		
		usuario.setSenha(this.passwordEncoder.encode(usuario.getSenha()));
		usuario.setConfirmacaoSenha(usuario.getSenha());
		usuarios.save(usuario);
	}
	
	
	
}