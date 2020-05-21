package com.desenvolvimento.bets4you.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.desenvolvimento.bets4you.service.CadastroUsuarioService;
import com.desenvolvimento.bets4you.storage.FotoStorage;

@Configuration
@ComponentScan(basePackageClasses = { CadastroUsuarioService.class, FotoStorage.class })
public class ServiceConfig {
	
}