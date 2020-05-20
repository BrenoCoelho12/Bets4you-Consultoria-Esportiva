package com.desenvolvimento.bets4you.repository.filter;

import com.desenvolvimento.bets4you.model.Pais;

public class EquipeFilter {

    private String nome;

    private Pais nacionalidade;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Pais getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(Pais nacionalidade) {
        this.nacionalidade = nacionalidade;
    }


}
