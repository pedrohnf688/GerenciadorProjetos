package com.pedrohnf688.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedrohnf688.api.modelo.Usuario;
import com.pedrohnf688.api.modelo.enums.EnumTipoUsuario;
import com.pedrohnf688.api.repository.UsuarioRepository;
import com.pedrohnf688.api.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository ur;

	@Override
	public Optional<Usuario> buscarPorId(Long id) {
		return this.ur.findById(id);
	}

	@Override
	public Optional<Usuario> salvar(Usuario usuario) {
		return Optional.ofNullable(this.ur.save(usuario));
	}

	@Override
	public List<Usuario> listar() {
		return this.ur.findAll();
	}

	@Override
	public void deletarPorId(Long id) {
		this.ur.deleteById(id);
	}

	@Override
	public List<Usuario> listaPorPerfil(EnumTipoUsuario perfil) {
		return this.ur.findByTipoUsuario(perfil);
	}

	@Override
	public List<Usuario> findAllByEquipeId(Long equipeId) {
		return this.ur.findAllByEquipeId(equipeId);
	}

}
