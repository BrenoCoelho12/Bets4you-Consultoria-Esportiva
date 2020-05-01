package com.desenvolvimento.pibetting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
	
	@RequestMapping("/")
	public String index() {
		return "index";
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
	public String planos() {
		
		return "planos";
	}
}
