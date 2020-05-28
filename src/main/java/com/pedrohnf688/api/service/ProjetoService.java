package com.pedrohnf688.api.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.pedrohnf688.api.modelo.Projeto;
import com.pedrohnf688.api.modelo.enums.EnumStatusProjeto;
import com.pedrohnf688.api.modelo.enums.EnumTipoCategoria;

public interface ProjetoService {

	Optional<Projeto> buscarPorId(Long id);

	Optional<Projeto> salvar(Projeto projeto);

	Page<Projeto> listar(Pageable pageable);

	void deletarPorId(Long id);

	Page<Projeto> buscarPorCategoria(EnumTipoCategoria categoria, PageRequest pageRequest);

	Page<Projeto> buscarPorStatus(EnumStatusProjeto status, PageRequest pageRequest);

	Page<Projeto> findAllByEquipeId(Long equipeId, PageRequest pageRequest);

}
