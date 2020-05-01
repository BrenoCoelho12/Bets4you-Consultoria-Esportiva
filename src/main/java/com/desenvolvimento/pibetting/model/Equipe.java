package com.desenvolvimento.pibetting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.util.StringUtils;

@Entity
@Table(name = "equipe")
public class Equipe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min = 3, max = 29, message = "Informe um nome para a equipe (MIN = 3 caracteres, MAX = 29 caracteres)")
	private String nome;
	
	@NotNull(message = "Informe a nacionalidade da equipe.")
	@ManyToOne
	@JoinColumn(name = "nacionalidade_id")
	private Pais nacionalidade;
	
	/*@OneToMany(mappedBy = "")
	private List<Jogo> jogosMandante;*/
	
	@NotBlank(message = "Selecione uma foto")
	private String foto;
	
	@Column(name="content_type")
	private String contentType;
	
	public String getFoto() {
		return foto;
	}
	
	public String getFotoOuImagemVazia() {
		return !StringUtils.isEmpty(foto) ? foto : "nomeImagemVazia.png";
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
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
		Equipe other = (Equipe) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
