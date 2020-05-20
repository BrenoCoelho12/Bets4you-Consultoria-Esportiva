
package com.desenvolvimento.bets4you.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.desenvolvimento.bets4you.model.Equipe;
import com.desenvolvimento.bets4you.model.Pais;
import com.desenvolvimento.bets4you.repository.Equipes;

@Service
public class BuscarEquipesPorNacionalidade {

	@Autowired
	private Equipes equipes;
	
	public List<Equipe> buscarEquipesPorNacionalidade(Pais pais){
		
		if(!StringUtils.isEmpty(pais)) {
			
			List<Equipe> listaEquipes = new ArrayList<Equipe>();
			listaEquipes = equipes.findByNacionalidade(pais);
			return listaEquipes;
		}
		
		return null;
	}
}
