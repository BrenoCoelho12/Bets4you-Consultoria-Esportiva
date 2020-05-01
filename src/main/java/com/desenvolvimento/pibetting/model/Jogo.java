package com.desenvolvimento.pibetting.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "jogo")
public class Jogo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Campo sugestão não pode ser vazio.")
	@Size(min = 5, max = 50, message = "Quantidade de caracteres para a sugestão: min. = 5, max. = 49.")
	private String sugestao;
	
	@NotNull(message = "Informe uma odd válida.")
	@DecimalMin(value="1.00", message = "A odd mínima permitida é de 1.00")
	private BigDecimal odd;
	
	@JoinColumn(name = "aposta_id")
	@ManyToOne
	private Aposta codAposta;
	
	@NotNull(message = "Selecione a equipe mandante do jogo.")
	@ManyToOne
	@JoinColumn(name="mandante_id")
	private Equipe mandante;
	
	@NotNull(message = "Selecione a equipe visitante do jogo.")
	@ManyToOne
	@JoinColumn(name="visitante_id")
	private Equipe visitante;
	
	@PrePersist // Antes de persistir(salvar) qualquer Jogo no banco
	@PreUpdate // Antes de atualizar qualquer Jogo no banco
	private void PrePersistUpdate() {
		sugestao = sugestao.toUpperCase();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSugestao() {
		return sugestao;
	}

	public void setSugestao(String sugestao) {
		this.sugestao = sugestao;
	}

	public BigDecimal getOdd() {
		return odd;
	}

	public void setOdd(BigDecimal odd) {
		this.odd = odd;
	}

	public Aposta getCodAposta() {
		return codAposta;
	}

	public void setCodAposta(Aposta codAposta) {
		this.codAposta = codAposta;
	}

	public Equipe getMandante() {
		return mandante;
	}

	public void setMandante(Equipe mandante) {
		this.mandante = mandante;
	}

	public Equipe getVisitante() {
		return visitante;
	}

	public void setVisitante(Equipe visitante) {
		this.visitante = visitante;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jogo other = (Jogo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
