
package com.desenvolvimento.pibetting.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.desenvolvimento.pibetting.model.Equipe;
import com.desenvolvimento.pibetting.model.Pais;
import com.desenvolvimento.pibetting.repository.Equipes;

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
