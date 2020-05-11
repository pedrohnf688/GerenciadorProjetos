package com.pedrohnf688.api.service;

import java.util.List;
import java.util.Optional;

import com.pedrohnf688.api.modelo.Comentario;
import com.pedrohnf688.api.modelo.Projeto;

public interface ComentarioService {

	Optional<Comentario> buscarPorId(Long id);

	Optional<Comentario> salvar(Comentario Comentario);

	List<Comentario> listar();

	void deletarPorId(Long id);

	List<Comentario> listaPorAutor(String autor);

	List<Projeto> findAllByTarefaId(Long tarefaId);
}
