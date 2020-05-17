package com.pedrohnf688.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pedrohnf688.api.modelo.Orcamento;

@Repository
public interface OrcamentoRepository extends JpaRepository<Orcamento, Long>{

	List<Orcamento> findAllByProjetoId(Long projetoId);
}
