package com.pedrohnf688.api.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.pedrohnf688.api.modelo.Credencial;
import com.pedrohnf688.api.modelo.enums.EnumTipoUsuario;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class JwtUserFactory {

	public static JwtUser create(Credencial credencial) {
		return new JwtUser(credencial.getId(), credencial.getUsername(), credencial.getSenha(),
				mapToGrantedAuthorities(credencial.getUsuario().getTipoUsuario()));

	}

	private static List<GrantedAuthority> mapToGrantedAuthorities(EnumTipoUsuario perfilEnum) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(perfilEnum.toString()));
		return authorities;

	}

}
