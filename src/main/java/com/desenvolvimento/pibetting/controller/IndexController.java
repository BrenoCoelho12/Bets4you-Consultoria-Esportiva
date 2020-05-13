package com.desenvolvimento.pibetting.controller;

import com.desenvolvimento.pibetting.repository.Planos;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	@GetMapping("/gestaoBanca")
	public ModelAndView gestaoBanca() {
		ModelAndView mv = new ModelAndView("gestao_banca");
		
		return mv;
	}
	
	@RequestMapping("/planos")
	public ModelAndView planos() {
		ModelAndView mv = new ModelAndView("planos");
		mv.addObject("planos", planos.findAll());
		return mv;
	}
}
