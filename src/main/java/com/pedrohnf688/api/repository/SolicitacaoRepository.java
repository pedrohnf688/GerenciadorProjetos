package com.pedrohnf688.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.pedrohnf688.api.modelo.Solicitacao;

@Repository
public interface SolicitacaoRepository extends PagingAndSortingRepository<Solicitacao, Long> {

	Page<Solicitacao> findAllByUsuarioId(Long usuarioId, Pageable pageable);
	
}
