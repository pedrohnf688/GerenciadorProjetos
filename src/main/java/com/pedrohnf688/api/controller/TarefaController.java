package com.pedrohnf688.api.controller;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pedrohnf688.api.helper.Response;
import com.pedrohnf688.api.modelo.Projeto;
import com.pedrohnf688.api.modelo.Tarefa;
import com.pedrohnf688.api.modelo.enums.EnumStatusTarefa;
import com.pedrohnf688.api.service.impl.ProjetoServiceImpl;
import com.pedrohnf688.api.service.impl.TarefaServiceImpl;

@RestController
@RequestMapping(value = "/tarefa")
public class TarefaController {

	@Autowired
	private TarefaServiceImpl tsi;

	@Autowired
	private ProjetoServiceImpl psi;

	@GetMapping
	public ResponseEntity<Response<Page<Tarefa>>> listAllTarefas(
			@RequestParam(value = "pag", defaultValue = "0") int pag,
			@RequestParam(value = "ord", defaultValue = "id") String ord,
			@RequestParam(value = "dir", defaultValue = "DESC") String dir) {

		Response<Page<Tarefa>> response = new Response<Page<Tarefa>>();

		Pageable pageable = PageRequest.of(pag, 20, Direction.valueOf(dir), ord);

		Page<Tarefa> listaTarefas = this.tsi.listar(pageable);

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
	public ResponseEntity<Response<Page<Tarefa>>> listTarefaByDataCreated(@RequestParam("dateCreated") Date dateCreated,
			@RequestParam(value = "pag", defaultValue = "0") int pag,
			@RequestParam(value = "ord", defaultValue = "id") String ord,
			@RequestParam(value = "dir", defaultValue = "DESC") String dir) {

		Response<Page<Tarefa>> response = new Response<Page<Tarefa>>();

		PageRequest pageRequest = PageRequest.of(pag, 20, Direction.valueOf(dir), ord);

		Page<Tarefa> listaTarefas = this.tsi.listaPorDateCreated(dateCreated, pageRequest);

		if (listaTarefas.isEmpty()) {
			response.getErros().add("A lista de tarefas está vazia.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(listaTarefas);
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "dateCreatedAndStatus")
	public ResponseEntity<Response<Page<Tarefa>>> listTarefaByDataCreatedAndStatus(
			@RequestParam("dateCreated") Date dateCreated, @RequestParam("status") EnumStatusTarefa status,
			@RequestParam(value = "pag", defaultValue = "0") int pag,
			@RequestParam(value = "ord", defaultValue = "id") String ord,
			@RequestParam(value = "dir", defaultValue = "DESC") String dir) {

		Response<Page<Tarefa>> response = new Response<Page<Tarefa>>();

		PageRequest pageRequest = PageRequest.of(pag, 20, Direction.valueOf(dir), ord);

		Page<Tarefa> listaTarefas = this.tsi.listaPorDateEStatus(dateCreated, status, pageRequest);

		if (listaTarefas.isEmpty()) {
			response.getErros().add("A lista de tarefas está vazia.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(listaTarefas);
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "status")
	public ResponseEntity<Response<Page<Tarefa>>> listTarefaByDataCreatedAndStatus(
			@RequestParam("status") EnumStatusTarefa status, @RequestParam(value = "pag", defaultValue = "0") int pag,
			@RequestParam(value = "ord", defaultValue = "id") String ord,
			@RequestParam(value = "dir", defaultValue = "DESC") String dir) {

		Response<Page<Tarefa>> response = new Response<Page<Tarefa>>();

		PageRequest pageRequest = PageRequest.of(pag, 20, Direction.valueOf(dir), ord);

		Page<Tarefa> listaTarefas = this.tsi.listaPorStatus(status, pageRequest);

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
	public ResponseEntity<Response<Page<Tarefa>>> listAllTarefasByUsuarioId(@PathVariable("usuarioId") Long usuarioId,
			@RequestParam(value = "pag", defaultValue = "0") int pag,
			@RequestParam(value = "ord", defaultValue = "id") String ord,
			@RequestParam(value = "dir", defaultValue = "DESC") String dir) {

		Response<Page<Tarefa>> response = new Response<Page<Tarefa>>();

		PageRequest pageRequest = PageRequest.of(pag, 20, Direction.valueOf(dir), ord);

		Page<Tarefa> listaTarefas = this.tsi.findAllByUsuarioId(usuarioId, pageRequest);

		if (listaTarefas.isEmpty()) {
			response.getErros().add("A lista de tarefas está vazia.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(listaTarefas);
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "projeto/{projetoId}")
	public ResponseEntity<Response<Page<Tarefa>>> listAllTarefasByProjetoId(@PathVariable("projetoId") Long projetoId,
			@RequestParam(value = "pag", defaultValue = "0") int pag,
			@RequestParam(value = "ord", defaultValue = "id") String ord,
			@RequestParam(value = "dir", defaultValue = "DESC") String dir) {

		Response<Page<Tarefa>> response = new Response<Page<Tarefa>>();

		PageRequest pageRequest = PageRequest.of(pag, 20, Direction.valueOf(dir), ord);

		Page<Tarefa> listaTarefas = this.tsi.findAllByProjetoId(projetoId, pageRequest);

		if (listaTarefas.isEmpty()) {
			response.getErros().add("A lista de tarefas está vazia.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(listaTarefas);
		return ResponseEntity.ok(response);
	}

	@PostMapping("{projetoId}")
	public ResponseEntity<Response<Tarefa>> cadastrarTarefa(@Valid @RequestBody Tarefa tarefa,
			@PathVariable("projetoId") Long projetoId, BindingResult result) throws NoSuchAlgorithmException {

		Response<Tarefa> response = new Response<Tarefa>();

		Optional<Projeto> p = this.psi.buscarPorId(projetoId);

		if (!p.isPresent()) {
			result.addError(new ObjectError("Projeto", "Projeto não existente."));
		}

		tarefa.setDateCreated(new Date());

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		tarefa.setProjeto(p.get());

		response.setData(this.tsi.salvar(tarefa).get());
		return ResponseEntity.ok(response);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<String>> remover(@PathVariable("id") Long id) {

		Response<String> response = new Response<String>();

		Optional<Tarefa> t = this.tsi.buscarPorId(id);

		if (!t.isPresent()) {
			response.getErros().add("Tarefa não existente");
			return ResponseEntity.badRequest().body(response);
		}

		this.tsi.deletarPorId(id);

		return ResponseEntity.ok(new Response<String>());
	}

}
