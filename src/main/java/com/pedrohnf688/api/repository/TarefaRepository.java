package com.pedrohnf688.api.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.pedrohnf688.api.modelo.Tarefa;
import com.pedrohnf688.api.modelo.enums.EnumStatusTarefa;

@Repository
public interface TarefaRepository extends PagingAndSortingRepository<Tarefa, Long> {

	Page<Tarefa> findAllByDateCreated(Date date, Pageable pageable);

	Tarefa findByTitulo(String titulo);

	Page<Tarefa> findAllByDateCreatedAndStatusTarefa(Date date, EnumStatusTarefa status, Pageable pageable);

	Page<Tarefa> findAllByStatusTarefa(EnumStatusTarefa status, Pageable pageable);

	Page<Tarefa> findAllByUsuarioId(Long usuarioId, Pageable pageable);

	Page<Tarefa> findAllByProjetoId(Long projetoId, Pageable pageable);
}
