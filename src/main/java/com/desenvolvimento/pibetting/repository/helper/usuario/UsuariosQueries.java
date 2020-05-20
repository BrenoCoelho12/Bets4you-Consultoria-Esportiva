package com.desenvolvimento.pibetting.repository.helper.usuario;

import java.util.List;
import java.util.Optional;

import com.desenvolvimento.pibetting.model.Usuario;

public interface UsuariosQueries {

	public Optional<Usuario> porEmail(String email);
	
	public List<String> permissoes(Usuario usuario);

	public void atualizarEmailValidation(Long idUsuario);

	public void atualizarAcessoVip(Long idUsuario);

}
