package com.desenvolvimento.bets4you.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desenvolvimento.bets4you.model.Usuario;
import com.desenvolvimento.bets4you.repository.helper.usuario.UsuariosQueries;

public interface Usuarios extends JpaRepository<Usuario, Long>, UsuariosQueries{
	
	public Optional<Usuario> findByEmailIgnoreCase(String email);

	public Usuario findById(Long id);

	
}
