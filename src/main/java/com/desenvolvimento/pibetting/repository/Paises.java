package com.desenvolvimento.pibetting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desenvolvimento.pibetting.model.Pais;

@Repository
public interface Paises extends JpaRepository<Pais, Long> {
	
}
