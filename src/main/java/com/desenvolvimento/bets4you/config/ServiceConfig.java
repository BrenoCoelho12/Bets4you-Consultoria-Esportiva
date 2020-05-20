package com.desenvolvimento.bets4you.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.desenvolvimento.bets4you.service.CadastroUsuarioService;
import com.desenvolvimento.bets4you.storage.FotoStorage;
import com.desenvolvimento.bets4you.storage.local.FotoStorageLocal;

@Configuration
@ComponentScan(basePackageClasses = CadastroUsuarioService.class)
public class ServiceConfig {

	@Bean
	public FotoStorage fotoStorage() {
		return new FotoStorageLocal();
	}
	
}