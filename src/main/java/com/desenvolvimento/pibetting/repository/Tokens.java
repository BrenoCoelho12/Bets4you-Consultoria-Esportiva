package com.desenvolvimento.pibetting.repository;

import com.desenvolvimento.pibetting.model.TokenValidation;
import com.desenvolvimento.pibetting.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Tokens extends JpaRepository<TokenValidation, Long> {

        public Optional<TokenValidation> findByToken(String token);

        public Optional<TokenValidation> findByUsuario(Usuario usuario);
}
