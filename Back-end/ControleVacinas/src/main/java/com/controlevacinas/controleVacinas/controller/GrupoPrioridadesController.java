package com.controlevacinas.controleVacinas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.controlevacinas.controleVacinas.model.GrupoPrioridades;
import com.controlevacinas.controleVacinas.repository.GrupoPrioridadesRepository;

@RestController
@RequestMapping("/grupo-prioridades")
@CrossOrigin(origins = {"http://localhost:3000"})
public class GrupoPrioridadesController {
	
	@Autowired
	private GrupoPrioridadesRepository grupoRepository;
	
	@GetMapping
	public ResponseEntity<List<GrupoPrioridades>> listarTodos() {
		return ResponseEntity.ok(grupoRepository.findAll());
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<GrupoPrioridades> buscarPeloCodigo(@PathVariable Long codigo){
		return grupoRepository.findById(codigo)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

}
