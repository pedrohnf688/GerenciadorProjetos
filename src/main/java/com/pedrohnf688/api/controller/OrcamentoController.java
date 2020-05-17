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
import com.pedrohnf688.api.modelo.Orcamento;
import com.pedrohnf688.api.service.impl.OrcamentoServiceImpl;

@RestController
@RequestMapping(value = "/orcamento")
public class OrcamentoController {

	@Autowired
	private OrcamentoServiceImpl osi;
	
	@GetMapping
	public ResponseEntity<Response<List<Orcamento>>> listAllOrcamentos() {
		Response<List<Orcamento>> response = new Response<List<Orcamento>>();
		List<Orcamento> listaOrcamentos = this.osi.listar();

		if (listaOrcamentos.isEmpty()) {
			response.getErros().add("A lista de orçamentos está vazia.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(listaOrcamentos);
		return ResponseEntity.ok(response);
	}

	
	@GetMapping(value = "{orcamentoId}")
	public ResponseEntity<Response<Orcamento>> listByOrcamentoId(@PathVariable("orcamentoId") Long orcamentoId) {
		Response<Orcamento> response = new Response<Orcamento>();
		Optional<Orcamento> o = this.osi.buscarPorId(orcamentoId);

		if (!o.isPresent()) {
			response.getErros().add("Orçamento não existente.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(o.get());
		return ResponseEntity.ok(response);
	}


	@GetMapping(value = "projeto/{projetoId}")
	public ResponseEntity<Response<List<Orcamento>>> listAllOrcamentosByProjetoId(@PathVariable("projetoId") Long projetoId) {
		Response<List<Orcamento>> response = new Response<List<Orcamento>>();
		List<Orcamento> listaOrcamentos = this.osi.findAllByProjetoId(projetoId);

		if (listaOrcamentos.isEmpty()) {
			response.getErros().add("A lista de orçamentos está vazia.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(listaOrcamentos);
		return ResponseEntity.ok(response);
	}

	
}
