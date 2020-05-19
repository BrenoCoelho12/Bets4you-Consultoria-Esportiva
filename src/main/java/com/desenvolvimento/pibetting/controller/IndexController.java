package com.desenvolvimento.pibetting.controller;

import com.desenvolvimento.pibetting.repository.Planos;
import com.desenvolvimento.pibetting.security.UsuarioSistema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class IndexController {

	@Autowired
	private Planos planos;
	
	@RequestMapping("/")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("index");

		return mv;
	}
	
	@GetMapping("/dicas")
	public ModelAndView dicas() {
		ModelAndView mv = new ModelAndView("dicas");
		
		return mv;
	}
	
	@RequestMapping("/gestao-de-banca")
	public ModelAndView gestaoBanca() {
		ModelAndView mv = new ModelAndView("gestao_banca");
		
		return mv;
	}

	@RequestMapping("/gestao-de-banca-usuario")
	/*foi necessario criar esse método porque a aba de
	gestão de banca encontra-se tanto na barra de navegação1
	quanto na barra de navegação2... Se n houvessem 2 htmls
	para barra de navegação, haveria problema na hora do redirecionamento
	ao clicar no link da logo
	 */
	public ModelAndView gestaoBancaUsuarioLogado() {
		ModelAndView mv = new ModelAndView("gestao_banca2");

		return mv;
	}

	@RequestMapping("/como-trabalhamos")
	public ModelAndView comoTrabalhamos() {
		ModelAndView mv = new ModelAndView("como_trabalhamos");

		return mv;
	}
	
	@RequestMapping("/planos")
	public ModelAndView planos(@AuthenticationPrincipal UsuarioSistema usuario) {
		ModelAndView mv = new ModelAndView("planos");
		mv.addObject("planos", planos.findAll());
		mv.addObject("usuario", usuario.getUsuario());
		return mv;
	}
}
