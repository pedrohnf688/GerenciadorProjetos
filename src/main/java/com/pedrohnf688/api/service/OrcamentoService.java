package com.pedrohnf688.api.service;

import java.util.List;
import java.util.Optional;

import com.pedrohnf688.api.modelo.Orcamento;

public interface OrcamentoService {

	Optional<Orcamento> buscarPorId(Long id);

	Optional<Orcamento> salvar(Orcamento orcamento);

	List<Orcamento> listar();

	void deletarPorId(Long id);
	
	List<Orcamento> findAllByProjetoId(Long projetoId);

}
