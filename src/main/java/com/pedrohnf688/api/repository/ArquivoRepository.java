package com.pedrohnf688.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pedrohnf688.api.modelo.Arquivo;

@Repository
public interface ArquivoRepository extends JpaRepository<Arquivo, String> {

	List<Arquivo> findAllBySolicitacaoId(Long solicitacaoId);

	Arquivo findByfotoPerfilId(Long usuarioId);

}
