package com.pedrohnf688.api.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pedrohnf688.api.modelo.Solicitacao;
import com.pedrohnf688.api.repository.SolicitacaoRepository;
import com.pedrohnf688.api.service.SolicitacaoService;

@Service
@Transactional(readOnly = false)
public class SolicitacaoServiceImpl implements SolicitacaoService {

	@Autowired
	private SolicitacaoRepository sr;

	@Override
	@Transactional(readOnly = true)
	public Optional<Solicitacao> buscarPorId(Long id) {
		return this.sr.findById(id);
	}

	@Override
	public Optional<Solicitacao> salvar(Solicitacao solicitacao) {
		return Optional.ofNullable(this.sr.save(solicitacao));
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Solicitacao> listar(Pageable pageable) {
		return this.sr.findAll(pageable);
	}

	@Override
	public void deletarPorId(Long id) {
		this.sr.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Solicitacao> findAllByUsuarioId(Long usuarioId, PageRequest pageRequest) {
		return this.sr.findAllByUsuarioId(usuarioId, pageRequest);
	}

}
