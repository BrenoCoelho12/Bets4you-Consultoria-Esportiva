package com.desenvolvimento.pibetting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desenvolvimento.pibetting.model.Aposta;

@Repository
public interface Apostas extends JpaRepository<Aposta, Long> {
	
	
	
}