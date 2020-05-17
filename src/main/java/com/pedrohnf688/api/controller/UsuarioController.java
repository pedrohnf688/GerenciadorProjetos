package com.pedrohnf688.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedrohnf688.api.helper.Response;
import com.pedrohnf688.api.modelo.Usuario;
import com.pedrohnf688.api.modelo.enums.EnumTipoUsuario;
import com.pedrohnf688.api.service.impl.UsuarioServiceImpl;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioServiceImpl usi;

	@GetMapping
	public ResponseEntity<Response<List<Usuario>>> listAllUsuarios() {
		Response<List<Usuario>> response = new Response<List<Usuario>>();
		List<Usuario> listaUsuarios = this.usi.listar();

		if (listaUsuarios.isEmpty()) {
			response.getErros().add("A lista de usuários está vazia.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(listaUsuarios);
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "{usuarioId}")
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
	public ResponseEntity<Response<List<Usuario>>> listAllClientes() {
		Response<List<Usuario>> response = new Response<List<Usuario>>();
		List<Usuario> listaClientes = this.usi.listaPorPerfil(EnumTipoUsuario.ROLE_CLIENTE);

		if (listaClientes.isEmpty()) {
			response.getErros().add("A lista de clientes está vazia.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(listaClientes);
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "admins")
	public ResponseEntity<Response<List<Usuario>>> listAllAdministradores() {
		Response<List<Usuario>> response = new Response<List<Usuario>>();
		List<Usuario> listaAdmins = this.usi.listaPorPerfil(EnumTipoUsuario.ROLE_ADMINISTRADOR);

		if (listaAdmins.isEmpty()) {
			response.getErros().add("A lista de administradores está vazia.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(listaAdmins);
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "devs")
	public ResponseEntity<Response<List<Usuario>>> listAllDesenvolvedores() {
		Response<List<Usuario>> response = new Response<List<Usuario>>();
		List<Usuario> listaDevs = this.usi.listaPorPerfil(EnumTipoUsuario.ROLE_DESENVOLVEDOR);

		if (listaDevs.isEmpty()) {
			response.getErros().add("A lista de desenvolvedores está vazia.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(listaDevs);
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "equipe/{equipeId}")
	public ResponseEntity<Response<List<Usuario>>> listAllByEquipeId(@PathVariable("equipeId") Long equipeId) {
		Response<List<Usuario>> response = new Response<List<Usuario>>();
		List<Usuario> listaEquipe = this.usi.findAllByEquipeId(equipeId);

		if (listaEquipe.isEmpty()) {
			response.getErros().add("A lista de membros da equipe está vazia.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(listaEquipe);
		return ResponseEntity.ok(response);
	}

}
