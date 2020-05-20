package com.desenvolvimento.bets4you.service.event.equipe;

import org.springframework.util.StringUtils;

import com.desenvolvimento.bets4you.model.Equipe;

public class EquipeSalvaEvent {
	
	private Equipe equipe;
	
	public EquipeSalvaEvent(Equipe equipe) {
		super();
		this.equipe = equipe;
	}
	
	public Equipe getEquipe() {
		return equipe;
	}
	
	public boolean temFoto() {
		return !StringUtils.isEmpty(equipe.getFoto());
	}
	
}
