package com.desenvolvimento.bets4you.controller.converter;

import com.desenvolvimento.bets4you.model.Aposta;
import com.desenvolvimento.bets4you.model.Equipe;
import org.springframework.core.convert.converter.Converter;

import com.desenvolvimento.bets4you.model.Jogo;
import org.springframework.util.StringUtils;

public class ApostasConverter implements Converter<String, Aposta> {

	@Override
	public Aposta convert(String idAposta) {

		if(!StringUtils.isEmpty(idAposta)) {
			Aposta aposta = new Aposta();
			aposta.setId(Long.valueOf(idAposta));

			return aposta;
		}

		return null;
	}

}
