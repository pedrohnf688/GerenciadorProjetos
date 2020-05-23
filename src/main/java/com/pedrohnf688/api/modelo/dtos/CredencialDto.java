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
	private String nome;
	private Date dataNascimento;

	@Enumerated(EnumType.STRING)
	private EnumTipoUsuario tipoUsuario;

	@Enumerated(EnumType.STRING)
	private EnumTipoSexo tipoSexo;
	private String cargo;
	private String descricao;
	private Boolean lider = false;


}
