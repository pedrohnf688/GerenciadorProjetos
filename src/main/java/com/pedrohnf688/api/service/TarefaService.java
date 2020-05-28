package com.pedrohnf688.api.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.pedrohnf688.api.modelo.Tarefa;
import com.pedrohnf688.api.modelo.enums.EnumStatusTarefa;

public interface TarefaService {

	Optional<Tarefa> buscarPorId(Long id);

	Optional<Tarefa> salvar(Tarefa tarefa);

	Page<Tarefa> listar(Pageable pageable);

	void deletarPorId(Long id);

	Page<Tarefa> listaPorDateCreated(Date date, PageRequest pageRequest);

	Optional<Tarefa> buscarPorTitulo(String titulo);

	Page<Tarefa> listaPorDateEStatus(Date date, EnumStatusTarefa status, PageRequest pageRequest);

	Page<Tarefa> listaPorStatus(EnumStatusTarefa status, PageRequest pageRequest);

	Page<Tarefa> findAllByUsuarioId(Long usuarioId, PageRequest pageRequest);

	Page<Tarefa> findAllByProjetoId(Long projetoId, PageRequest pageRequest);

}
