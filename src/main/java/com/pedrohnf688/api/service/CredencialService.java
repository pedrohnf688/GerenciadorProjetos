package com.pedrohnf688.api.service;

import java.util.Optional;

import com.pedrohnf688.api.modelo.Credencial;

public interface CredencialService {
	
	Optional<Credencial> buscarPorEmail(String email);
}
