package com.desenvolvimento.bets4you.model;

public enum Situacao {

    VENCIDA("VENCIDA"),
    PERDIDA("PERDIDA");

    private String descricao;

    Situacao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
