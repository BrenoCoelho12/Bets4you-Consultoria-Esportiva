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

	@Transactional
	public void atualizarStatus(Aposta aposta){
		Boolean status = aposta.getStatus(); //pegando o status desejado para a aposta

		aposta = apostas.findById(aposta.getId()); //pegando a aposta completa pelo id, já que o objeto 'aposta' chega apenas com o valor do id e do status.

		aposta.setStatus(status); //setando na aposta o valor do status desejado

		apostas.save(aposta); //atualizando a aposta para que tenha um novo status

	}
}
