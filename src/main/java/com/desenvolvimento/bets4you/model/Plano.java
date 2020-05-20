package com.desenvolvimento.bets4you.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "plano")
public class Plano {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "informe uma descrição válida.")
    private String descricao;

    @NotNull(message = "informe um valor válido.")
    private BigDecimal valor;

    @NotNull(message = "informe um desconto válido.")
    private BigDecimal desconto;

    @NotBlank(message = "informe uma duração valida")
    private Long duracao;

    @NotNull(message = "informe se o plano tem renovação automática")
    @Column(name = "renovacao_automatica")
    private Boolean renovacaoAutomatica;

    @Column(name = "unidade_tempo")
    @NotNull
    private String unidadeTempo;

    @NotNull
    private Boolean ativo = true;

    @Transient
    private BigDecimal valorTotal;

    @Transient
    private BigDecimal valorComDesconto;

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public BigDecimal getValorTotal() {
        return valor.multiply(new BigDecimal(duracao));
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal getValorComDesconto() {
        return valor.subtract(valor.multiply(desconto.divide(new BigDecimal(100))));
    }

    public void setValorComDesconto(BigDecimal valorComDesconto) {
        this.valorComDesconto = valorComDesconto;
    }

    public Long getDuracao() {
        return duracao;
    }

    public void setDuracao(Long duracao) {
        this.duracao = duracao;
    }

    public String getUnidadeTempo() {
        return unidadeTempo;
    }

    public void setUnidadeTempo(String unidadeTempo) {
        this.unidadeTempo = unidadeTempo;
    }

    public Boolean getRenovacaoAutomatica() {
        return renovacaoAutomatica;
    }

    public void setRenovacaoAutomatica(Boolean renovacaoAutomatica) {
        this.renovacaoAutomatica = renovacaoAutomatica;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

}
