package com.pedrohnf688.api.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedrohnf688.api.helper.PasswordUtils;
import com.pedrohnf688.api.helper.Response;
import com.pedrohnf688.api.modelo.Credencial;
import com.pedrohnf688.api.modelo.Usuario;
import com.pedrohnf688.api.modelo.dtos.CredencialDto;
import com.pedrohnf688.api.service.impl.CredencialServiceImpl;

@RestController
@RequestMapping(value = "/credencial")
public class CredencialController {

	@Autowired
	private CredencialServiceImpl csi;

	@GetMapping
	public ResponseEntity<Response<List<Credencial>>> listAllCredencial() {
		Response<List<Credencial>> response = new Response<List<Credencial>>();
		List<Credencial> listaCredencial = this.csi.listar();

		if (listaCredencial.isEmpty()) {
			response.getErros().add("A lista de credenciais está vazia.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(listaCredencial);
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "{credencialId}")
	public ResponseEntity<Response<Credencial>> findById(@PathVariable("credencialId") Long credencialId) {
		Response<Credencial> response = new Response<Credencial>();
		Optional<Credencial> c = this.csi.buscarPorId(credencialId);

		if (!c.isPresent()) {
			response.getErros().add("Credencial não existente.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(c.get());
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Response<CredencialDto>> cadastrarCredencial(@Valid @RequestBody CredencialDto credencialDto,
			BindingResult result) throws NoSuchAlgorithmException {
		Response<CredencialDto> response = new Response<CredencialDto>();

		this.csi.buscarPorUsername(credencialDto.getUsername())
				.ifPresent(c -> result.addError(new ObjectError("Credencial", "Username já existente.")));
		this.csi.buscarPorEmail(credencialDto.getEmail())
				.ifPresent(c -> result.addError(new ObjectError("Credencial", "Email já existente.")));

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		Credencial c = new Credencial();
		c.setEmail(credencialDto.getEmail());
		c.setSenha(PasswordUtils.gerarBCrypt(credencialDto.getSenha()));
		c.setUsername(credencialDto.getUsername());

		Usuario u = new Usuario();
		u.setCargo(credencialDto.getCargo());
		u.setDataNascimento(credencialDto.getDataNascimento());
		u.setDescricao(credencialDto.getDescricao());
		u.setNome(credencialDto.getNome());
		u.setTipoSexo(credencialDto.getTipoSexo());
		u.setTipoUsuario(credencialDto.getTipoUsuario());
		u.setLider(credencialDto.getLider());
		c.setUsuario(u);

		this.csi.salvar(c);
		response.setData(credencialDto);
		return ResponseEntity.ok(response);
	}

}
