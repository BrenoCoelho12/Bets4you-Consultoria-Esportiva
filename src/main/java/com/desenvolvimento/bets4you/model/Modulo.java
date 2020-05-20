package com.desenvolvimento.bets4you.model;

public enum Modulo {
	
	VIP("Vip"),
	GRATIS("Gratis");
	
	private String descricao;
	
	Modulo(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
