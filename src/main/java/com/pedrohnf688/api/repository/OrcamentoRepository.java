package com.pedrohnf688.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pedrohnf688.api.modelo.Orcamento;

@Repository
public interface OrcamentoRepository extends JpaRepository<Orcamento, Long>{

	@Query(value = "SELECT * FROM Orcamento o WHERE o.projeto.id = :projetoId", nativeQuery = true)
	List<Orcamento> findAllByProjetoId(@Param("projetoId") Long projetoId);
}
