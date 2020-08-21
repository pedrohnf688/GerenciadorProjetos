package com.pedrohnf688.api.modelo.dtos;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.pedrohnf688.api.modelo.enums.EnumTipoSexo;
import com.pedrohnf688.api.modelo.enums.EnumTipoUsuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CredencialDto {

	private String email;
	private String username;
	private String senha;

	private String usuarioNome;
	private Date usuarioDataNascimento;

	@Enumerated(EnumType.STRING)
	private EnumTipoUsuario usuarioTipoUsuario;

	@Enumerated(EnumType.STRING)
	private EnumTipoSexo usuarioTipoSexo;
	private String usuarioCargo;
	private String usuarioDescricao;
	private Boolean usuarioLider;

}
