package com.generation.blogpessoal.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.repository.PostagemRepository;
import com.generation.blogpessoal.repository.TemaRepository;

import jakarta.validation.Valid;

//passa tudo por ela e verifica se vai "continuar"
@RestController
//Mapeia o caminho
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostagemController {

	@Autowired
	private PostagemRepository postagemRepository;

	@Autowired
	private TemaRepository temaRepository;

	@GetMapping
	// vai mostrar uma lista preenchida do objeto
	/*
	 * ResponseEntity<List<Postagem>> -> retorno do método ResponseEntity é para
	 * tratar a resposta http, por exemplo 201 como create, 404 como not found,
	 * opcional dependendo do contexto
	 */
	public ResponseEntity<List<Postagem>> getAll() {
		return ResponseEntity.ok(postagemRepository.findAll());
	}

	// Indica que esse método será chamado quando alguém acessar GET /{id}
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> getById(@PathVariable Long id) {

		// Tenta encontrar uma postagem com o ID informado no banco de dados
		return postagemRepository.findById(id)

				// Se encontrar, retorna uma resposta HTTP 200 (OK) com a postagem no corpo
				.map(resposta -> ResponseEntity.ok(resposta))

				// Se não encontrar, retorna uma resposta HTTP 404 (Not Found)
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo) {
		return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));
	}

	@PostMapping
	public ResponseEntity<Postagem> post(@Valid @RequestBody Postagem postagem) {
		if (temaRepository.existsById(postagem.getTema().getId()))
			return ResponseEntity.status(HttpStatus.CREATED).body(postagemRepository.save(postagem));

		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tema não existe!", null);

	}

	@PutMapping
	public ResponseEntity<Postagem> put(@Valid @RequestBody Postagem postagem) {
		if (postagemRepository.existsById(postagem.getId())) {

			if (temaRepository.existsById(postagem.getTema().getId()))
				return ResponseEntity.status(HttpStatus.OK)
						.body(postagemRepository.save(postagem));

			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tema não existe!", null);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	// Indica que, se tudo ocorrer bem, o método retornará HTTP 204 (No Content)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	// Mapeia requisições DELETE para a URL /{id}
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {

		// Tenta encontrar uma postagem com o ID fornecido
		Optional<Postagem> postagem = postagemRepository.findById(id);

		// Se não encontrar a postagem, lança uma exceção com erro 404 (Not Found)
		if (postagem.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);

		// Se encontrar, deleta a postagem do banco de dados
		postagemRepository.deleteById(id);
	}

}
