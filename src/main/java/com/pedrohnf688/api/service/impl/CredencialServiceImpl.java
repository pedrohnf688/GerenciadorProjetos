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
	private CredencialRepository cr;

	public Optional<Credencial> buscarPorUsername(String username) {
		log.info("Buscando usuario pelo username: {}", username);
		return Optional.ofNullable(this.cr.findByUsername(username));
	}

	@Override
	public Optional<Credencial> buscarPorId(Long id) {
		return this.cr.findById(id);
	}

	@Override
	public Optional<Credencial> salvar(Credencial credencial) {
		return Optional.ofNullable(this.cr.save(credencial));
	}

	@Override
	public List<Credencial> listar() {
		return this.cr.findAll();
	}

	@Override
	public void deletarPorId(Long id) {
		this.cr.deleteById(id);
	}

	@Override
	public Optional<Credencial> findByUsuarioId(Long usuarioId) {
		return Optional.ofNullable(this.cr.findByUsuarioId(usuarioId));
	}

	@Override
	public Optional<Credencial> buscarPorEmail(String email) {
		return Optional.ofNullable(this.cr.findByEmail(email));
	}

}
