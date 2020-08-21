package com.pedrohnf688.api.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pedrohnf688.api.modelo.Equipe;
import com.pedrohnf688.api.repository.EquipeRepository;
import com.pedrohnf688.api.service.EquipeService;

@Service
@Transactional(readOnly = false)
public class EquipeServiceImpl implements EquipeService {

	@Autowired
	private EquipeRepository er;

	@Override
	@Transactional(readOnly = true)
	public Optional<Equipe> buscarPorId(Long id) {
		return this.er.findById(id);
	}

	@Override
	public Optional<Equipe> salvar(Equipe equipe) {
		return Optional.ofNullable(this.er.save(equipe));
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Equipe> listar(Pageable pageable) {
		return this.er.findAll(pageable);
	}

	@Override
	public void deletarPorId(Long id) {
		this.er.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Equipe> buscarPorTitulo(String titulo) {
		return Optional.ofNullable(this.er.findByTitulo(titulo));
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Equipe> listaPorDateCreated(Date date, PageRequest pageRequest) {
		return this.er.findAllByDateCreated(date, pageRequest);
	}

}
