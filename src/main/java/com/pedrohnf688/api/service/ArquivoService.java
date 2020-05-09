package com.pedrohnf688.api.service;

import org.springframework.web.multipart.MultipartFile;

import com.pedrohnf688.api.modelo.Arquivo;

public interface ArquivoService {
	
	Arquivo storeFile(MultipartFile file);
	
	Arquivo getFile(String fileId);

}
