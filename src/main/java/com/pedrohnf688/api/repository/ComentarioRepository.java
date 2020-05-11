package com.pedrohnf688.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pedrohnf688.api.modelo.Comentario;
import com.pedrohnf688.api.modelo.Projeto;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

	List<Comentario> findAllByAutor(String autor);

	@Query(value = "SELECT * FROM Comentario c WHERE c.tarefa.id = :tarefaId", nativeQuery = true)
	List<Projeto> findAllByTarefaId(@Param("tarefaId") Long tarefaId);

}
