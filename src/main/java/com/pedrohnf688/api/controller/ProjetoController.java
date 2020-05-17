package com.pedrohnf688.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pedrohnf688.api.helper.Response;
import com.pedrohnf688.api.modelo.Projeto;
import com.pedrohnf688.api.modelo.enums.EnumStatusProjeto;
import com.pedrohnf688.api.modelo.enums.EnumTipoCategoria;
import com.pedrohnf688.api.service.impl.ProjetoServiceImpl;

@RestController
@RequestMapping(value = "/projeto")
public class ProjetoController {

	@Autowired
	private ProjetoServiceImpl psi;

	@GetMapping
	public ResponseEntity<Response<List<Projeto>>> listAllProjetos() {
		Response<List<Projeto>> response = new Response<List<Projeto>>();
		List<Projeto> listaProjetos = this.psi.listar();

		if (listaProjetos.isEmpty()) {
			response.getErros().add("A lista de projetos está vazia.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(listaProjetos);
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "{projetoId}")
	public ResponseEntity<Response<Projeto>> listBySolicitacaoId(@PathVariable("projetoId") Long projetoId) {
		Response<Projeto> response = new Response<Projeto>();
		Optional<Projeto> p = this.psi.buscarPorId(projetoId);

		if (!p.isPresent()) {
			response.getErros().add("Projeto não existente.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(p.get());
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "categoria")
	public ResponseEntity<Response<List<Projeto>>> listProjetoByCategoria(@RequestParam("categoria") EnumTipoCategoria categoria) {
		Response<List<Projeto>> response = new Response<List<Projeto>>();
		List<Projeto> listaProjetos = this.psi.buscarPorCategoria(categoria);

		if (listaProjetos.isEmpty()) {
			response.getErros().add("A lista de projeto da categoria " + categoria.toString().replaceAll("_", " ").toLowerCase() + " está vazia.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(listaProjetos);
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "status")
	public ResponseEntity<Response<List<Projeto>>> listProjetoByStatus(@RequestParam("status") EnumStatusProjeto status) {
		Response<List<Projeto>> response = new Response<List<Projeto>>();
		List<Projeto> listaProjetos = this.psi.buscarPorStatus(status);

		if (listaProjetos.isEmpty()) {
			response.getErros().add("A lista de projeto de status " + status.toString().replaceAll("_", " ").toLowerCase() + " está vazia.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(listaProjetos);
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "equipe/{equipeId}")
	public ResponseEntity<Response<List<Projeto>>> listProjetoByEquipeId(@RequestParam("equipeId") Long equipeId) {
		Response<List<Projeto>> response = new Response<List<Projeto>>();
		List<Projeto> listaProjetos = this.psi.findAllByEquipeId(equipeId);

		if (listaProjetos.isEmpty()) {
			response.getErros().add("A lista de projeto da equipe está vazia.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(listaProjetos);
		return ResponseEntity.ok(response);
	}

}
