package com.pedrohnf688.api.controller;

import java.util.Date;
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
import com.pedrohnf688.api.modelo.Tarefa;
import com.pedrohnf688.api.modelo.enums.EnumStatusTarefa;
import com.pedrohnf688.api.service.impl.TarefaServiceImpl;

@RestController
@RequestMapping(value = "/tarefa")
public class TarefaController {

	@Autowired
	private TarefaServiceImpl tsi;

	@GetMapping
	public ResponseEntity<Response<List<Tarefa>>> listAllTarefas() {
		Response<List<Tarefa>> response = new Response<List<Tarefa>>();
		List<Tarefa> listaTarefas = this.tsi.listar();

		if (listaTarefas.isEmpty()) {
			response.getErros().add("A lista de tarefas está vazia.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(listaTarefas);
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "{tarefaId}")
	public ResponseEntity<Response<Tarefa>> listByTarefaId(@PathVariable("tarefaId") Long tarefaId) {
		Response<Tarefa> response = new Response<Tarefa>();
		Optional<Tarefa> t = this.tsi.buscarPorId(tarefaId);

		if (!t.isPresent()) {
			response.getErros().add("Tarefa não existente.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(t.get());
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "dateCreated")
	public ResponseEntity<Response<List<Tarefa>>> listTarefaByDataCreated(@RequestParam("dateCreated") Date dateCreated) {
		Response<List<Tarefa>> response = new Response<List<Tarefa>>();
		List<Tarefa> listaTarefas = this.tsi.listaPorDateCreated(dateCreated);

		if (listaTarefas.isEmpty()) {
			response.getErros().add("A lista de tarefas está vazia.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(listaTarefas);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "dateCreatedAndStatus")
	public ResponseEntity<Response<List<Tarefa>>> listTarefaByDataCreatedAndStatus(@RequestParam("dateCreated") Date dateCreated, 
			@RequestParam("status") EnumStatusTarefa status) {
		Response<List<Tarefa>> response = new Response<List<Tarefa>>();
		List<Tarefa> listaTarefas = this.tsi.listaPorDateEStatus(dateCreated, status);

		if (listaTarefas.isEmpty()) {
			response.getErros().add("A lista de tarefas está vazia.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(listaTarefas);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "status")
	public ResponseEntity<Response<List<Tarefa>>> listTarefaByDataCreatedAndStatus(@RequestParam("status") EnumStatusTarefa status) {
		Response<List<Tarefa>> response = new Response<List<Tarefa>>();
		List<Tarefa> listaTarefas = this.tsi.listaPorStatus(status);

		if (listaTarefas.isEmpty()) {
			response.getErros().add("A lista de tarefas está vazia.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(listaTarefas);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "titulo")
	public ResponseEntity<Response<Tarefa>> listTarefaByTitulo(@RequestParam("titulo") String titulo) {
		Response<Tarefa> response = new Response<Tarefa>();
		Optional<Tarefa> t = this.tsi.buscarPorTitulo(titulo);

		if (!t.isPresent()) {
			response.getErros().add("Tarefa não existente");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(t.get());
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "usuario/{usuarioId}")
	public ResponseEntity<Response<List<Tarefa>>> listAllTarefasByUsuarioId(@PathVariable("usuarioId") Long usuarioId) {
		Response<List<Tarefa>> response = new Response<List<Tarefa>>();
		List<Tarefa> listaTarefas = this.tsi.findAllByUsuarioId(usuarioId);

		if (listaTarefas.isEmpty()) {
			response.getErros().add("A lista de tarefas está vazia.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(listaTarefas);
		return ResponseEntity.ok(response);
	}


	@GetMapping(value = "projeto/{projetoId}")
	public ResponseEntity<Response<List<Tarefa>>> listAllTarefasByProjetoId(@PathVariable("projetoId") Long projetoId) {
		Response<List<Tarefa>> response = new Response<List<Tarefa>>();
		List<Tarefa> listaTarefas = this.tsi.findAllByProjetoId(projetoId);

		if (listaTarefas.isEmpty()) {
			response.getErros().add("A lista de tarefas está vazia.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(listaTarefas);
		return ResponseEntity.ok(response);
	}

}
