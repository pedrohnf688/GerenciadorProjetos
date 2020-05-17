package com.pedrohnf688.api.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedrohnf688.api.modelo.Equipe;
import com.pedrohnf688.api.repository.EquipeRepository;
import com.pedrohnf688.api.service.EquipeService;

@Service
public class EquipeServiceImpl implements EquipeService {

	@Autowired
	private EquipeRepository er;

	@Override
	public Optional<Equipe> buscarPorId(Long id) {
		return this.er.findById(id);
	}

	@Override
	public Optional<Equipe> salvar(Equipe equipe) {
		return Optional.ofNullable(this.er.save(equipe));
	}

	@Override
	public List<Equipe> listar() {
		return this.er.findAll();
	}

	@Override
	public void deletarPorId(Long id) {
		this.er.deleteById(id);
	}

	@Override
	public Optional<Equipe> buscarPorTitulo(String titulo) {
		return Optional.ofNullable(this.er.findByTitulo(titulo));
	}

	@Override
	public List<Equipe> listaPorDateCreated(Date date) {
		return this.er.findAllByDateCreated(date);
	}

}
