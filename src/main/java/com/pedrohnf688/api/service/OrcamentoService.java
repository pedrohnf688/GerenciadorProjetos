package com.pedrohnf688.api.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.pedrohnf688.api.modelo.Orcamento;

public interface OrcamentoService {

	Optional<Orcamento> buscarPorId(Long id);

	Optional<Orcamento> salvar(Orcamento orcamento);

	Page<Orcamento> listar(Pageable pageable);

	void deletarPorId(Long id);

	Page<Orcamento> findAllByProjetoId(Long projetoId, PageRequest pageRequest);

}
