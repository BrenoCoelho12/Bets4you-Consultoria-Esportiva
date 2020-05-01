package com.desenvolvimento.pibetting.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.desenvolvimento.pibetting.model.Equipe;
import com.desenvolvimento.pibetting.repository.Equipes;
import com.desenvolvimento.pibetting.service.event.equipe.EquipeSalvaEvent;
import com.desenvolvimento.pibetting.service.exception.EquipeCadastradaException;

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
		
		publisher.publishEvent(new EquipeSalvaEvent(equipe));
		
	}
}
