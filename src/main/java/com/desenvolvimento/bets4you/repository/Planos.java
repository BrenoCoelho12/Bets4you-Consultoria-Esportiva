package com.desenvolvimento.bets4you.repository;

import com.desenvolvimento.bets4you.model.Plano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Planos  extends JpaRepository<Plano, Long> {

    public Plano findById(Long id);
}
