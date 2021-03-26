package com.controlevacinas.controleVacinas.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.controlevacinas.controleVacinas.model.GrupoPrioridades;

public interface GrupoPrioridadesRepository extends JpaRepository<GrupoPrioridades, Long> {

	List<GrupoPrioridades> findAll();
	Optional<GrupoPrioridades> findById(Long codigo);

	
}
