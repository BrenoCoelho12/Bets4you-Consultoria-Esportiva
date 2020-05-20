package com.desenvolvimento.bets4you.repository;

import com.desenvolvimento.bets4you.model.TokenValidation;
import com.desenvolvimento.bets4you.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Tokens extends JpaRepository<TokenValidation, Long> {

        public Optional<TokenValidation> findByToken(String token);

        public Optional<TokenValidation> findByUsuario(Usuario usuario);
}
