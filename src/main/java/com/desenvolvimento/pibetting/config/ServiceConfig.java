package com.desenvolvimento.pibetting.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.desenvolvimento.pibetting.service.CadastroUsuarioService;
import com.desenvolvimento.pibetting.storage.FotoStorage;
import com.desenvolvimento.pibetting.storage.local.FotoStorageLocal;

@Configuration
@ComponentScan(basePackageClasses = CadastroUsuarioService.class)
public class ServiceConfig {

	@Bean
	public FotoStorage fotoStorage() {
		return new FotoStorageLocal();
	}
	
}