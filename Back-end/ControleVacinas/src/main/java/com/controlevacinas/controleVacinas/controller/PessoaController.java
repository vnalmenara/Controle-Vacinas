package com.controlevacinas.controleVacinas.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.controlevacinas.controleVacinas.model.Pessoa;
import com.controlevacinas.controleVacinas.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoas")
@CrossOrigin(origins = {"http://localhost:3000"})
public class PessoaController {

	@Autowired
	private PessoaRepository pessoaRepository;

	@GetMapping
	public ResponseEntity<List<Pessoa>> listarTodos() {
		return ResponseEntity.ok(pessoaRepository.findAll());
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Pessoa> buscarPeloCodigo(@PathVariable Long codigo){
		return pessoaRepository.findById(codigo)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ResponseEntity<Pessoa> cadastraPessoa(@RequestBody Pessoa pessoa) {
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaRepository.save(pessoa));
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Pessoa> atualizar(@PathVariable("codigo") Long codigo, @RequestBody Pessoa pessoa) {
		return pessoaRepository.findById(codigo).map(record -> {
			record.setCpf(pessoa.getCpf());
			record.setDataNascimento(pessoa.getDataNascimento());
			record.setEmail(pessoa.getEmail());
			record.setIdade(pessoa.getIdade());
			record.setNome(pessoa.getNome());
			record.setTelefone(pessoa.getTelefone());
			record.setVacinada(pessoa.getVacinada());
			Pessoa pessoaAtualizada = pessoaRepository.save(pessoa);
			return ResponseEntity.ok().body(pessoaAtualizada);
		}).orElse(ResponseEntity.notFound().build());
	}

}
