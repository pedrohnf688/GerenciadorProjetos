package com.pedrohnf688.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedrohnf688.api.modelo.Comentario;
import com.pedrohnf688.api.repository.ComentarioRepository;
import com.pedrohnf688.api.service.ComentarioService;

@Service
public class ComentarioServiceImpl implements ComentarioService {

	@Autowired
	private ComentarioRepository cr;

	@Override
	public Optional<Comentario> buscarPorId(Long id) {
		return this.cr.findById(id);
	}

	@Override
	public Optional<Comentario> salvar(Comentario comentario) {
		return Optional.ofNullable(this.cr.save(comentario));
	}

	@Override
	public List<Comentario> listar() {
		return this.cr.findAll();
	}

	@Override
	public void deletarPorId(Long id) {
		this.cr.deleteById(id);
	}

	@Override
	public List<Comentario> listaPorAutor(String autor) {
		return this.cr.findAllByAutor(autor);
	}

	@Override
	public List<Comentario> findAllByTarefaId(Long tarefaId) {
		return this.cr.findAllByTarefaId(tarefaId);
	}

}
