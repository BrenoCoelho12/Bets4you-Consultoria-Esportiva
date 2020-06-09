package com.desenvolvimento.bets4you.dto;

import com.desenvolvimento.bets4you.model.Situacao;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ApostaDTO {

    private BigDecimal confianca;
    private BigDecimal odd;
    private LocalDate data;
    private Situacao situacao;

    public ApostaDTO(BigDecimal confianca, BigDecimal odd, LocalDate data, Situacao situacao) {
        this.confianca = confianca;
        this.odd = odd;
        this.data = data;
        this.situacao = situacao;
    }

    public BigDecimal getConfianca() {
        return confianca;
    }

    public void setConfianca(BigDecimal confianca) {
        this.confianca = confianca;
    }

    public BigDecimal getOdd() {
        return odd;
    }

    public void setOdd(BigDecimal odd) {
        this.odd = odd;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }
}
