package com.pedrohnf688.api.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedrohnf688.api.modelo.Tarefa;
import com.pedrohnf688.api.modelo.enums.EnumStatusTarefa;
import com.pedrohnf688.api.repository.TarefaRepository;
import com.pedrohnf688.api.service.TarefaService;

@Service
public class TarefaServiceImpl implements TarefaService {

	@Autowired
	private TarefaRepository tr;

	@Override
	public Optional<Tarefa> buscarPorId(Long id) {
		return this.tr.findById(id);
	}

	@Override
	public Optional<Tarefa> salvar(Tarefa tarefa) {
		return Optional.ofNullable(this.tr.save(tarefa));
	}

	@Override
	public List<Tarefa> listar() {
		return this.tr.findAll();
	}

	@Override
	public void deletarPorId(Long id) {
		this.tr.deleteById(id);
	}

	@Override
	public List<Tarefa> listaPorDateCreated(Date date) {
		return this.tr.findAllByDateCreated(date);
	}

	@Override
	public Optional<Tarefa> buscarPorTitulo(String titulo) {
		return Optional.ofNullable(this.tr.findByTitulo(titulo));
	}

	@Override
	public List<Tarefa> listaPorDateEStatus(Date date, EnumStatusTarefa status) {
		return this.tr.findAllByDateCreatedAndStatusTarefa(date, status);
	}

	@Override
	public List<Tarefa> listaPorStatus(EnumStatusTarefa status) {
		return this.tr.findAllByStatusTarefa(status);
	}

	@Override
	public List<Tarefa> findAllByUsuarioId(Long usuarioId) {
		return this.tr.findAllByUsuarioId(usuarioId);
	}

	@Override
	public List<Tarefa> findAllByProjetoId(Long projetoId) {
		return this.tr.findAllByProjetoId(projetoId);
	}

}
