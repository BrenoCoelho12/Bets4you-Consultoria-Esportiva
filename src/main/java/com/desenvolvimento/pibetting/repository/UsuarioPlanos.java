package com.desenvolvimento.pibetting.repository;

import com.desenvolvimento.pibetting.model.Usuario;
import com.desenvolvimento.pibetting.model.UsuarioPlano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioPlanos extends JpaRepository<UsuarioPlano, Long> {

    public Optional<UsuarioPlano> findByUsuario(Usuario usuario);
}
