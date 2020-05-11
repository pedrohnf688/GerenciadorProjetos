package com.pedrohnf688.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pedrohnf688.api.modelo.Projeto;
import com.pedrohnf688.api.modelo.enums.EnumStatusProjeto;
import com.pedrohnf688.api.modelo.enums.EnumTipoCategoria;
import com.pedrohnf688.api.service.ProjetoService;

@Service
public class ProjetoServiceImpl implements ProjetoService {

	@Override
	public Optional<Projeto> buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Projeto> salvar(Projeto projeto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Projeto> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletarPorId(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Projeto> buscarPorCategoria(EnumTipoCategoria categoria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Projeto> buscarPorStatus(EnumStatusProjeto status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Projeto> findAllByEquipeId(Long equipeId) {
		// TODO Auto-generated method stub
		return null;
	}

}
