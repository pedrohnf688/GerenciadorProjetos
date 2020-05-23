package com.pedrohnf688.api.service;

import java.util.List;
import java.util.Optional;

import com.pedrohnf688.api.modelo.Credencial;

public interface CredencialService {

	Optional<Credencial> buscarPorId(Long id);

	Optional<Credencial> salvar(Credencial credencial);

	List<Credencial> listar();

	void deletarPorId(Long id);

	Optional<Credencial> buscarPorUsername(String username);

	Optional<Credencial> findByUsuarioId(Long usuarioId);

	Optional<Credencial> buscarPorEmail(String email);

}
