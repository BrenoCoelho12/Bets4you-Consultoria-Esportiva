package com.desenvolvimento.bets4you.service;

import com.desenvolvimento.bets4you.model.Jogo;
import com.desenvolvimento.bets4you.model.Situacao;
import com.desenvolvimento.bets4you.service.exception.ImpossivelApagarEntidadeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.desenvolvimento.bets4you.model.Aposta;
import com.desenvolvimento.bets4you.repository.Apostas;

import javax.persistence.PersistenceException;
import java.util.List;

@Service
public class CadastroApostaService {

	@Autowired
	private Apostas apostas;

	@Autowired
	private CadastroJogoService cadastroJogoService;

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

	@Transactional
	public void atualizarSituacao(Aposta aposta){
		Situacao situacao = aposta.getSituacao(); //pegando a situacao desejada para a aposta

		aposta = apostas.findById(aposta.getId()); //pegando a aposta completa pelo id, já que o objeto 'aposta' chega apenas com o valor do id e do status.

		aposta.setSituacao(situacao); //setando na aposta o valor do status desejado

		apostas.save(aposta); //atualizando a aposta para que tenha um novo status

	}

	@Transactional
	public void excluir(Long codigoAposta) {
		try{
			List<Jogo> jogos = apostas.findById(codigoAposta).getJogos(); //pegando
			for(int i = 0;i<jogos.size();i++){
				cadastroJogoService.delete(jogos.get(i));
			}
			apostas.delete(codigoAposta);
			apostas.flush();
		}
		catch(PersistenceException e){
			throw new ImpossivelApagarEntidadeException("Impossível apagar aposta.");
		}

	}

}
