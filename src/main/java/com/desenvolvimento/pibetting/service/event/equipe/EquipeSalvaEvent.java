package com.desenvolvimento.pibetting.service.event.equipe;

import org.springframework.util.StringUtils;

import com.desenvolvimento.pibetting.model.Equipe;

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
