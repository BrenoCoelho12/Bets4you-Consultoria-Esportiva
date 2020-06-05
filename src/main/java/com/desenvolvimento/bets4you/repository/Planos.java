package com.desenvolvimento.bets4you.repository;

import com.desenvolvimento.bets4you.model.Plano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Planos  extends JpaRepository<Plano, Long> {

    public Optional<Plano> findById(Long id);
}
