package com.pedrohnf688.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pedrohnf688.api.modelo.Projeto;
import com.pedrohnf688.api.modelo.enums.EnumStatusProjeto;
import com.pedrohnf688.api.modelo.enums.EnumTipoCategoria;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

	List<Projeto> findAllByTipoCategoria(EnumTipoCategoria categoria);

	List<Projeto> findAllByStatusProjeto(EnumStatusProjeto status);

	@Query(value = "SELECT * FROM Projeto p WHERE p.equipe.id = :equipeId", nativeQuery = true)
	List<Projeto> findAllByEquipeId(@Param("equipeId") Long equipeId);

}
