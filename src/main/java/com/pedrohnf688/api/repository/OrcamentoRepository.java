package com.pedrohnf688.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.pedrohnf688.api.modelo.Orcamento;

@Repository
public interface OrcamentoRepository extends PagingAndSortingRepository<Orcamento, Long> {

	Page<Orcamento> findAllByProjetoId(Long projetoId, Pageable pageable);
}
