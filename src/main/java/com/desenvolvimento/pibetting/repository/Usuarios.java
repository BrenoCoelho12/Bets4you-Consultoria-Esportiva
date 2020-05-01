package com.desenvolvimento.pibetting.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desenvolvimento.pibetting.model.Usuario;
import com.desenvolvimento.pibetting.repository.helper.usuario.UsuariosQueries;

public interface Usuarios extends JpaRepository<Usuario, Long>, UsuariosQueries{
	
	public Optional<Usuario> findByEmailIgnoreCase(String email);
	
}
