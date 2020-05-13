package com.desenvolvimento.pibetting.model;

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
    private String duracao;

    @NotNull(message = "informe se o plano tem renovação automática")
    private Boolean renovacaoAutomatica;

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

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }
}
