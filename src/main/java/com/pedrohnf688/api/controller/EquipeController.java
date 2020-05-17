package com.pedrohnf688.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedrohnf688.api.helper.Response;
import com.pedrohnf688.api.modelo.Equipe;
import com.pedrohnf688.api.service.impl.EquipeServiceImpl;

@RestController
@RequestMapping(value = "/equipe")
public class EquipeController {

	@Autowired
	private EquipeServiceImpl esi;

	@GetMapping
	public ResponseEntity<Response<List<Equipe>>> listAllEquipe() {
		Response<List<Equipe>> response = new Response<List<Equipe>>();
		List<Equipe> listaEquipes = this.esi.listar();

		if (listaEquipes.isEmpty()) {
			response.getErros().add("A lista de equipes está vazia.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(listaEquipes);
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "{equipeId}")
	public ResponseEntity<Response<Equipe>> listByEquipeId(@PathVariable("equipeId") Long equipeId) {
		Response<Equipe> response = new Response<Equipe>();
		Optional<Equipe> e = this.esi.buscarPorId(equipeId);

		if (!e.isPresent()) {
			response.getErros().add("Equipe não existente.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(e.get());
		return ResponseEntity.ok(response);
	}

}
