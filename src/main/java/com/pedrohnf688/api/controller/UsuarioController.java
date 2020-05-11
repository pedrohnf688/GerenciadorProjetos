package com.pedrohnf688.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedrohnf688.api.modelo.Usuario;
import com.pedrohnf688.api.modelo.enums.EnumTipoUsuario;
import com.pedrohnf688.api.service.impl.UsuarioServiceImpl;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioServiceImpl usi;

	@GetMapping
	public List<Usuario> listAllUsuarios() {
		return this.usi.listar();
	}

	@GetMapping(value = "{usuarioId}")
	public Optional<Usuario> listByUsuarioId(@PathVariable("usuarioId") Long usuarioId) {
		return this.usi.buscarPorId(usuarioId);
	}

	@GetMapping(value = "clientes")
	public List<Usuario> listAllClientes() {
		return this.usi.listaPorPerfil(EnumTipoUsuario.ROLE_CLIENTE);
	}

	@GetMapping(value = "admins")
	public List<Usuario> listAllAdministradores() {
		return this.usi.listaPorPerfil(EnumTipoUsuario.ROLE_ADMINISTRADOR);
	}

	@GetMapping(value = "devs")
	public List<Usuario> listAllDesenvolvedores() {
		return this.usi.listaPorPerfil(EnumTipoUsuario.ROLE_DESENVOLVEDOR);
	}

	@GetMapping(value = "equipe/{equipeId}")
	public List<Usuario> listAllByEquipeId(@PathVariable("equipeId") Long equipeId) {
		return this.usi.findAllByEquipeId(equipeId);
	}

}
