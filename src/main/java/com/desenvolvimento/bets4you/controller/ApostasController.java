package com.desenvolvimento.bets4you.controller;

import javax.persistence.PersistenceException;
import javax.validation.Valid;

import com.desenvolvimento.bets4you.repository.Apostas;
import com.desenvolvimento.bets4you.service.exception.ImpossivelApagarEntidadeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.desenvolvimento.bets4you.model.Aposta;
import com.desenvolvimento.bets4you.repository.Paises;
import com.desenvolvimento.bets4you.service.CadastroApostaService;
import com.desenvolvimento.bets4you.service.CadastroJogoService;

@Controller
@RequestMapping("/aposta")
public class ApostasController {
	
	@Autowired
	private Paises paises;

	@Autowired
	private Apostas apostas;

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
	
	@GetMapping("/lista")
	public ModelAndView pesquisarAposta() {
		ModelAndView mv = new ModelAndView("/aposta/listagem_apostas");
		mv.addObject("apostas", apostas.findByApostasDoMesAtualDesc());
		return mv; 
	}
	
	@PostMapping( value = "/novo")
	public ModelAndView cadastrarAposta(@ModelAttribute @Valid Aposta aposta, BindingResult result, Model model, RedirectAttributes attributes) {
		
		if(result.hasErrors())
		{	
			return novaAposta(aposta);
		}

		try {
			cadastroApostaService.salvar(aposta);

			for(int i = 0;i<aposta.getJogos().size();i++) {
				aposta.getJogos().get(i).setCodAposta(aposta);
				cadastroJogoService.salvar(aposta.getJogos().get(i));
			}
		}
		catch(PersistenceException p) {
			ModelAndView mv = new ModelAndView("500");
			System.out.println(p.getMessage());
			return mv;
		}

		
		return new ModelAndView("redirect:/aposta/novo");
	}

	@GetMapping(value = "/gerenciamentoApostas")
	public ModelAndView gerenciamentoApostas(Model model){
		ModelAndView mv = new ModelAndView("/aposta/gerenciar_apostas");

		model.addAttribute("apostas", apostas.findByApostasDoMesAtualDesc());

		return mv;

	}

	@PostMapping(value = "/gerenciamentoApostas", consumes = { MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ResponseEntity<?> atualizarStatusAposta(@RequestBody @Valid Aposta aposta,  BindingResult result){

		try{
			cadastroApostaService.atualizarStatus(aposta);
		}
		catch(Exception e){
			System.out.println("ERro ao atualizar status da aposta: " + e);
		}

		return ResponseEntity.ok("ok");

	}

	@PostMapping(value = "/atualizarSituacao", consumes = { MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ResponseEntity<?> atualizarSituacaoAposta(@RequestBody @Valid Aposta aposta,  BindingResult result){

		try{
			cadastroApostaService.atualizarSituacao(aposta);
		}
		catch(Exception e){
			System.out.println("ERro ao atualizar situacao da aposta: " + e);
		}

		return ResponseEntity.ok("ok");

	}

	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Long id) {
		try{
			cadastroApostaService.excluir(id);
		}
		catch(ImpossivelApagarEntidadeException e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}


}
