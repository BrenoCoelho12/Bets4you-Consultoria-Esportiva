package com.desenvolvimento.bets4you.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desenvolvimento.bets4you.model.Pais;

@Repository
public interface Paises extends JpaRepository<Pais, Long> {
	
}
