package com.pedrohnf688.api.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.pedrohnf688.api.modelo.Equipe;

public interface EquipeService {

	Optional<Equipe> buscarPorId(Long id);

	Optional<Equipe> salvar(Equipe equipe);

	List<Equipe> listar();

	void deletarPorId(Long id);

	Optional<Equipe> buscarPorTitulo(String titulo);

	List<Equipe> listaPorDateCreated(Date date);

}
