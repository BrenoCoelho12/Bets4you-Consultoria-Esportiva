package com.desenvolvimento.pibetting.repository;

import com.desenvolvimento.pibetting.model.UsuarioPlano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioPlanos extends JpaRepository<UsuarioPlano, Long> {

}
