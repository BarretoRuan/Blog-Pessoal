package com.generation.blogpessoal.model;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity //vai transformal a class em tabela artaves do JPA
@Table(name = "tb_postagens") //define o nome da tabela, caso o não defina criará um nome generico
public class Postagem {

	@Id // class primaria
	@GeneratedValue(strategy = GenerationType.IDENTITY) // AUTOCREMENT
	private Long id;

	@NotBlank (message = "O atributo texto é Obrigatório!")// Pode ser preenchido mas tambem pode ser nulo (VARCHAR)
	@Size(min = 3, max = 100, message = "O atributo título deve conter no mínimo 05 e no máximo 100 caracteres") // Respeitar o campo e ser preenchido com o minimo e o max de caracteres
	private String titulo;

	@NotBlank (message = "O atributo título é Obrigatório!")
	@Size(min = 10, max = 1000)
	private String texto;

	// Pega data e hora do sistema automaticamente e guarda no sistema
	@UpdateTimestamp
	private Instant dataDate;

	
	@ManyToOne // Vai definar a chave estrangeira
	@JsonIgnoreProperties("postagem") // Vai ignorar as propriedades de postagem
	private Tema tema;
	
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Usuario usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}


	public Instant getDataDate() {
		return dataDate;
	}

	public void setDataDate(Instant dataDate) {
		this.dataDate = dataDate;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

}
