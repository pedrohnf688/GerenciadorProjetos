package com.pedrohnf688.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedrohnf688.api.modelo.Equipe;
import com.pedrohnf688.api.service.impl.EquipeServiceImpl;

@RestController
@RequestMapping(value = "/equipe")
public class EquipeController {

	@Autowired
	private EquipeServiceImpl esi;

	@GetMapping
	public List<Equipe> listAllEquipe() {
		return this.esi.listar();
	}

	@GetMapping(value = "{equipeId}")
	public Optional<Equipe> listByEquipeId(@PathVariable("equipeId") Long equipeId) {
		return this.esi.buscarPorId(equipeId);
	}

}
