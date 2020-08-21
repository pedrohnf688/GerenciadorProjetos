package com.pedrohnf688.api.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.pedrohnf688.api.exceptions.FileStorageException;
import com.pedrohnf688.api.exceptions.MyFileNotFoundException;
import com.pedrohnf688.api.modelo.Arquivo;
import com.pedrohnf688.api.repository.ArquivoRepository;
import com.pedrohnf688.api.service.ArquivoService;

@Service
@Transactional(readOnly = false)
public class ArquivoServiceImpl implements ArquivoService {

	@Autowired
	private ArquivoRepository ar;

	public Arquivo storeFile(MultipartFile file) {

		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {

			if (fileName.contains("..")) {
				throw new FileStorageException(
						"Desculpa! Nome do arquivo contém sequência de caminho inválida " + fileName);
			}

			Arquivo arquivo = new Arquivo(fileName, file.getContentType(), file.getBytes());

			return ar.save(arquivo);
		} catch (IOException ex) {
			throw new FileStorageException(
					"Não foi possível armazenar o arquivo " + fileName + ". Por favor, tente novamente!", ex);
		}
	}

	public Arquivo getFile(String fileId) {
		return ar.findById(fileId)
				.orElseThrow(() -> new MyFileNotFoundException("Arquivo não encontrado com o id " + fileId));
	}

	@Override
	@Transactional(readOnly = true)
	public List<Arquivo> findAllBySolicitacaoId(Long solicitacaoId) {
		return this.ar.findAllBySolicitacaoId(solicitacaoId);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Arquivo> findByfotoPerfilId(Long usuarioId) {
		return Optional.ofNullable(this.ar.findByfotoPerfilId(usuarioId));
	}

	@Override
	public Optional<Arquivo> salvar(Arquivo arquivo) {
		return Optional.ofNullable(this.ar.save(arquivo));
	}
	

}
