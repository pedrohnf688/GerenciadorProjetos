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
import org.springframework.web.bind.annotation.RestController;

import com.pedrohnf688.api.helper.Response;
import com.pedrohnf688.api.modelo.Solicitacao;
import com.pedrohnf688.api.modelo.Usuario;
import com.pedrohnf688.api.service.impl.SolicitacaoServiceImpl;
import com.pedrohnf688.api.service.impl.UsuarioServiceImpl;

@RestController
@RequestMapping(value = "/solicitacao")
public class SolicitacaoController {

	@Autowired
	private SolicitacaoServiceImpl ssi;
	
	@Autowired
	private UsuarioServiceImpl usi;

	@GetMapping
	public ResponseEntity<Response<List<Solicitacao>>> listAllSolicitacoes() {
		Response<List<Solicitacao>> response = new Response<List<Solicitacao>>();
		List<Solicitacao> listaSolicitacoes = this.ssi.listar();

		if (listaSolicitacoes.isEmpty()) {
			response.getErros().add("A lista de solicitações está vazia.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(listaSolicitacoes);
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "{solicitacaoId}")
	public ResponseEntity<Response<Solicitacao>> listBySolicitacaoId(@PathVariable("solicitacaoId") Long solicitacaoId) {
		Response<Solicitacao> response = new Response<Solicitacao>();
		Optional<Solicitacao> s = this.ssi.buscarPorId(solicitacaoId);

		if (!s.isPresent()) {
			response.getErros().add("Solicitação não existente.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(s.get());
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "usuario/{usuarioId}")
	public ResponseEntity<Response<List<Solicitacao>>> listBySolicitacaoClienteId(@PathVariable("usuarioId") Long usuarioId) {
		Response<List<Solicitacao>> response = new Response<List<Solicitacao>>();
		List<Solicitacao> listaSolicitacoes = this.ssi.findAllByUsuarioId(usuarioId);

		if (listaSolicitacoes.isEmpty()) {
			response.getErros().add("A lista de solicitações do usuário está vazia.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(listaSolicitacoes);
		return ResponseEntity.ok(response);
	}

	
	@PostMapping("{usuarioId}")
	public ResponseEntity<Response<Solicitacao>> cadastrarSolicitacao(@Valid @RequestBody Solicitacao solicitacao,
			@PathVariable("usuarioId") Long usuarioId, BindingResult result) throws NoSuchAlgorithmException {
		
		Response<Solicitacao> response = new Response<Solicitacao>();
		
		Optional<Usuario> u = this.usi.buscarPorId(usuarioId);
		
		if(!u.isPresent()) {
			result.addError(new ObjectError("Usuário", "Usuário não encontrado."));
		}
		
		solicitacao.setDateCreated(new Date());

		if(result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		solicitacao.setUsuario(u.get());
		response.setData(this.ssi.salvar(solicitacao).get());
		return ResponseEntity.ok(response);
	}

}
