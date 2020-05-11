package com.desenvolvimento.pibetting.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import jdk.nashorn.internal.objects.annotations.Getter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "aposta")
public class Aposta implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "A confiança da aposta não pode ser nula.")
	@DecimalMin(value="1.00", message = "A confiança mínima deve ser 1")
	//@DecimalMax(value="5.00", message = "A confiança máxima deve ser 5")
	private BigDecimal confianca;
	
	@Size(max = 199, message = "Quantidade de caracteres inválido. Tamanho máximo = 199 caracteres.")
	@NotBlank(message = "Informe uma descrição para a aposta.")
	@Column(name = "descricao_aposta")
	private String descricaoAposta;
	
	@NotNull(message = "A odd da aposta não pode ser nula.")
	@DecimalMin(value="1.00", message = "A odd mínima permitida é de 1.00")
	private BigDecimal odd;
	
	@NotNull(message = "Quantidade de jogos inválido.")
	@Column(name="qtd_jogos")
	@Min(value=1, message = "A quantidade mínima de jogos é 1.")
	private Integer qtdJogos; 
	
	@Column(name="created_in")
	private Date data = new Date();

	@NotNull(message = "Insira corretamente o(s) jogo(s).")
	@Size(min = 1, message = "A quantidade mínima de jogos é 1.")
	@OneToMany(mappedBy = "codAposta")
	@Fetch(FetchMode.JOIN)
	private List<Jogo> jogos;

	@PrePersist //PrePersist - Antes de persistir (salvar) qualquer conteudo no banco 
	@PreUpdate  // PreUpdate -  Antes de atualizar qualquer conteudo
	private void PrePersistUpdate() {
		descricaoAposta = descricaoAposta.toUpperCase();
	}

	@Enumerated(EnumType.STRING)
	private Modulo modulo;

	@Column(name = "status")
	private Boolean status = false;

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getConfianca() {
		return confianca;
	}

	public void setConfianca(BigDecimal confianca) {
		this.confianca = confianca;
	}

	public String getDescricaoAposta() {
		return descricaoAposta;
	}

	public void setDescricaoAposta(String descricaoAposta) {
		this.descricaoAposta = descricaoAposta;
	}

	public BigDecimal getOdd() {
		return odd;
	}

	public void setOdd(BigDecimal odd) {
		this.odd = odd;
	}

	public Integer getQtdJogos() {
		return qtdJogos;
	}

	public void setQtdJogos(Integer qtdJogos) {
		this.qtdJogos = qtdJogos;
	}

	public List<Jogo> getJogos() { return jogos; }

	public void setJogos(List<Jogo> jogos) {
		this.jogos = jogos;
	}

	public Modulo getModulo() {
		return modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

	public Date getData() { return data; }

	public void setData(Date data) { this.data = data; }

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
		Aposta other = (Aposta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
