package com.pedrohnf688.api.controller;

import java.security.NoSuchAlgorithmException;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pedrohnf688.api.helper.Response;
import com.pedrohnf688.api.modelo.Usuario;
import com.pedrohnf688.api.modelo.dtos.CredencialDto;
import com.pedrohnf688.api.modelo.enums.EnumTipoUsuario;
import com.pedrohnf688.api.service.impl.UsuarioServiceImpl;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioServiceImpl usi;

	@GetMapping
	@ApiOperation(value = "Listagem de todos os usuários")
	public ResponseEntity<Response<Page<Usuario>>> listAllUsuarios(
			@RequestParam(value = "pag", defaultValue = "0") int pag,
			@RequestParam(value = "ord", defaultValue = "id") String ord,
			@RequestParam(value = "dir", defaultValue = "DESC") String dir) {

		Response<Page<Usuario>> response = new Response<Page<Usuario>>();

		Pageable pageable = PageRequest.of(pag, 20, Direction.valueOf(dir), ord);

		Page<Usuario> listaUsuarios = this.usi.listar(pageable);

		if (listaUsuarios.isEmpty()) {
			response.getErros().add("A lista de usuários está vazia.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(listaUsuarios);
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "{usuarioId}")
	@ApiOperation(value = "Buscar usuário por id")
	public ResponseEntity<Response<Usuario>> listByUsuarioId(@PathVariable("usuarioId") Long usuarioId) {
		Response<Usuario> response = new Response<Usuario>();
		Optional<Usuario> c = this.usi.buscarPorId(usuarioId);

		if (!c.isPresent()) {
			response.getErros().add("Usuário não existente.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(c.get());
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "clientes")
	@ApiOperation(value = "Listagem de todos os clientes")
	public ResponseEntity<Response<Page<Usuario>>> listAllClientes(
			@RequestParam(value = "pag", defaultValue = "0") int pag,
			@RequestParam(value = "ord", defaultValue = "id") String ord,
			@RequestParam(value = "dir", defaultValue = "DESC") String dir) {

		Response<Page<Usuario>> response = new Response<Page<Usuario>>();

		PageRequest pageRequest = PageRequest.of(pag, 20, Direction.valueOf(dir), ord);

		Page<Usuario> listaClientes = this.usi.listaPorPerfil(EnumTipoUsuario.ROLE_CLIENTE, pageRequest);

		if (listaClientes.isEmpty()) {
			response.getErros().add("A lista de clientes está vazia.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(listaClientes);
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "admins")
	@ApiOperation(value = "Listagem de todos os administradores")
	public ResponseEntity<Response<Page<Usuario>>> listAllAdministradores(
			@RequestParam(value = "pag", defaultValue = "0") int pag,
			@RequestParam(value = "ord", defaultValue = "id") String ord,
			@RequestParam(value = "dir", defaultValue = "DESC") String dir) {

		Response<Page<Usuario>> response = new Response<Page<Usuario>>();

		PageRequest pageRequest = PageRequest.of(pag, 20, Direction.valueOf(dir), ord);

		Page<Usuario> listaAdmins = this.usi.listaPorPerfil(EnumTipoUsuario.ROLE_ADMINISTRADOR, pageRequest);

		if (listaAdmins.isEmpty()) {
			response.getErros().add("A lista de administradores está vazia.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(listaAdmins);
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "devs")
	@ApiOperation(value = "Listagem de todos os desenvolvedores")
	public ResponseEntity<Response<Page<Usuario>>> listAllDesenvolvedores(
			@RequestParam(value = "pag", defaultValue = "0") int pag,
			@RequestParam(value = "ord", defaultValue = "id") String ord,
			@RequestParam(value = "dir", defaultValue = "DESC") String dir) {

		Response<Page<Usuario>> response = new Response<Page<Usuario>>();

		PageRequest pageRequest = PageRequest.of(pag, 20, Direction.valueOf(dir), ord);

		Page<Usuario> listaDevs = this.usi.listaPorPerfil(EnumTipoUsuario.ROLE_DESENVOLVEDOR, pageRequest);

		if (listaDevs.isEmpty()) {
			response.getErros().add("A lista de desenvolvedores está vazia.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(listaDevs);
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "equipe/{equipeId}")
	@ApiOperation(value = "Listagem de todos os usuários pelo id da equipe")
	public ResponseEntity<Response<Page<Usuario>>> listAllByEquipeId(@PathVariable("equipeId") Long equipeId,
			@RequestParam(value = "pag", defaultValue = "0") int pag,
			@RequestParam(value = "ord", defaultValue = "id") String ord,
			@RequestParam(value = "dir", defaultValue = "DESC") String dir) {

		Response<Page<Usuario>> response = new Response<Page<Usuario>>();

		PageRequest pageRequest = PageRequest.of(pag, 20, Direction.valueOf(dir), ord);

		Page<Usuario> listaEquipe = this.usi.findAllByEquipeId(equipeId, pageRequest);

		if (listaEquipe.isEmpty()) {
			response.getErros().add("A lista de membros da equipe está vazia.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(listaEquipe);
		return ResponseEntity.ok(response);
	}

	@PutMapping(value = "editar/{usuarioId}")
	@ApiOperation(value = "Editar perfil do usuário")
	public ResponseEntity<Response<Usuario>> editarPerfil(@PathVariable("usuarioId") Long usuarioId, @Valid @RequestBody CredencialDto usuario, BindingResult result)
			throws NoSuchAlgorithmException {

		Response<Usuario> response = new Response<Usuario>();

		Optional<Usuario> u = this.usi.buscarPorId(usuarioId);
			
		if(!u.isPresent()) {
			result.addError(new ObjectError("Usuário", "Usuário não encontrado."));
		}
		
		
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(this.usi.salvar(u.get()).get());
		return ResponseEntity.ok(response);
	}

}
