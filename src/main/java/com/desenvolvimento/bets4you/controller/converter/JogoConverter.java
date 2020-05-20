package com.desenvolvimento.bets4you.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.desenvolvimento.bets4you.model.Jogo;

public class JogoConverter implements Converter<String, Jogo> { //aula 9.7

	@Override
	public Jogo convert(String idJogo) {
		
		if(!StringUtils.isEmpty(idJogo)) {
			Jogo jogo = new Jogo();
			jogo.setId(Long.valueOf(idJogo));
			
			return jogo;
		}
		
		return null;
	}
	
	
	
	
	
}
