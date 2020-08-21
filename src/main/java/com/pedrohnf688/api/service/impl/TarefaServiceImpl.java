package com.pedrohnf688.api.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pedrohnf688.api.modelo.Tarefa;
import com.pedrohnf688.api.modelo.enums.EnumStatusTarefa;
import com.pedrohnf688.api.repository.TarefaRepository;
import com.pedrohnf688.api.service.TarefaService;

@Service
@Transactional(readOnly = false)
public class TarefaServiceImpl implements TarefaService {

	@Autowired
	private TarefaRepository tr;

	@Override
	@Transactional(readOnly = true)
	public Optional<Tarefa> buscarPorId(Long id) {
		return this.tr.findById(id);
	}

	@Override
	public Optional<Tarefa> salvar(Tarefa tarefa) {
		return Optional.ofNullable(this.tr.save(tarefa));
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Tarefa> listar(Pageable pageable) {
		return this.tr.findAll(pageable);
	}

	@Override
	public void deletarPorId(Long id) {
		this.tr.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Tarefa> listaPorDateCreated(Date date, PageRequest pageRequest) {
		return this.tr.findAllByDateCreated(date, pageRequest);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Tarefa> buscarPorTitulo(String titulo) {
		return Optional.ofNullable(this.tr.findByTitulo(titulo));
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Tarefa> listaPorDateEStatus(Date date, EnumStatusTarefa status, PageRequest pageRequest) {
		return this.tr.findAllByDateCreatedAndStatusTarefa(date, status, pageRequest);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Tarefa> listaPorStatus(EnumStatusTarefa status, PageRequest pageRequest) {
		return this.tr.findAllByStatusTarefa(status, pageRequest);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Tarefa> findAllByUsuarioId(Long usuarioId, PageRequest pageRequest) {
		return this.tr.findAllByUsuarioId(usuarioId, pageRequest);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Tarefa> findAllByProjetoId(Long projetoId, PageRequest pageRequest) {
		return this.tr.findAllByProjetoId(projetoId, pageRequest);
	}

}
