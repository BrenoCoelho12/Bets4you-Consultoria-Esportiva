package com.desenvolvimento.bets4you.repository;

import com.desenvolvimento.bets4you.model.Usuario;
import com.desenvolvimento.bets4you.model.UsuarioPlano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioPlanos extends JpaRepository<UsuarioPlano, Long> {

    public Optional<UsuarioPlano> findByUsuario(Usuario usuario);
}
