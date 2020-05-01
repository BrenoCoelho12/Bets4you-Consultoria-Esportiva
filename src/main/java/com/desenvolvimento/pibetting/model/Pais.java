package com.desenvolvimento.pibetting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "pais")
public class Pais {
	
	@Id
	private Long id;
	
	@NotBlank(message = "Campo nome não pode ser vazio.")
	@Size(min = 1, max = 59, message = "Tamanho mínimo = 1 caracter. Tamanho máximo = 59 caracteres.")
	private String nome;
	
	@NotBlank(message = "Campo nome não pode ser vazio.")
	@Size(min = 1, max = 59, message = "Tamanho mínimo = 1 caracter. Tamanho máximo = 59 caracteres.")
	@Column(name = "nome_pt")
	private String nomePortugues;
	
	@NotBlank(message = "Campo sigla não pode ser vazio.")
	@Size(min = 2, max = 2, message = "Quantidade caracteres inválido.")
	private String sigla;
	
	@NotNull
	private Integer bacen;
	
	/*@OneToMany(mappedBy = "nacionalidade")
	private List<Equipe> equipes;
	
	@OneToMany(mappedBy = "nacionalidade")
	private List<Usuario> usuarios;*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomePortugues() {
		return nomePortugues;
	}

	public void setNomePortugues(String nomePortugues) {
		this.nomePortugues = nomePortugues;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public Integer getBacen() {
		return bacen;
	}

	public void setBacen(Integer bacen) {
		this.bacen = bacen;
	}

	/*
	public List<Equipe> getEquipes() {
		return equipes;
	}

	public void setEquipes(List<Equipe> equipes) {
		this.equipes = equipes;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}*/

	

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
		Pais other = (Pais) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
