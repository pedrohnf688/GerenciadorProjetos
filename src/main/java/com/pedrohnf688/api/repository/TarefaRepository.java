package com.pedrohnf688.api.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pedrohnf688.api.modelo.Tarefa;
import com.pedrohnf688.api.modelo.enums.EnumStatusTarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

	List<Tarefa> findAllByDateCreated(Date date);

	Tarefa findByTitulo(String titulo);

	List<Tarefa> findAllByDateCreatedAndStatusTarefa(Date date, EnumStatusTarefa status);

	List<Tarefa> findAllByStatusTarefa(EnumStatusTarefa status);

	List<Tarefa> findAllByUsuarioId(Long usuarioId);

	List<Tarefa> findAllByProjetoId(Long projetoId);
}
