package com.desenvolvimento.bets4you.controller;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.desenvolvimento.bets4you.mail.Mailer;
import com.desenvolvimento.bets4you.model.TokenValidation;
import com.desenvolvimento.bets4you.repository.Apostas;
import com.desenvolvimento.bets4you.repository.Tokens;
import com.desenvolvimento.bets4you.repository.Usuarios;
import com.desenvolvimento.bets4you.service.CadastroTokenService;
import com.desenvolvimento.bets4you.service.CadastroUsuarioPlanoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.desenvolvimento.bets4you.model.Usuario;
import com.desenvolvimento.bets4you.repository.Paises;
import com.desenvolvimento.bets4you.security.UsuarioSistema;
import com.desenvolvimento.bets4you.service.CadastroUsuarioService;
import com.desenvolvimento.bets4you.service.exception.EmailUsuarioCadastradoException;

import java.util.Optional;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private Paises paises;

	@Autowired
	private Tokens tokens;

	@Autowired
	private Apostas apostas;

	@Autowired
	private Usuarios usuarios;

	@Autowired
	private CadastroUsuarioService CadastroUsuarioService;

	@Autowired
	private CadastroTokenService cadastroTokenService;

	@Autowired
	private CadastroUsuarioPlanoService cadastroUsuarioPlanoService;

	@Autowired
	private Mailer mailer;
	
	@RequestMapping("/dashboard")
	public ModelAndView menuInicial(@AuthenticationPrincipal UsuarioSistema usuario) {
		ModelAndView mv = new ModelAndView("/usuario/dashboard");

		mv.addObject("usuarioVip", usuario.getUsuario().getAcessoVip());
		mv.addObject("apostas", apostas.findByStatus(true)); //pegando as apostas do dia (ou seja, as que estão com status = true)
		return mv;
	}

	/*
	@PostMapping("/addPlano")
	public void adicionarPlano(@Valid UsuarioPlano usuarioPlano, BindingResult result){


		if(!result.hasErrors()){
			cadastroUsuarioPlanoService.cadastrar(usuarioPlano);
			CadastroUsuarioService.setUsuarioVip(usuarioPlano.getUsuario());
		}
	}*/

	@RequestMapping("/perfil")
	public ModelAndView perfil(@AuthenticationPrincipal UsuarioSistema usuario){
		ModelAndView mv = new ModelAndView("/usuario/perfil");
		mv.addObject("usuario", usuario.getUsuario());

		String descricaoPlano = cadastroUsuarioPlanoService.verificarPlanoUsuario(usuario.getUsuario());

		mv.addObject("descricaoPlano", descricaoPlano);

		return mv;
	}
	
	@RequestMapping("/novo")
	public ModelAndView novoUsuario(Usuario usuario) {
		ModelAndView mv = new ModelAndView("/usuario/cadastro_usuario");
		mv.addObject("paises", paises.findAll());
		
		
		return mv;
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView cadastrarUsuario(@Valid Usuario usuario, BindingResult result, Model model, RedirectAttributes attributes, HttpServletRequest request) {
		
		if(result.hasErrors()) {
			return novoUsuario(usuario);
		}
		
		try {
			CadastroUsuarioService.salvar(usuario);
			String token = cadastroTokenService.newToken(usuario);
			mailer.confirmacaoEmail(usuario, token, request);
			return aguardandoConfirmacaoEmail(usuario, token);
		}
		
		catch(EmailUsuarioCadastradoException e) {
			result.rejectValue("email", e.getMessage(), e.getMessage());
			return novoUsuario(usuario);
		}
		

	}

	@RequestMapping("/aguardandoConfirmacaoEmail")
	public ModelAndView aguardandoConfirmacaoEmail(Usuario usuario, String token) {
		ModelAndView mv = new ModelAndView("/usuario/aguardando_confirmacao_email");

		mv.addObject("token", token);
		mv.addObject("email", usuario.getEmail());
		return mv;
	}

	@GetMapping("/email/confirmRegistration")
	public String confirmacaoEmail(@RequestParam("token") String tokenUsuario) {
		Optional<TokenValidation> token = tokens.findByToken(tokenUsuario); //recuperando o TokenValidation do banco de dados a partir do token criado para o usuario
		TokenValidation tokenValidation = token.get(); //transformando o Optional<TokenValidation> em um Objeto TokenValidation

		if(token.isPresent() && !tokenValidation.getUsuario().getEmailValidation()){ //verificando se existia ao menos 1 TokenValidation no banco que condiz com o tokenUsuario (token criado para o usuario) e se a coluna email_validation do usuário ainda é falsa

			try{
				usuarios.atualizarEmailValidation(tokenValidation.getUsuario().getId());//atualizando o boolean token_validation para true, de modo a ter certeza que o usuario confirmou seu email

				/* TEMPORÁRIO */
				cadastroUsuarioPlanoService.addPlanoVip(tokenValidation.getUsuario());
				usuarios.atualizarAcessoVip(tokenValidation.getUsuario().getId());
				/* TEMPORÁRIO */

				return "/usuario/token_valido"; //redirecionando para o html token_valido
			}
			catch (PersistenceException e){
				System.out.println(e);
				return "500";
			}


		}

		return "/usuario/token_invalido";

	}

	@PostMapping(value = "/email/reenviar", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ResponseEntity reenviarEmail(@RequestBody @Valid TokenValidation token, HttpServletRequest request) {
		//inicialmente o objeto token (do tipo TokenValidation) só contém o valor da string token
		Optional<TokenValidation> tokenOptional = tokens.findByToken(token.getToken()); //recebendo o TokenValidation do banco de dados a partir da string token recebida via json

		if(tokenOptional.isPresent()){ //verificando se há ao menos 1 registro no banco
			token = tokenOptional.get(); //setando o valor do TokenValidation para a variavel token
			mailer.confirmacaoEmail(token.getUsuario(), token.getToken(), request); //reenviando o email
			return ResponseEntity.ok("reenviado");
		}


		return ResponseEntity.badRequest().body("Erro");
	}

	@GetMapping("/resetPassword")
	public ModelAndView resetPassword(Usuario usuario){
		ModelAndView mv = new ModelAndView("usuario/esqueci_senha");

		return mv;
	}

	@PostMapping("/resetPassword")
	public ModelAndView sendResetPassword(Usuario usuario, HttpServletRequest request){
		Optional<Usuario> usuarioOptional = usuarios.findByEmailIgnoreCase(usuario.getEmail());

		if(usuarioOptional.isPresent() && usuarioOptional.get().getEmailValidation() == true) {
			Optional<TokenValidation> token = tokens.findByUsuario(usuarioOptional.get());
			mailer.emailResetPassword(usuarioOptional.get(), token.get().getToken(), request);
			return resetPassword(usuario).addObject("mensagem", "Email de recuperação enviado para: " + usuarioOptional.get().getEmail());
		}

		else {
			return resetPassword(usuario).addObject("mensagem", "Usuário não encontrado.");
		}

	}

	@GetMapping("/email/confirmPassword")
	public ModelAndView confirmPassword(@RequestParam("token") String tokenUsuario) {
		Optional<TokenValidation> token = tokens.findByToken(tokenUsuario); //recuperando o TokenValidation do banco de dados a partir do token criado para o usuario

		if(token.isPresent()){ //verificando se existia ao menos 1 TokenValidation no banco que condiz com o tokenUsuario (token criado para o usuario)

			return new ModelAndView("/usuario/nova_senha").addObject("usuario", token.get().getUsuario());

		}

		return new ModelAndView("500");

	}


	@RequestMapping(value = "/email/confirmPassword", method = RequestMethod.POST)
	public ModelAndView newPassword(Usuario usuario) {

		ModelAndView mv = new ModelAndView("/usuario/nova_senha");

		if(usuario.getSenha().length() < 6){
            mv.addObject("mensagemErro", "A senha deve conter no mínimo 6 caracteres.");
            return mv;
        }

		else if(!(usuario.getSenha().equals(usuario.getConfirmacaoSenha()))) {
			mv.addObject("mensagemErro", "As senhas não conferem!");
			return mv;
		}

		try {
			CadastroUsuarioService.atualizarSenha(usuario, usuario.getSenha());
			mv.addObject("mensagemSucesso", "Senha atualizada com sucesso!");
			return mv;
		}
		catch(Exception e) {
			mv.addObject("mensagemErro", "Erro ao atualizar senha. Houve um erro inesperado.  Entre em contato conosco.");
			return mv;
		}

	}


}
