package com.pedrohnf688.api.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.pedrohnf688.api.modelo.Equipe;

public interface EquipeService {

	Optional<Equipe> buscarPorId(Long id);

	Optional<Equipe> salvar(Equipe equipe);

	Page<Equipe> listar(Pageable pageable);

	void deletarPorId(Long id);

	Optional<Equipe> buscarPorTitulo(String titulo);

	Page<Equipe> listaPorDateCreated(Date date, PageRequest pageRequest);

}
