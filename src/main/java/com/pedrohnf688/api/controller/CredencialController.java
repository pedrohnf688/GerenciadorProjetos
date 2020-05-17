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
import com.pedrohnf688.api.modelo.Credencial;
import com.pedrohnf688.api.service.impl.CredencialServiceImpl;

@RestController
@RequestMapping(value = "/credencial")
public class CredencialController {

	@Autowired
	private CredencialServiceImpl csi;

	@GetMapping
	public ResponseEntity<Response<List<Credencial>>> listAllCredencial() {
		Response<List<Credencial>> response = new Response<List<Credencial>>();
		List<Credencial> listaCredencial = this.csi.listar();

		if (listaCredencial.isEmpty()) {
			response.getErros().add("A lista de credenciais está vazia.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(listaCredencial);
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "{credencialId}")
	public ResponseEntity<Response<Credencial>> findById(@PathVariable("credencialId") Long credencialId) {
		Response<Credencial> response = new Response<Credencial>();
		Optional<Credencial> c = this.csi.buscarPorId(credencialId);

		if (!c.isPresent()) {
			response.getErros().add("Credencial não existente.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(c.get());
		return ResponseEntity.ok(response);
	}

}
