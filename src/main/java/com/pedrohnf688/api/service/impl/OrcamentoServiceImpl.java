package com.pedrohnf688.api.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pedrohnf688.api.modelo.Orcamento;
import com.pedrohnf688.api.repository.OrcamentoRepository;
import com.pedrohnf688.api.service.OrcamentoService;

@Service
@Transactional(readOnly = false)
public class OrcamentoServiceImpl implements OrcamentoService {

	@Autowired
	private OrcamentoRepository or;

	@Override
	@Transactional(readOnly = true)
	public Optional<Orcamento> buscarPorId(Long id) {
		return this.or.findById(id);
	}

	@Override
	public Optional<Orcamento> salvar(Orcamento orcamento) {
		return Optional.ofNullable(this.or.save(orcamento));
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Orcamento> listar(Pageable pageable) {
		return this.or.findAll(pageable);
	}

	@Override
	public void deletarPorId(Long id) {
		this.or.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Orcamento> findAllByProjetoId(Long projetoId, PageRequest pageRequest) {
		return this.or.findAllByProjetoId(projetoId, pageRequest);
	}

}
