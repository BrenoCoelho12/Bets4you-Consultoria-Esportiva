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
    private BigDecimal valorMensal;

    @NotNull(message = "informe um valor válido.")
    private BigDecimal valorTotal;

    @NotNull(message = "informe um desconto válido.")
    private Long desconto;

    @NotBlank(message = "informe uma duração valida")
    private Long duracao;

    @Column(name = "unidade_tempo")
    @NotNull
    private String unidadeTempo;

    @NotNull
    private Boolean ativo = true;

    @NotNull
    private Long qtdParcelas;

    @Transient
    private BigDecimal valorSemDesconto;

    public Long getQtdParcelas() {
        return qtdParcelas;
    }

    public void setQtdParcelas(Long qtdParcelas) {
        this.qtdParcelas = qtdParcelas;
    }

    public BigDecimal getValorSemDesconto() {
        return valorSemDesconto = new BigDecimal(duracao * 29.99).setScale(2, BigDecimal.ROUND_UP);
    }

    public void setValorSemDesconto(BigDecimal valorSemDesconto) {
        this.valorSemDesconto = valorSemDesconto;
    }

    public BigDecimal getValorMensal() {
        return valorMensal;
    }

    public void setValorMensal(BigDecimal valorMensal) {
        this.valorMensal = valorMensal;
    }

    public Long getDesconto() {
        return desconto;
    }

    public void setDesconto(Long desconto) {
        this.desconto = desconto;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
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


}
