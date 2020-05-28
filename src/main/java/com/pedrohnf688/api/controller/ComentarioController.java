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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pedrohnf688.api.helper.Response;
import com.pedrohnf688.api.modelo.Comentario;
import com.pedrohnf688.api.modelo.Tarefa;
import com.pedrohnf688.api.service.impl.ComentarioServiceImpl;
import com.pedrohnf688.api.service.impl.TarefaServiceImpl;

@RestController
@RequestMapping(value = "/comentario")
public class ComentarioController {

	@Autowired
	private ComentarioServiceImpl csi;

	@Autowired
	private TarefaServiceImpl tsi;

	@GetMapping
	public ResponseEntity<Response<Page<Comentario>>> listAllComentario(
			@RequestParam(value = "pag", defaultValue = "0") int pag,
			@RequestParam(value = "ord", defaultValue = "id") String ord,
			@RequestParam(value = "dir", defaultValue = "DESC") String dir) {

		Response<Page<Comentario>> response = new Response<Page<Comentario>>();

		Pageable pageable = PageRequest.of(pag, 20, Direction.valueOf(dir), ord);

		Page<Comentario> listaComentarios = this.csi.listar(pageable);

		if (listaComentarios.isEmpty()) {
			response.getErros().add("A lista de comentários está vazia.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(listaComentarios);
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "{comentarioId}")
	public ResponseEntity<Response<Comentario>> listByComentarioId(@PathVariable("comentarioId") Long comentarioId) {
		Response<Comentario> response = new Response<Comentario>();
		Optional<Comentario> c = this.csi.buscarPorId(comentarioId);

		if (!c.isPresent()) {
			response.getErros().add("Comentário não existente.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(c.get());
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "autor")
	public ResponseEntity<Response<Page<Comentario>>> listTarefaByDataCreated(@RequestParam("autor") String autor,
			@RequestParam(value = "pag", defaultValue = "0") int pag,
			@RequestParam(value = "ord", defaultValue = "id") String ord,
			@RequestParam(value = "dir", defaultValue = "DESC") String dir) {

		Response<Page<Comentario>> response = new Response<Page<Comentario>>();

		PageRequest pageRequest = PageRequest.of(pag, 20, Direction.valueOf(dir), ord);

		Page<Comentario> listaComentarios = this.csi.listaPorAutor(autor, pageRequest);

		if (listaComentarios.isEmpty()) {
			response.getErros().add("A lista de comentarios está vazia.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(listaComentarios);
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "tarefa/{tarefaId}")
	public ResponseEntity<Response<Page<Comentario>>> listAllComentariosByTarefaId(
			@PathVariable("tarefaId") Long tarefaId, @RequestParam(value = "pag", defaultValue = "0") int pag,
			@RequestParam(value = "ord", defaultValue = "id") String ord,
			@RequestParam(value = "dir", defaultValue = "DESC") String dir) {

		Response<Page<Comentario>> response = new Response<Page<Comentario>>();

		PageRequest pageRequest = PageRequest.of(pag, 20, Direction.valueOf(dir), ord);

		Page<Comentario> listaComentarios = this.csi.findAllByTarefaId(tarefaId, pageRequest);

		if (listaComentarios.isEmpty()) {
			response.getErros().add("A lista de comentários está vazia.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(listaComentarios);
		return ResponseEntity.ok(response);
	}

	@PostMapping("{tarefaId}")
	public ResponseEntity<Response<Comentario>> cadastrarComentario(@Valid @RequestBody Comentario comentario,
			@PathVariable("tarefaId") Long tarefaId, BindingResult result) throws NoSuchAlgorithmException {

		Response<Comentario> response = new Response<Comentario>();

		Optional<Tarefa> t = this.tsi.buscarPorId(tarefaId);

		if (!t.isPresent()) {
			result.addError(new ObjectError("Tarefa", "Tarefa não existente."));
		}

		comentario.setDateCreated(new Date());

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		comentario.setTarefa(t.get());

		response.setData(this.csi.salvar(comentario).get());
		return ResponseEntity.ok(response);
	}

}
