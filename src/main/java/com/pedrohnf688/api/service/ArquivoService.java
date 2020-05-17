package com.pedrohnf688.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.pedrohnf688.api.modelo.Arquivo;

public interface ArquivoService {
	
	Arquivo storeFile(MultipartFile file);
	
	Arquivo getFile(String fileId);

	List<Arquivo> findAllBySolicitacaoId(Long solicitacaoId);

	Optional<Arquivo> findByfotoPerfilId(Long usuarioId);

}
