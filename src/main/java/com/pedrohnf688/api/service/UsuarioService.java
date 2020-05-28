package com.pedrohnf688.api.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.pedrohnf688.api.modelo.Usuario;
import com.pedrohnf688.api.modelo.enums.EnumTipoUsuario;

public interface UsuarioService {

	Optional<Usuario> buscarPorId(Long id);

	Optional<Usuario> salvar(Usuario usuario);

	Page<Usuario> listar(Pageable pageable);

	void deletarPorId(Long id);

	Page<Usuario> listaPorPerfil(EnumTipoUsuario perfil, PageRequest pageRequest);

	Page<Usuario> findAllByEquipeId(Long equipeId, PageRequest pageRequest);
	
}
