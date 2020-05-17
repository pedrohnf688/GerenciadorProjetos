package com.pedrohnf688.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedrohnf688.api.modelo.Orcamento;
import com.pedrohnf688.api.repository.OrcamentoRepository;
import com.pedrohnf688.api.service.OrcamentoService;

@Service
public class OrcamentoServiceImpl implements OrcamentoService {

	@Autowired
	private OrcamentoRepository or;

	@Override
	public Optional<Orcamento> buscarPorId(Long id) {
		return this.or.findById(id);
	}

	@Override
	public Optional<Orcamento> salvar(Orcamento orcamento) {
		return Optional.ofNullable(this.or.save(orcamento));
	}

	@Override
	public List<Orcamento> listar() {
		return this.or.findAll();
	}

	@Override
	public void deletarPorId(Long id) {
		this.or.deleteById(id);
	}

	@Override
	public List<Orcamento> findAllByProjetoId(Long projetoId) {
		return this.or.findAllByProjetoId(projetoId);
	}

}
