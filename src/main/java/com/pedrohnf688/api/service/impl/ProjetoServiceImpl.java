package com.pedrohnf688.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<Projeto> listar() {
		return this.pr.findAll();
	}

	@Override
	public void deletarPorId(Long id) {
		this.pr.deleteById(id);
	}

	@Override
	public List<Projeto> buscarPorCategoria(EnumTipoCategoria categoria) {
		return this.pr.findAllByTipoCategoria(categoria);
	}

	@Override
	public List<Projeto> buscarPorStatus(EnumStatusProjeto status) {
		return this.pr.findAllByStatusProjeto(status);
	}

	@Override
	public List<Projeto> findAllByEquipeId(Long equipeId) {
		return this.pr.findAllByEquipeId(equipeId);
	}

}
