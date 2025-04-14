package com.generation.blogpessoal.model;

import java.time.LocalDate;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
//vai transformal a class em tabela artaves do JPA
@Entity 
//define o nome da tabela, caso o não defina criará um nome generico
@Table(name = "tb_postagens") 
public class Postagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//Pode ser tanto preenchido como tambem nulo
	@NotBlank 
	// Respeitar o campo e ser preenchido com o minimoe o max de caracteres
	@Size (min = 3 , max = 100) 
	private String titulo;
	
	@NotBlank 
	@Size (min = 10 , max = 1000)
	private String texto;
	
	@UpdateTimestamp
	private LocalDate dataDate;

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
	
	
	
	
}
