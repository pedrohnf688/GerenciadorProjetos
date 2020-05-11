package com.pedrohnf688.api.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pedrohnf688.api.modelo.Equipe;

@Repository
public interface EquipeRepository extends JpaRepository<Equipe, Long> {

	Equipe findByTitulo(String titulo);

	List<Equipe> findAllByDateCreated(Date date);

}
