package com.desenvolvimento.pibetting.controller;

import java.util.List;

import javax.validation.Valid;

import com.desenvolvimento.pibetting.repository.Equipes;
import com.desenvolvimento.pibetting.repository.filter.EquipeFilter;
import com.desenvolvimento.pibetting.service.exception.EquipeCadastradaException;
import com.desenvolvimento.pibetting.service.exception.ImpossivelApagarEntidadeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.desenvolvimento.pibetting.model.Equipe;
import com.desenvolvimento.pibetting.model.Pais;
import com.desenvolvimento.pibetting.repository.Paises;
import com.desenvolvimento.pibetting.service.BuscarEquipesPorNacionalidade;
import com.desenvolvimento.pibetting.service.CadastroEquipeService;

@Controller 
@RequestMapping("/equipe")
public class EquipeController {
	
	@Autowired
	private Paises paises;
	
	@Autowired
	private BuscarEquipesPorNacionalidade buscarEquipesPorNacionalidade;
	
	@Autowired
	private CadastroEquipeService cadastroEquipeService;

	@Autowired
	private Equipes equipes;
	
	@RequestMapping("/novo")
	public ModelAndView novaEquipe(Equipe equipe) { 
		ModelAndView mv = new ModelAndView("equipe/cadastro_equipe");
		
		mv.addObject("paises", paises.findAll());
		
		return mv;
	}
	
	@RequestMapping(value="/novo", method = RequestMethod.POST)
	public ModelAndView cadastrarEquipe(@Valid Equipe equipe, BindingResult result, Model model, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			return novaEquipe(equipe);
		}

		try {
			cadastroEquipeService.salvar(equipe);
		}
		catch(EquipeCadastradaException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novaEquipe(equipe);
		}

		attributes.addFlashAttribute("mensagem", "Equipe cadastrada com sucesso.");
		return new ModelAndView("redirect:/equipe/novo");
	}
	
	@RequestMapping(value = "/buscar", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ResponseEntity<?> carregarEquipes(@RequestBody @Valid Pais pais, BindingResult result) {
		
		List<Equipe> equipes;
		equipes = buscarEquipesPorNacionalidade.buscarEquipesPorNacionalidade(pais);
		return ResponseEntity.ok(equipes);
	}

	@GetMapping("/lista")
	public ModelAndView pesquisarEquipe(EquipeFilter equipeFilter, BindingResult result){
		ModelAndView mv = new ModelAndView("/equipe/listagem_equipes");
		mv.addObject("paises", paises.findAll());
		mv.addObject("equipes", equipes.filtrar(equipeFilter));
		return mv;
	}

	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Long id) {
		try{
			cadastroEquipeService.excluir(id);
		}
		catch(ImpossivelApagarEntidadeException e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}

}
