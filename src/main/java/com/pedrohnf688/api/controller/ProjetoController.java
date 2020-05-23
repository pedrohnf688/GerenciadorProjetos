package com.pedrohnf688.api.controller;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.pedrohnf688.api.modelo.Projeto;
import com.pedrohnf688.api.modelo.Solicitacao;
import com.pedrohnf688.api.modelo.enums.EnumStatusProjeto;
import com.pedrohnf688.api.modelo.enums.EnumTipoCategoria;
import com.pedrohnf688.api.service.impl.ProjetoServiceImpl;
import com.pedrohnf688.api.service.impl.SolicitacaoServiceImpl;

@RestController
@RequestMapping(value = "/projeto")
public class ProjetoController {

	@Autowired
	private ProjetoServiceImpl psi;
	
	@Autowired
	private SolicitacaoServiceImpl ssi;

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
	public ResponseEntity<Response<List<Projeto>>> listProjetoByCategoria(
			@RequestParam("categoria") EnumTipoCategoria categoria) {
		Response<List<Projeto>> response = new Response<List<Projeto>>();
		List<Projeto> listaProjetos = this.psi.buscarPorCategoria(categoria);

		if (listaProjetos.isEmpty()) {
			response.getErros().add("A lista de projeto da categoria "
					+ categoria.toString().replaceAll("_", " ").toLowerCase() + " está vazia.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(listaProjetos);
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "status")
	public ResponseEntity<Response<List<Projeto>>> listProjetoByStatus(
			@RequestParam("status") EnumStatusProjeto status) {
		Response<List<Projeto>> response = new Response<List<Projeto>>();
		List<Projeto> listaProjetos = this.psi.buscarPorStatus(status);

		if (listaProjetos.isEmpty()) {
			response.getErros().add("A lista de projeto de status "
					+ status.toString().replaceAll("_", " ").toLowerCase() + " está vazia.");
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

	@PostMapping("{solicitacaoId}")
	public ResponseEntity<Response<Projeto>> cadastrarProjeto(@Valid @RequestBody Projeto projeto,
			@PathVariable("solicitacaoId") Long solicitacaoId, BindingResult result) throws NoSuchAlgorithmException {
		
		Response<Projeto> response = new Response<Projeto>();
		
		Optional<Solicitacao> s = this.ssi.buscarPorId(solicitacaoId);
		
		if(!s.isPresent()) {
			result.addError(new ObjectError("Solicitação", "Solicitação não existente."));
		}
		
		projeto.setDateCreated(new Date());

		if(result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		projeto.setSolicitacao(s.get());
		response.setData(this.psi.salvar(projeto).get());
		return ResponseEntity.ok(response);
	}

	
}
