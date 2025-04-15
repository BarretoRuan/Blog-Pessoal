package com.generation.blogpessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.blogpessoal.model.Postagem;

//interface de acesso ao metodos, vai herdar todas estrutura da JPA
public interface PostagemRepository extends JpaRepository<Postagem, Long> {

	// pega as informações e ignora letras maiusculas(metodo personalizado)
	public List<Postagem> findAllByTituloContainingIgnoreCase(@Param("titulo") String titulo);

}
