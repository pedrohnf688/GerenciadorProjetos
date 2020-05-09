package com.pedrohnf688.api.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TokenDto {

	private String token;
	private String perfil;
	private Long id;
	
}
