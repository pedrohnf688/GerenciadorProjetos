package com.pedrohnf688.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedrohnf688.api.modelo.Credencial;
import com.pedrohnf688.api.service.impl.CredencialServiceImpl;

@RestController
@RequestMapping(value = "/credencial")
public class CredencialController {

	@Autowired
	private CredencialServiceImpl csi;

	@GetMapping
	public List<Credencial> listAllCredencial() {
		return this.csi.listar();
	}

	@GetMapping(value = "{credencialId}")
	public Optional<Credencial> listByEquipeId(@PathVariable("credencialId") Long credencialId) {
		return this.csi.buscarPorId(credencialId);
	}

}
