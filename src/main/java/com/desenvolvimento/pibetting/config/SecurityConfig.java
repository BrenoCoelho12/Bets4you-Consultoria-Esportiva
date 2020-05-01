package com.desenvolvimento.pibetting.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.desenvolvimento.pibetting.security.AppUserDetailsService;

@EnableWebSecurity
@ComponentScan(basePackageClasses = AppUserDetailsService.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
		.antMatchers("/layout/**")
		.antMatchers("/javascripts/**")
		.antMatchers("/") //pagina inicial
		.antMatchers("/planos") //pagina de visualização dos planos vips
		.antMatchers("/usuario/novo") //pagina de cadastro de usuário
		.antMatchers("/usuario/aguardandoConfirmacaoEmail")
		.antMatchers(HttpMethod.GET, "/usuario/email/confirmRegistration")
		.antMatchers("/usuario/email/reenviar");//redirecionada ao apertar o botao de confirmar email
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/aposta/novo").hasAuthority("CADASTRO_APOSTA") //seção 19.6
				.antMatchers("/equipe/novo").hasAuthority("CADASTRO_EQUIPE") //seção 19.6
				.anyRequest().authenticated()
				.and()
			.formLogin()
			.loginPage("/login") //informando qual a pagina de login
			.permitAll()
			.defaultSuccessUrl("/usuario/dashboard", true) //pagina de redirecionamento quando for feito login
				.and()
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")) //url de logout
				.and()
			.exceptionHandling()
				.accessDeniedPage("/403") //quando a permissao de acesso a uma pagina for negado redirecione para essa pagina
				.and()
			.sessionManagement()
				.maximumSessions(1) //numero maximo de sessões em paralelo por usuario
				.expiredUrl("/login") //quando a sessao for encerrada redirecione para essa pagina
				.and()
				.invalidSessionUrl("/login");  //quando a sessao for encerrada e o usuario tentar enviar alguma requisição POST redirecione para essa pagina
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
