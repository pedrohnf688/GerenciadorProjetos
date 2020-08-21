package com.pedrohnf688.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pedrohnf688.api.helper.Response;
import com.pedrohnf688.api.modelo.Arquivo;
import com.pedrohnf688.api.modelo.dtos.UploadFileResponse;
import com.pedrohnf688.api.service.impl.ArquivoServiceImpl;

@RestController
@RequestMapping(value = "/arquivo")
public class ArquivoController {

	@Autowired
	private ArquivoServiceImpl as;

	@PostMapping("/uploadFile")
	public ResponseEntity<Response<UploadFileResponse>> uploadFile(@RequestParam("file") MultipartFile file) {
		Response<UploadFileResponse> response = new Response<UploadFileResponse>();

		Arquivo dbFile = as.storeFile(file);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("arquivo/downloadFile/")
				.path(dbFile.getId()).toUriString();

		dbFile.setSize(file.getSize());
		dbFile.setFileDownloadUri(fileDownloadUri);
		this.as.salvar(dbFile);

		response.setData(new UploadFileResponse(dbFile.getFileName(), dbFile.getFileDownloadUri(), dbFile.getFileType(),dbFile.getSize()));
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/downloadFile/{fileId}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {

		Arquivo dbFile = as.getFile(fileId);

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(dbFile.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
				.body(new ByteArrayResource(dbFile.getData()));
	}

}
