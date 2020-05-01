package com.desenvolvimento.pibetting.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.desenvolvimento.pibetting.model.Equipe;

public class EquipeConverter implements Converter<String, Equipe>{

	@Override
	public Equipe convert(String idEquipe) {
		
		if(!StringUtils.isEmpty(idEquipe)) {
			Equipe equipe = new Equipe();
			equipe.setId(Long.valueOf(idEquipe));
			
			return equipe;
		}
		
		return null;
	}

}
