package com.pedrohnf688.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.pedrohnf688.api.modelo.Projeto;
import com.pedrohnf688.api.modelo.enums.EnumStatusProjeto;
import com.pedrohnf688.api.modelo.enums.EnumTipoCategoria;

@Repository
public interface ProjetoRepository extends PagingAndSortingRepository<Projeto, Long> {

	Page<Projeto> findAllByTipoCategoria(EnumTipoCategoria categoria, Pageable pageable);

	Page<Projeto> findAllByStatusProjeto(EnumStatusProjeto status, Pageable pageable);

	Page<Projeto> findAllByEquipeId(Long equipeId, Pageable pageable);

}
