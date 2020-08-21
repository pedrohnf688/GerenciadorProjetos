package com.pedrohnf688.api.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pedrohnf688.api.modelo.Comentario;
import com.pedrohnf688.api.repository.ComentarioRepository;
import com.pedrohnf688.api.service.ComentarioService;

@Service
@Transactional(readOnly = false)
public class ComentarioServiceImpl implements ComentarioService {

	@Autowired
	private ComentarioRepository cr;

	@Override
	@Transactional(readOnly = false)
	public Optional<Comentario> buscarPorId(Long id) {
		return this.cr.findById(id);
	}

	@Override
	public Optional<Comentario> salvar(Comentario comentario) {
		return Optional.ofNullable(this.cr.save(comentario));
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Comentario> listar(Pageable pageable) {
		return this.cr.findAll(pageable);
	}

	@Override
	public void deletarPorId(Long id) {
		this.cr.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Comentario> listaPorAutor(String autor, PageRequest pageRequest) {
		return this.cr.findAllByAutor(autor, pageRequest);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Comentario> findAllByTarefaId(Long tarefaId, PageRequest pageRequest) {
		return this.cr.findAllByTarefaId(tarefaId, pageRequest);
	}

}
