package com.pedrohnf688.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pedrohnf688.api.modelo.Projeto;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

}
