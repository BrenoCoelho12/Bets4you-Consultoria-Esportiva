package com.desenvolvimento.pibetting.repository;

import com.desenvolvimento.pibetting.model.Equipe;
import com.desenvolvimento.pibetting.model.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desenvolvimento.pibetting.model.Aposta;

import java.util.List;

@Repository
public interface Apostas extends JpaRepository<Aposta, Long> {

    public Aposta findById(Long id);

    public List<Aposta> findByStatus(Boolean status);

    public List<Aposta> findByOrderByDataDesc();
	
}