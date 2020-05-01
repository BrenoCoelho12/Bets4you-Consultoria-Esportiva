package com.desenvolvimento.pibetting.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.desenvolvimento.pibetting.model.Aposta;
import com.desenvolvimento.pibetting.repository.Paises;
import com.desenvolvimento.pibetting.service.CadastroApostaService;
import com.desenvolvimento.pibetting.service.CadastroJogoService;

@Controller
@RequestMapping("/aposta")
public class ApostasController {
	
	@Autowired
	private Paises paises;

	@Autowired
	private CadastroJogoService cadastroJogoService;

	@Autowired
	private CadastroApostaService cadastroApostaService;
	
	@RequestMapping("/novo")
	public ModelAndView novaAposta(Aposta aposta) {
		
		ModelAndView mv = new ModelAndView("aposta/cadastro_aposta");
		mv.addObject("paises", paises.findAll());
		return mv;
	}
	
	@GetMapping("/pesquisar")
	public ModelAndView pesquisarAposta() {
		ModelAndView mv = new ModelAndView("/aposta/pesquisar_aposta");
		
		return mv; 
	}
	
	@PostMapping( value = "/novo")
	public ModelAndView cadastrarAposta(@ModelAttribute @Valid Aposta aposta, BindingResult result, Model model, RedirectAttributes attributes) {
		
		if(result.hasErrors())
		{	
			return novaAposta(aposta);
		}
		
		cadastroApostaService.salvar(aposta);
		
		for(int i = 0;i<aposta.getJogos().size();i++) {
			aposta.getJogos().get(i).setCodAposta(aposta);
			cadastroJogoService.salvar(aposta.getJogos().get(i));
		}
		
		return new ModelAndView("redirect:/aposta/novo");
	}	
		
}
