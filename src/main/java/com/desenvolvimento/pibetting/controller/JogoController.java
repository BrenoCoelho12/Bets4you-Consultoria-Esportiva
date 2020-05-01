package com.desenvolvimento.pibetting.controller;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.desenvolvimento.pibetting.model.Jogo;

@Controller
@RequestMapping("/jogo")
public class JogoController {

	@RequestMapping(value = "/verificar", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ResponseEntity<?> verificarJogo(@RequestBody @Valid Jogo jogo, BindingResult result) {
		
		if(result.hasErrors()) {
			
			return ResponseEntity.badRequest().body(result.getFieldError().getDefaultMessage());
		}
		return ResponseEntity.ok(jogo);
	}

}
