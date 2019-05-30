package br.com.etechoracio.apiexemplo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.etechoracio.apiexemplo.dao.PersonagemDAO;
import br.com.etechoracio.apiexemplo.model.Personagem;

@RestController
@RequestMapping("/personagens")
public class PersonagemController {

	@Autowired
	private PersonagemDAO dao;

	@GetMapping
	public List<Personagem> listar() {
		return dao.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Personagem> buscar(@PathVariable Long id) {
		Optional<Personagem> resultado = dao.findById(id);
		if(resultado.isPresent()) {
			return ResponseEntity.ok(resultado.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public void inserir(@RequestBody Personagem p) {
		dao.save(p);
	}
}
