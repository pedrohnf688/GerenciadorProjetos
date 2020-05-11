package com.pedrohnf688.api.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pedrohnf688.api.modelo.Tarefa;
import com.pedrohnf688.api.modelo.enums.EnumStatusTarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

	List<Tarefa> findAllByDateCreated(Date date);

	Tarefa findByTitulo(String titulo);

	List<Tarefa> findAllByDateCreatedANDStatusTarefa(Date date, EnumStatusTarefa status);

	List<Tarefa> findAllByStatusTarefa(EnumStatusTarefa status);

	@Query(value = "SELECT * FROM Tarefa t WHERE t.usuario.id = :usuarioId", nativeQuery = true)
	List<Tarefa> findAllByUsuarioId(@Param("usuarioId") Long usuarioId);

	@Query(value = "SELECT * FROM Tarefa t WHERE t.projeto.id = :projetoId", nativeQuery = true)
	List<Tarefa> findAllByProjetoId(@Param("projetoId") Long projetoId);
}
