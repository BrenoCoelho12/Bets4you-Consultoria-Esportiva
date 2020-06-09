package com.desenvolvimento.bets4you.repository;

import com.desenvolvimento.bets4you.model.Situacao;
import com.desenvolvimento.bets4you.repository.helper.aposta.ApostasQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desenvolvimento.bets4you.model.Aposta;

import java.util.List;

@Repository
public interface Apostas extends JpaRepository<Aposta, Long>, ApostasQueries {

    public Aposta findById(Long id);

    public List<Aposta> findByStatus(Boolean status);

    public List<Aposta> findByOrderByDataDesc();
	
}