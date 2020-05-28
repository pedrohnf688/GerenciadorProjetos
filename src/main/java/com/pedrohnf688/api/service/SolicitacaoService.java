package com.pedrohnf688.api.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.pedrohnf688.api.modelo.Solicitacao;

public interface SolicitacaoService {
	
	Optional<Solicitacao> buscarPorId(Long id);

	Optional<Solicitacao> salvar(Solicitacao solicitacao);

	Page<Solicitacao> listar(Pageable pageable);

	void deletarPorId(Long id);

	Page<Solicitacao> findAllByUsuarioId(Long usuarioId, PageRequest pageRequest);
}
