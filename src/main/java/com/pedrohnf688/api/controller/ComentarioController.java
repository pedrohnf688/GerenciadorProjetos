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
import com.pedrohnf688.api.modelo.Comentario;
import com.pedrohnf688.api.service.impl.ComentarioServiceImpl;

@RestController
@RequestMapping(value = "/comentario")
public class ComentarioController {
	
	@Autowired
	private ComentarioServiceImpl csi;
	
	@GetMapping
	public ResponseEntity<Response<List<Comentario>>> listAllComentario() {
		Response<List<Comentario>> response = new Response<List<Comentario>>();
		List<Comentario> listaComentarios = this.csi.listar();

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
	public ResponseEntity<Response<List<Comentario>>> listTarefaByDataCreated(@RequestParam("autor") String autor) {
		Response<List<Comentario>> response = new Response<List<Comentario>>();
		List<Comentario> listaComentarios = this.csi.listaPorAutor(autor);

		if (listaComentarios.isEmpty()) {
			response.getErros().add("A lista de comentarios está vazia.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(listaComentarios);
		return ResponseEntity.ok(response);
	}

	
	
	@GetMapping(value = "tarefa/{tarefaId}")
	public ResponseEntity<Response<List<Comentario>>> listAllComentariosByTarefaId(@PathVariable("tarefaId") Long tarefaId) {
		Response<List<Comentario>> response = new Response<List<Comentario>>();
		List<Comentario> listaComentarios = this.csi.findAllByTarefaId(tarefaId);

		if (listaComentarios.isEmpty()) {
			response.getErros().add("A lista de comentários está vazia.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(listaComentarios);
		return ResponseEntity.ok(response);
	}


}
