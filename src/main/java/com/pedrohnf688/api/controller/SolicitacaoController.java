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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pedrohnf688.api.helper.Response;
import com.pedrohnf688.api.modelo.Solicitacao;
import com.pedrohnf688.api.modelo.Usuario;
import com.pedrohnf688.api.modelo.enums.EnumStatusSolicitacao;
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
	public ResponseEntity<Response<Page<Solicitacao>>> listAllSolicitacoes(
			@RequestParam(value = "pag", defaultValue = "0") int pag,
			@RequestParam(value = "ord", defaultValue = "id") String ord,
			@RequestParam(value = "dir", defaultValue = "DESC") String dir) {

		Response<Page<Solicitacao>> response = new Response<Page<Solicitacao>>();

		Pageable pageable = PageRequest.of(pag, 20, Direction.valueOf(dir), ord);

		Page<Solicitacao> listaSolicitacoes = this.ssi.listar(pageable);

		if (listaSolicitacoes.isEmpty()) {
			response.getErros().add("A lista de solicitações está vazia.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(listaSolicitacoes);
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "{solicitacaoId}")
	public ResponseEntity<Response<Solicitacao>> listBySolicitacaoId(
			@PathVariable("solicitacaoId") Long solicitacaoId) {
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
	public ResponseEntity<Response<Page<Solicitacao>>> listBySolicitacaoClienteId(
			@PathVariable("usuarioId") Long usuarioId, @RequestParam(value = "pag", defaultValue = "0") int pag,
			@RequestParam(value = "ord", defaultValue = "id") String ord,
			@RequestParam(value = "dir", defaultValue = "DESC") String dir) {

		Response<Page<Solicitacao>> response = new Response<Page<Solicitacao>>();

		PageRequest pageRequest = PageRequest.of(pag, 20, Direction.valueOf(dir), ord);

		Page<Solicitacao> listaSolicitacoes = this.ssi.findAllByUsuarioId(usuarioId, pageRequest);

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

		if (!u.isPresent()) {
			result.addError(new ObjectError("Usuário", "Usuário não encontrado."));
		}

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		solicitacao.setDateCreated(new Date());
		solicitacao.setUsuario(u.get());
		response.setData(this.ssi.salvar(solicitacao).get());
		return ResponseEntity.ok(response);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<String>> remover(@PathVariable("id") Long id) {

		Response<String> response = new Response<String>();

		Optional<Solicitacao> s = this.ssi.buscarPorId(id);

		if (!s.isPresent()) {
			response.getErros().add("Solicitação não existente");
			return ResponseEntity.badRequest().body(response);
		}

		this.ssi.deletarPorId(id);
		return ResponseEntity.ok(new Response<String>());
	}

	@PutMapping("/alterarStatusSolicitacao")
	public ResponseEntity<Response<Solicitacao>> alterarStatusSolicitacao(@PathVariable("id") Long id,
			@RequestParam("novoStatus") String novoStatus, @RequestParam("problema") String problema) {
		
		Response<Solicitacao> response = new Response<Solicitacao>();
		
		Optional<Solicitacao> s = this.ssi.buscarPorId(id);

		if (!s.isPresent()) {
			response.getErros().add("Solicitação não existente");
			return ResponseEntity.badRequest().body(response);
		}

		if(s.get().getStatusSolicitacao() == EnumStatusSolicitacao.RECUSADA && s.get().getProblema() == null) {
			response.getErros().add("É obrigado informar o problema da solicitação por ser recusada.");
			return ResponseEntity.badRequest().body(response);
		}else if(s.get().getProblema() != null) {
			
		}

		return ResponseEntity.ok(response);
	}

}
