package com.pedrohnf688.api.modelo.dtos;

import java.util.List;

import com.pedrohnf688.api.modelo.Projeto;
import com.pedrohnf688.api.modelo.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EquipeDto {

	private Long id;
	private List<Usuario> listaUsuarios;
	private List<Projeto> listaProjetos;
}
