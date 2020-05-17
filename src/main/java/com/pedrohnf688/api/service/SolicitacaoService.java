package com.pedrohnf688.api.service;

import java.util.List;
import java.util.Optional;

import com.pedrohnf688.api.modelo.Solicitacao;

public interface SolicitacaoService {
	
	Optional<Solicitacao> buscarPorId(Long id);

	Optional<Solicitacao> salvar(Solicitacao solicitacao);

	List<Solicitacao> listar();

	void deletarPorId(Long id);

	List<Solicitacao> findAllByUsuarioId(Long usuarioId);
}
