package com.pedrohnf688.api.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pedrohnf688.api.modelo.Usuario;
import com.pedrohnf688.api.modelo.enums.EnumTipoUsuario;
import com.pedrohnf688.api.repository.UsuarioRepository;
import com.pedrohnf688.api.service.UsuarioService;

@Service
@Transactional(readOnly = false)
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository ur;

	@Override
	@Transactional(readOnly = true)
	public Optional<Usuario> buscarPorId(Long id) {
		return this.ur.findById(id);
	}

	@Override
	public Optional<Usuario> salvar(Usuario usuario) {
		return Optional.ofNullable(this.ur.save(usuario));
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Usuario> listar(Pageable pageable) {
		return this.ur.findAll(pageable);
	}

	@Override
	public void deletarPorId(Long id) {
		this.ur.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Usuario> listaPorPerfil(EnumTipoUsuario perfil, PageRequest pageRequest) {
		return this.ur.findByTipoUsuario(perfil, pageRequest);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Usuario> findAllByEquipeId(Long equipeId, PageRequest pageRequest) {
		return this.ur.findAllByEquipeId(equipeId, pageRequest);
	}

}
