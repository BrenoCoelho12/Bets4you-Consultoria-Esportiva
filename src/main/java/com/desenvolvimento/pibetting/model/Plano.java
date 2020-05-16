package com.desenvolvimento.pibetting.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

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



    public BigDecimal getValorTotal() {
        return valor.multiply(new BigDecimal(duracao));
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
