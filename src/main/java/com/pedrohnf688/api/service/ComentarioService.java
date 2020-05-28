package com.pedrohnf688.api.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.pedrohnf688.api.modelo.Comentario;

public interface ComentarioService {

	Optional<Comentario> buscarPorId(Long id);

	Optional<Comentario> salvar(Comentario Comentario);

	Page<Comentario> listar(Pageable pageable);

	void deletarPorId(Long id);

	Page<Comentario> listaPorAutor(String autor, PageRequest pageRequest);

	Page<Comentario> findAllByTarefaId(Long tarefaId, PageRequest pageRequest);
}
