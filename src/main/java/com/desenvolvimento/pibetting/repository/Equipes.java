package com.desenvolvimento.pibetting.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desenvolvimento.pibetting.model.Equipe;
import com.desenvolvimento.pibetting.model.Pais;

@Repository
public interface Equipes extends JpaRepository<Equipe, Long>{
		
	public List<Equipe> findByNacionalidade(Pais pais);
	
	public Optional<Equipe> findByNacionalidadeAndNomeIgnoreCase(Pais pais, String nome);
	
}