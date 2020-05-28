package com.pedrohnf688.api.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.pedrohnf688.api.modelo.Equipe;

@Repository
public interface EquipeRepository extends PagingAndSortingRepository<Equipe, Long> {

	Equipe findByTitulo(String titulo);

	Page<Equipe> findAllByDateCreated(Date date, Pageable pageable);

}
