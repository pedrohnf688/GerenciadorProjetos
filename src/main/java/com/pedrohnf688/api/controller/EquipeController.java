package com.pedrohnf688.api.controller;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import com.pedrohnf688.api.modelo.Equipe;
import com.pedrohnf688.api.modelo.Projeto;
import com.pedrohnf688.api.modelo.Usuario;
import com.pedrohnf688.api.modelo.dtos.EquipeDto;
import com.pedrohnf688.api.service.impl.EquipeServiceImpl;
import com.pedrohnf688.api.service.impl.ProjetoServiceImpl;
import com.pedrohnf688.api.service.impl.UsuarioServiceImpl;

@RestController
@RequestMapping(value = "/equipe")
public class EquipeController {

	@Autowired
	private EquipeServiceImpl esi;

	@Autowired
	private UsuarioServiceImpl usi;

	@Autowired
	private ProjetoServiceImpl psi;

	@GetMapping
	public ResponseEntity<Response<Page<Equipe>>> listAllEquipe(
			@RequestParam(value = "pag", defaultValue = "0") int pag,
			@RequestParam(value = "ord", defaultValue = "id") String ord,
			@RequestParam(value = "dir", defaultValue = "DESC") String dir) {

		Response<Page<Equipe>> response = new Response<Page<Equipe>>();

		Pageable pageable = PageRequest.of(pag, 20, Direction.valueOf(dir), ord);

		Page<Equipe> listaEquipes = this.esi.listar(pageable);

		if (listaEquipes.isEmpty()) {
			response.getErros().add("A lista de equipes está vazia.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(listaEquipes);
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "{equipeId}")
	public ResponseEntity<Response<Equipe>> listByEquipeId(@PathVariable("equipeId") Long equipeId) {
		Response<Equipe> response = new Response<Equipe>();
		Optional<Equipe> e = this.esi.buscarPorId(equipeId);

		if (!e.isPresent()) {
			response.getErros().add("Equipe não existente.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(e.get());
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Response<Equipe>> cadastrarEquipe(@Valid @RequestBody Equipe equipe, BindingResult result)
			throws NoSuchAlgorithmException {
		Response<Equipe> response = new Response<Equipe>();

		this.esi.buscarPorTitulo(equipe.getTitulo())
				.ifPresent(e -> result.addError(new ObjectError("Equipe", "Titulo já existente.")));

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		equipe.setQtdMembros(0);
		equipe.setDateCreated(new Date());

		this.esi.salvar(equipe);
		response.setData(equipe);
		return ResponseEntity.ok(response);
	}

	@PutMapping(value = "addMembros")
	public ResponseEntity<Response<EquipeDto>> adicionarMembros(@Valid @RequestBody EquipeDto equipeDto,
			BindingResult result) throws NoSuchAlgorithmException {

		Response<EquipeDto> response = new Response<EquipeDto>();

		Optional<Equipe> e = this.esi.buscarPorId(equipeDto.getId());
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();

		if (!e.isPresent()) {
			result.addError(new ObjectError("Equipe", "Equipe não existente."));
		} else if (equipeDto.getListaUsuarios().isEmpty()) {
			result.addError(new ObjectError("Equipe", "A lista de membros da equipe está vazia."));
		}

		for (Usuario user : equipeDto.getListaUsuarios()) {
			if (this.usi.buscarPorId(user.getId()).isPresent()) {
				listaUsuarios.add(this.usi.buscarPorId(user.getId()).get());
				user.setEquipe(e.get());
				this.usi.salvar(user);
			} else {
				result.addError(new ObjectError("Usuário", "Usuário:" + user.getId() + " não existente."));
			}
		}

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		e.get().setListaUsuarios(listaUsuarios);
		e.get().setQtdMembros(listaUsuarios.size());

		this.esi.salvar(e.get());

		response.setData(equipeDto);
		return ResponseEntity.ok(response);
	}

	@PutMapping(value = "removerMembros")
	public ResponseEntity<Response<EquipeDto>> removerMembros(@Valid @RequestBody EquipeDto equipeDto,
			BindingResult result) throws NoSuchAlgorithmException {

		Response<EquipeDto> response = new Response<EquipeDto>();

		Optional<Equipe> e = this.esi.buscarPorId(equipeDto.getId());

		if (!e.isPresent()) {
			result.addError(new ObjectError("Equipe", "Equipe não existente."));
		}

		if (e.get().getQtdMembros() == 0) {
			result.addError(new ObjectError("Equipe", "Equipe não tem membros."));
		}

		if (equipeDto.getListaUsuarios().isEmpty()) {
			result.addError(new ObjectError("Equipe", "A lista de membros da equipe está vazia."));
		}

		for (Usuario usuario : equipeDto.getListaUsuarios()) {
			if (e.get().getQtdMembros() > 1) {
				e.get().getListaUsuarios().remove(usuario);
				e.get().setQtdMembros(e.get().getQtdMembros() - 1);
				usuario.setEquipe(null);
				this.usi.salvar(usuario);
				this.esi.salvar(e.get());
			}
		}

		response.setData(equipeDto);
		return ResponseEntity.ok(response);
	}

	@PutMapping(value = "addProjetos")
	public ResponseEntity<Response<EquipeDto>> adicionarProjeto(@Valid @RequestBody EquipeDto equipeDto,
			BindingResult result) throws NoSuchAlgorithmException {

		Response<EquipeDto> response = new Response<EquipeDto>();

		Optional<Equipe> e = this.esi.buscarPorId(equipeDto.getId());
		List<Projeto> listaProjetos = new ArrayList<Projeto>();

		if (!e.isPresent()) {
			result.addError(new ObjectError("Equipe", "Equipe não existente."));
		}

		if (equipeDto.getListaProjetos().isEmpty()) {
			result.addError(new ObjectError("Projeto", "A lista de projetos está vazia."));
		}

		for (Projeto p : equipeDto.getListaProjetos()) {
			if (this.psi.buscarPorId(p.getId()).isPresent()) {
				listaProjetos.add(this.psi.buscarPorId(p.getId()).get());
				p.setEquipe(e.get());
				this.psi.salvar(p);
			} else {
				result.addError(new ObjectError("Projeto", "Projeto:" + p.getId() + " não existente."));
			}
		}

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		e.get().setListaProjetos(listaProjetos);
		this.esi.salvar(e.get());

		response.setData(equipeDto);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<String>> remover(@PathVariable("id") Long id) {

		Response<String> response = new Response<String>();

		Optional<Equipe> e = this.esi.buscarPorId(id);

		if (!e.isPresent()) {
			response.getErros().add("Equipe não existente");
			return ResponseEntity.badRequest().body(response);
		}

		this.esi.deletarPorId(id);

		return ResponseEntity.ok(new Response<String>());
	}

}
