package com.pedrohnf688.api.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pedrohnf688.api.modelo.Projeto;
import com.pedrohnf688.api.modelo.enums.EnumStatusProjeto;
import com.pedrohnf688.api.modelo.enums.EnumTipoCategoria;
import com.pedrohnf688.api.repository.ProjetoRepository;
import com.pedrohnf688.api.service.ProjetoService;

@Service
public class ProjetoServiceImpl implements ProjetoService {

	@Autowired
	private ProjetoRepository pr;

	@Override
	public Optional<Projeto> buscarPorId(Long id) {
		return this.pr.findById(id);
	}

	@Override
	public Optional<Projeto> salvar(Projeto projeto) {
		return Optional.ofNullable(this.pr.save(projeto));
	}

	@Override
	public Page<Projeto> listar(Pageable pageable) {
		return this.pr.findAll(pageable);
	}

	@Override
	public void deletarPorId(Long id) {
		this.pr.deleteById(id);
	}

	@Override
	public Page<Projeto> buscarPorCategoria(EnumTipoCategoria categoria, PageRequest pageRequest) {
		return this.pr.findAllByTipoCategoria(categoria, pageRequest);
	}

	@Override
	public Page<Projeto> buscarPorStatus(EnumStatusProjeto status, PageRequest pageRequest) {
		return this.pr.findAllByStatusProjeto(status, pageRequest);
	}

	@Override
	public Page<Projeto> findAllByEquipeId(Long equipeId, PageRequest pageRequest) {
		return this.pr.findAllByEquipeId(equipeId, pageRequest);
	}

}
