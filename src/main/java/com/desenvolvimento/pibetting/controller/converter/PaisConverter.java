package com.desenvolvimento.pibetting.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.desenvolvimento.pibetting.model.Pais;

public class PaisConverter implements Converter<String, Pais> { //aula 9.7

	@Override
	public Pais convert(String id) {
		
		if(!StringUtils.isEmpty(id)) {
			Pais pais = new Pais();
			pais.setId(Long.valueOf(id));
			return pais;
		}
		
		
		return null;
	}

}
