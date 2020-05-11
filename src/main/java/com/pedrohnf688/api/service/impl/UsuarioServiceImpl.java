package com.pedrohnf688.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pedrohnf688.api.modelo.Usuario;
import com.pedrohnf688.api.modelo.enums.EnumTipoUsuario;
import com.pedrohnf688.api.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Override
	public Optional<Usuario> buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Usuario> salvar(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletarPorId(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Usuario> listaPorPerfil(EnumTipoUsuario perfil) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> findAllByEquipeId(Long equipeId) {
		// TODO Auto-generated method stub
		return null;
	}

}
