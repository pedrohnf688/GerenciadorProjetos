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
import com.pedrohnf688.api.modelo.Orcamento;
import com.pedrohnf688.api.modelo.Projeto;
import com.pedrohnf688.api.service.impl.OrcamentoServiceImpl;
import com.pedrohnf688.api.service.impl.ProjetoServiceImpl;

@RestController
@RequestMapping(value = "/orcamento")
public class OrcamentoController {

	@Autowired
	private OrcamentoServiceImpl osi;

	@Autowired
	private ProjetoServiceImpl psi;

	@GetMapping
	public ResponseEntity<Response<Page<Orcamento>>> listAllOrcamentos(
			@RequestParam(value = "pag", defaultValue = "0") int pag,
			@RequestParam(value = "ord", defaultValue = "id") String ord,
			@RequestParam(value = "dir", defaultValue = "DESC") String dir) {

		Response<Page<Orcamento>> response = new Response<Page<Orcamento>>();

		Pageable pageable = PageRequest.of(pag, 20, Direction.valueOf(dir), ord);

		Page<Orcamento> listaOrcamentos = this.osi.listar(pageable);

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
	public ResponseEntity<Response<Page<Orcamento>>> listAllOrcamentosByProjetoId(
			@PathVariable("projetoId") Long projetoId, @RequestParam(value = "pag", defaultValue = "0") int pag,
			@RequestParam(value = "ord", defaultValue = "id") String ord,
			@RequestParam(value = "dir", defaultValue = "DESC") String dir) {

		Response<Page<Orcamento>> response = new Response<Page<Orcamento>>();

		PageRequest pageRequest = PageRequest.of(pag, 20, Direction.valueOf(dir), ord);

		Page<Orcamento> listaOrcamentos = this.osi.findAllByProjetoId(projetoId, pageRequest);

		if (listaOrcamentos.isEmpty()) {
			response.getErros().add("A lista de orçamentos está vazia.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(listaOrcamentos);
		return ResponseEntity.ok(response);
	}

	@PostMapping("{projetoId}")
	public ResponseEntity<Response<Orcamento>> cadastrarOrcamento(@Valid @RequestBody Orcamento orcamento,
			@PathVariable("projetoId") Long projetoId, BindingResult result) throws NoSuchAlgorithmException {

		Response<Orcamento> response = new Response<Orcamento>();

		Optional<Projeto> p = this.psi.buscarPorId(projetoId);

		if (!p.isPresent()) {
			result.addError(new ObjectError("Projeto", "Projeto não existente."));
		}

		orcamento.setDateCreated(new Date());

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		orcamento.setProjeto(p.get());

		response.setData(this.osi.salvar(orcamento).get());
		return ResponseEntity.ok(response);
	}

}
