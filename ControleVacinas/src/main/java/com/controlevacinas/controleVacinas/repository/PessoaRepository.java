package com.controlevacinas.controleVacinas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.controlevacinas.controleVacinas.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}
