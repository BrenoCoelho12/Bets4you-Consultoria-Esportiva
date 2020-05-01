package com.desenvolvimento.pibetting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.desenvolvimento.pibetting.model.Aposta;
import com.desenvolvimento.pibetting.repository.Apostas;

@Service
public class CadastroApostaService {

	@Autowired
	private Apostas apostas;
	
	@Transactional
	public Long salvar(Aposta aposta) {
		
		apostas.save(aposta);
		
		return aposta.getId();
	}
}
