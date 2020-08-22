package com.desenvolvimento.bets4you.repository;

import java.util.List;
import java.util.Optional;

import com.desenvolvimento.bets4you.repository.helper.equipe.EquipesQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desenvolvimento.bets4you.model.Equipe;
import com.desenvolvimento.bets4you.model.Pais;

@Repository
public interface Equipes extends JpaRepository<Equipe, Long>, EquipesQueries {
		
	public List<Equipe> findByNacionalidadeOrderByNomeAsc(Pais pais);
	
	public Optional<Equipe> findByNacionalidadeAndNomeIgnoreCase(Pais pais, String nome);
	
}