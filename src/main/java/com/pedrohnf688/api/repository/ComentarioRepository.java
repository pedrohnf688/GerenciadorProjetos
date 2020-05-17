package com.pedrohnf688.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pedrohnf688.api.modelo.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

	List<Comentario> findAllByAutor(String autor);

	List<Comentario> findAllByTarefaId(Long tarefaId);

}
