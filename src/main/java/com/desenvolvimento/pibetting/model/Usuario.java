package com.desenvolvimento.pibetting.model;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.desenvolvimento.pibetting.validation.AtributoConfirmacao;

@AtributoConfirmacao(atributo = "senha", atributoConfirmacao = "confirmacaoSenha", message = "Confirmação de senha não confere")
@Entity
@Table(name = "usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Campo nome não pode ser vazio")
	@Size(max = 99, message = "O campo nome pode ter até 99 caracteres")
	private String nome;
	
	@NotBlank(message = "Campo email não pode ser vazio")
	private String email;
	
	@Size(min = 6, message = "A senha deve conter de 6 a 12 caracteres")
	private String senha;
	
	@NotNull
	@Column(name = "acesso_vip")
	private Boolean acessoVip = false;

	@NotNull(message = "Informe a nacionalidade da equipe.")
	@ManyToOne
	@JoinColumn(name = "nacionalidade_id")
	private Pais nacionalidade;
	
	@Transient
	private String confirmacaoSenha;

	@ManyToMany
	@JoinTable(name = "usuario_grupo", joinColumns = @JoinColumn(name = "codigo_usuario")
				, inverseJoinColumns = @JoinColumn(name = "codigo_grupo"))	
	private List<Grupo> grupos;

	@Column(name ="email_validation")
	private Boolean emailValidation = false;

	public Boolean getEmailValidation() {
		return emailValidation;
	}

	public void setEmailValidation(Boolean emailValidation) {
		this.emailValidation = emailValidation;
	}
	
	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}

	public void setConfirmacaoSenha(String confirmacaoSenha) {
		this.confirmacaoSenha = confirmacaoSenha;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Boolean getAcessoVip() {
		return acessoVip;
	}

	public void setAcessoVip(Boolean acessoVip) {
		this.acessoVip = acessoVip;
	}

	public Pais getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(Pais nacionalidade) {
		this.nacionalidade = nacionalidade;
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
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
