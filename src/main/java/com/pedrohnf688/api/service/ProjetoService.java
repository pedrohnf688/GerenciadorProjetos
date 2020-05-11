package com.pedrohnf688.api.service;

import java.util.List;
import java.util.Optional;

import com.pedrohnf688.api.modelo.Projeto;
import com.pedrohnf688.api.modelo.enums.EnumStatusProjeto;
import com.pedrohnf688.api.modelo.enums.EnumTipoCategoria;

public interface ProjetoService {

	Optional<Projeto> buscarPorId(Long id);

	Optional<Projeto> salvar(Projeto projeto);

	List<Projeto> listar();

	void deletarPorId(Long id);

	List<Projeto> buscarPorCategoria(EnumTipoCategoria categoria);

	List<Projeto> buscarPorStatus(EnumStatusProjeto status);

	List<Projeto> findAllByEquipeId(Long equipeId);

}
