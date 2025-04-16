package com.generation.blogpessoal.model;

import java.time.LocalDate;

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

//vai transformal a class em tabela artaves do JPA
@Entity
//define o nome da tabela, caso o não defina criará um nome generico
@Table(name = "tb_postagens")
public class Postagem {

	@Id
	// class primaria
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// AUTOCREMENT
	private Long id;

	// Pode ser preenchido mas tambem pode ser nulo (VARCHAR)
	@NotBlank
	// Respeitar o campo e ser preenchido com o minimo e o max de caracteres
	@Size(min = 3, max = 100)
	private String titulo;

	@NotBlank
	@Size(min = 10, max = 1000)
	private String texto;

	// Pega data e hora do sistema automaticamente e guarda no sistema
	@UpdateTimestamp
	private LocalDate dataDate;

	// Vai definar a chave estrangeira
	@ManyToOne
	// Vai ignorar as propriedades de postagem
	@JsonIgnoreProperties("postagem")
	private Tema tema;

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

	public LocalDate getDataDate() {
		return dataDate;
	}

	public void setDataDate(LocalDate dataDate) {
		this.dataDate = dataDate;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

}
