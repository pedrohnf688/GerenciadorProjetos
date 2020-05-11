package com.pedrohnf688.api.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pedrohnf688.api.modelo.Tarefa;
import com.pedrohnf688.api.modelo.enums.EnumStatusTarefa;
import com.pedrohnf688.api.service.TarefaService;

@Service
public class TarefaServiceImpl implements TarefaService {

	@Override
	public Optional<Tarefa> buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Tarefa> salvar(Tarefa tarefa) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tarefa> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletarPorId(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Tarefa> listaPorDateCreated(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Tarefa> buscarPorTitulo(String titulo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tarefa> listaPorDateEStatus(Date date, EnumStatusTarefa status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tarefa> listaPorStatus(EnumStatusTarefa status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tarefa> findAllByUsuarioId(Long usuarioId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tarefa> findAllByProjetoId(Long projetoId) {
		// TODO Auto-generated method stub
		return null;
	}

}
