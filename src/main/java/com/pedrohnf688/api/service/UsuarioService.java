package com.pedrohnf688.api.service;

import java.util.List;
import java.util.Optional;

import com.pedrohnf688.api.modelo.Usuario;
import com.pedrohnf688.api.modelo.enums.EnumTipoUsuario;

public interface UsuarioService {

	Optional<Usuario> buscarPorId(Long id);

	Optional<Usuario> salvar(Usuario usuario);

	List<Usuario> listar();

	void deletarPorId(Long id);

	List<Usuario> listaPorPerfil(EnumTipoUsuario perfil);

	List<Usuario> findAllByEquipeId(Long equipeId);
	
}
