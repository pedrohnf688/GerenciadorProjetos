package com.pedrohnf688.api.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.pedrohnf688.api.modelo.Tarefa;
import com.pedrohnf688.api.modelo.enums.EnumStatusTarefa;

public interface TarefaService {

	Optional<Tarefa> buscarPorId(Long id);

	Optional<Tarefa> salvar(Tarefa tarefa);

	List<Tarefa> listar();

	void deletarPorId(Long id);

	List<Tarefa> listaPorDateCreated(Date date);

	Optional<Tarefa> buscarPorTitulo(String titulo);

	List<Tarefa> listaPorDateEStatus(Date date, EnumStatusTarefa status);

	List<Tarefa> listaPorStatus(EnumStatusTarefa status);

	List<Tarefa> findAllByUsuarioId(Long usuarioId);

	List<Tarefa> findAllByProjetoId(Long projetoId);

}
