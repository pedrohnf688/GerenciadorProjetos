package com.pedrohnf688.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pedrohnf688.api.modelo.Comentario;
import com.pedrohnf688.api.modelo.Projeto;
import com.pedrohnf688.api.service.ComentarioService;

@Service
public class ComentarioServiceImpl implements ComentarioService {

	@Override
	public Optional<Comentario> buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Comentario> salvar(Comentario Comentario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comentario> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletarPorId(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Comentario> listaPorAutor(String autor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Projeto> findAllByTarefaId(Long tarefaId) {
		// TODO Auto-generated method stub
		return null;
	}

}
