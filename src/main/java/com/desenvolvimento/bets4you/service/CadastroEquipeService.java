package com.desenvolvimento.bets4you.service;

import java.util.Optional;

import com.desenvolvimento.bets4you.service.exception.ImpossivelApagarEntidadeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.desenvolvimento.bets4you.model.Equipe;
import com.desenvolvimento.bets4you.repository.Equipes;
import com.desenvolvimento.bets4you.service.exception.EquipeCadastradaException;

import javax.persistence.PersistenceException;

@Service
public class CadastroEquipeService {
	
	@Autowired
	private Equipes equipes;
	
	@Autowired 
	private ApplicationEventPublisher publisher;
	
	@Transactional
	public void salvar(Equipe equipe) {
		
		Optional<Equipe> equipeOptional = equipes.findByNacionalidadeAndNomeIgnoreCase(equipe.getNacionalidade(), equipe.getNome());
		
		if(equipeOptional.isPresent()) {
			throw new EquipeCadastradaException("Equipe já cadastrada");
		}
		
		equipes.save(equipe);
		
	}

	@Transactional
	public void excluir(Long id){
		try {
			equipes.delete(id);
			equipes.flush();
		}

		catch(PersistenceException e){
			throw new ImpossivelApagarEntidadeException("Impossível apapagar equipe, pois está vinculada a ao menos uma aposta.");
		}

	}

}
