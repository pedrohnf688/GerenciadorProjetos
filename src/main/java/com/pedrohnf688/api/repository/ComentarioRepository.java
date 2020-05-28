package com.pedrohnf688.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.pedrohnf688.api.modelo.Comentario;

@Repository
public interface ComentarioRepository extends PagingAndSortingRepository<Comentario, Long> {

	Page<Comentario> findAllByAutor(String autor, Pageable pageable);

	Page<Comentario> findAllByTarefaId(Long tarefaId, Pageable pageable);

}
