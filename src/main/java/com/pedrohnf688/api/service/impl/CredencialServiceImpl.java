package com.pedrohnf688.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedrohnf688.api.modelo.Credencial;
import com.pedrohnf688.api.repository.CredencialRepository;
import com.pedrohnf688.api.service.CredencialService;

@Service
public class CredencialServiceImpl implements CredencialService {

	private static final Logger log = LoggerFactory.getLogger(CredencialService.class);

	@Autowired
	private CredencialRepository credencialRepository;

	public Optional<Credencial> buscarPorUsername(String username) {
		log.info("Buscando usuario pelo username: {}", username);
		return Optional.ofNullable(this.credencialRepository.findByUsername(username));
	}

	@Override
	public Optional<Credencial> buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Credencial> salvar(Credencial credencial) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Credencial> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletarPorId(Long id) {
		// TODO Auto-generated method stub

	}

}
