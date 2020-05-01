package com.desenvolvimento.pibetting.service.event.equipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.desenvolvimento.pibetting.storage.FotoStorage;

@Component
public class EquipeListener {
	
	@Autowired
	private FotoStorage fotoStorage;
	
	@EventListener(condition = "#evento.temFoto()")
	public void EquipeSalva(EquipeSalvaEvent evento) {
		fotoStorage.salvar(evento.getEquipe().getFoto());
	}
	
}
