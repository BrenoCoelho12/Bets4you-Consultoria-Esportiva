package com.desenvolvimento.bets4you.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.desenvolvimento.bets4you.model.Jogo;
import com.desenvolvimento.bets4you.repository.Jogos;

@Service
public class CadastroJogoService {
	
	@Autowired
	private Jogos jogos;
	
	@Transactional
	public void salvar(Jogo jogo) {
		
		jogos.save(jogo);
	}

	@Transactional
	public void delete(Jogo jogo) {
		jogos.delete(jogo);
		jogos.flush();
	}

}
