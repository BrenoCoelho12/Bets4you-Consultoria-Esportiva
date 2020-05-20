package com.desenvolvimento.bets4you.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.desenvolvimento.bets4you.model.Usuario;
import com.desenvolvimento.bets4you.repository.Usuarios;
import com.desenvolvimento.bets4you.service.exception.EmailUsuarioCadastradoException;

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

	@Transactional
	public void atualizarSenha(Usuario usuario, String novaSenha){
		/*preciso receber o usuario completo com o findById porque o objeto
		usuario que chega possui apenas a nova senha e o id como parâmetro não nulo.
		Acontece que se não buscasse todos os parametros do usuario com o findById
		o usuarios.save daria erro.
		 */
		usuario = usuarios.findById(usuario.getId());
		usuario.setSenha(this.passwordEncoder.encode(novaSenha));
		usuario.setConfirmacaoSenha(usuario.getSenha());
		usuarios.save(usuario);

	}

	@Transactional
	public void setUsuarioVip(Usuario usuario){
		usuario.setAcessoVip(true);
		usuarios.save(usuario);
	}
	
}